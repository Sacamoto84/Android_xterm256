package com.gamer.xterm256

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import timber.log.Timber

class NetCommandDecoder(
    private val channelIn: Channel<String>,               //Входной канал от bt и wifi
    private val channelOutNetCommand: Channel<NetCommand> //Для Отображения списка текст.новая строка
) {

    /**
     * # Добавить команду
     */
    fun addCmd(name: String, cb: (List<String>) -> Unit = { }) = cmdList.add(CliCommand(name, cb))


    private var lastString: String = "" //Прошлая строка
    private val channelRoute = Channel<String>(1000000)

    @OptIn(DelicateCoroutinesApi::class)
    fun run() {
        Timber.i("Запуск декодировщика")
        GlobalScope.launch(Dispatchers.IO) { decodeScope() }
        GlobalScope.launch(Dispatchers.IO) { commandDecoder() }
        GlobalScope.launch(Dispatchers.IO) { cliDecoder() }
    }

    private suspend fun decodeScope() {

        val bigStr: StringBuilder =
                StringBuilder() //Большая строка в которую и складируются данные с канала

        while (true) {

            var string =
                    channelIn.receive() //Получить строку с канала, может содежать несколько строк

            if (isCheckUseCRLF) string =
                    string.replace("\r", "\u001B[01;39;05;0;49;05;10mCR\u001B[2m")

            //Timber.e( "in>>>${string.length} "+string )

            if (string.isEmpty()) continue

            bigStr.append(string) //Захерячиваем в большую строку

            //MARK: Будем сами делить на строки
            while (true) { //Индекс \n
                val indexN = bigStr.indexOf('\n')

                if (indexN != -1) { //Область полюбому имеет конец строки
                    //MARK: Чета есть, копируем в подстроку
                    val stringDoN = bigStr.substring(0, indexN)
                    bigStr.delete(0, bigStr.indexOf('\n') + 1)

                    lastString += stringDoN

                    if (isCheckUseCRLF) lastString += "\u001B[01;39;05;15;49;05;27mLF\u001B[2m"

                    channelRoute.send(lastString)
                    channelOutNetCommand.send(
                        NetCommand(
                            lastString, true
                        )
                    ) //Timber.i( "out>>>${lastString.length} "+lastString )
                    lastString = ""


                } else { //Конец строки не найден
                    //MARK: Тут для дополнения прошлой строки
                    //Получить полную запись посленней строки
                    lastString += bigStr
                    if (lastString.isNotEmpty()) {
                        channelOutNetCommand.send(
                            NetCommand(
                                lastString, false
                            )
                        ) //Timber.w( "out>>>${lastString.length} "+lastString )
                    }
                    bigStr.clear() //Он отжил свое)
                    break
                }

            }


        }


    }

    private suspend fun commandDecoder() {

        while (true) {

            //val raw = "qqq¹xzassd¡¡¡¡¡²45³qw" //'¹' '²' '³' 179 '¡' 161    ¡ A1   §A7 ¿DF ¬AC
            val raw = channelRoute.receive()

            val posStart = raw.indexOf("¹")
            val posCRC = raw.indexOf("²")
            val posEnd = raw.indexOf("³")

            if ((posStart == -1) || (posEnd == -1) || (posCRC == -1) || (posCRC !in (posStart + 1) until posEnd)) { //Timber.e("Ошибка позиции пакета S:$posStart C:$posCRC E:$posEnd")
                continue
            }

            if (((posEnd - posCRC) > 4) || ((posEnd - posCRC) == 1)) {
                Timber.e("S:$posStart C:$posCRC E:$posEnd")
                Timber.e("L0 > Error > (PosE - PosCRC) > 4 or == 1");
                continue
            }

            val crcStr = raw.substring(posCRC + 1 until posEnd)
            var crc = 0
            try {
                crc = crcStr.toInt()
            } catch (e: Exception) {
                Timber.e("Ошибка преобразования CRC $crcStr")
                continue
            }

            val s = raw.substring(posStart + 1 until posCRC)
            if (s == "") {
                Timber.e("Нет тела команды $raw")
                continue
            }
            val crc8 = CRC8(s)

            if (crc.toUByte() != crc8) {
                Timber.e("Ошибка CRC $crc != CRC8 $crc8 $raw")
                continue
            } //Прошли все проверкu
            channelOutCommand.send(s)

        }

    }

    /*
    Name  : CRC-8
    Poly  : 0x31    x^8 + x^5 + x^4 + 1
    Init  : 0xFF
    Revert: false
    XorOut: 0x00
    Check : 0xF7 ("123456789")
    MaxLen: 15 байт(127 бит) - обнаружение
    одинарных, двойных, тройных и всех нечетных ошибок
    */
    private fun CRC8(str: String): UByte {

        var _crc: UByte = 0xFFu.toUByte()
        var i: Int

        for (j in str.indices) {
            _crc = _crc xor str[j].code.toUByte()

            for (k in 0 until 8) {
                _crc = if (_crc and 0x80u.toUByte() != 0u.toUByte()) {
                    (_crc.toUInt() shl 1 xor 0x31u.toUInt()).toUByte()
                } else {
                    _crc.toUInt().shl(1).toUByte()
                }
            }
        }
        return _crc
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private val channelOutCommand = Channel<String>(1000000) //Готовые команды из пакета

    data class CliCommand(var name: String, var cb: (List<String>) -> Unit)

    //Перевод на сет
    private val cmdList = mutableListOf<CliCommand>() //Список команд


    private suspend fun cliDecoder() {
        while (true) {
            val s = channelOutCommand.receive()
            parse(s)
        }
    }

    private fun parse(str: String) {
        if (str.isEmpty()) return
        val l = str.split(' ').toMutableList()
        val name = l.first()
        l.removeFirst()
        val arg: List<String> = l.filter { it.isNotEmpty() }
        try {
            val command: CliCommand = cmdList.first { it.name == name }
            command.cb.invoke(arg)
        } catch (e: Exception) {
            Timber.e("CLI отсутствует команда $name")
        }

    }


}