package com.gamer.xterm256

import androidx.compose.ui.graphics.Color
import com.gamer.xterm256.screen.lazy.PairTextAndColor
import timber.log.Timber

val allColor = mutableListOf<Color>() //Палитра цветов по коду соответствие цвета

val listSortedColor = listOf(
    16, 22, 28, 34, 40, 46, 82, 76, 70, 64, 58, 52,
    17, 23, 29, 35, 41, 47, 83, 77, 71, 65, 59, 53,
    18, 24, 30, 36, 42, 48, 84, 78, 72, 66, 60, 54,
    19, 25, 31, 37, 43, 49, 85, 79, 73, 67, 61, 55,
    20, 26, 32, 38, 44, 50, 86, 80, 74, 68, 62, 56,
    21, 27, 33, 39, 45, 51, 87, 81, 75, 69, 63, 57,

    93, 99,105,111,117,123,159,153,147,141,135,129,
    92, 98,104,110,116,122,158,152,146,140,134,128,
    91, 97,103,109,115,121,157,151,145,139,133,127,
    90, 96,102,108,114,120,156,150,144,138,132,126,
    89, 95,101,107,113,119,155,149,143,137,131,125,
    88, 94,100,106,112,118,154,148,142,136,130,124,

    160,166,172,178,184,190,226,220,214,208,202,196,
    161,167,173,179,185,191,227,221,215,209,203,197,
    162,168,174,180,186,192,228,222,216,210,204,198,
    163,169,175,181,187,193,229,223,217,211,205,199,
    164,170,176,182,188,194,230,224,218,212,206,200,
    165,171,177,183,189,195,231,225,219,213,207,201,

    232,233,234,235,236,237,238,239,240,241,242,243,
    244,245,246,247,248,249,250,252,253,253,254,255
)

val defaultTextColor: Color = Color.White
val defaultBgColor: Color = Color.Black

data class CurrentColor(
    var color: Color = Color.White,
    var bgColor: Color = Color.Black,
    var bold: Boolean = false,
    var italic: Boolean = false,
    var underline: Boolean = false,
    var flash: Boolean = false   //Мигание
)

var сurrentColor = CurrentColor()
var symbolColor = CurrentColor()
var tempColor = CurrentColor()

//Получаем строку
fun stringcalculate(text: String): List<PairTextAndColor> {
    var str = text
    val listPair = mutableListOf<PairTextAndColor>()

    //Ищем есть ли знак ESC в строке
    val indexESC = str.indexOf('\u001b')

    //Ничего не нашли
    if (indexESC == -1) {
        listPair.add(
            PairTextAndColor(
                text = str,
                colorText = сurrentColor.color,
                colorBg = сurrentColor.bgColor,
                bold = сurrentColor.bold,
                italic = сurrentColor.italic,
                underline = сurrentColor.underline,
                flash = сurrentColor.flash
            )
        )
        return listPair
    } else { //Начало строки
        if (indexESC == 0) {

            if (str.indexOf('m') == -1) //Поиск m в конце если ее нет
            { //Нет хвоста должен быть в следующем пакете
                listPair.add(
                    PairTextAndColor(
                        text = str,
                        colorText = сurrentColor.color,
                        colorBg = сurrentColor.bgColor,
                        bold = сurrentColor.bold,
                        italic = сurrentColor.italic,
                        underline = сurrentColor.underline,
                        flash = сurrentColor.flash
                    )
                )
            } else

                do { // подстрока до первого указанного разделителя
                    val strESC =
                            str.substringBefore('m') // все что до m //Из последовательности обновляем текущие цвета //"38;05;232;48;05;226"
                    calculateColorInEscString(strESC.substring(2)) //Без первых двух символов
                    str = str.removePrefix(strESC + "m") //Удаляем префикс ESC
                    val subsring = str.substringBefore('\u001b')

                    listPair.add(
                        PairTextAndColor(
                            text = subsring,
                            colorText = сurrentColor.color,
                            colorBg = сurrentColor.bgColor,
                            bold = сurrentColor.bold,
                            italic = сurrentColor.italic,
                            underline = сurrentColor.underline,
                            flash = сurrentColor.flash
                        )
                    )
                    str = str.removePrefix(subsring) //Удаляем из исходной строки эту подстроку
                } while (str.indexOf('\u001b') != -1)
        }
    }
    return listPair
}

//Из последовательности обновляем текущие цвета
fun calculateColorInEscString(str: String) { //"38;05;232;48;05;226"
    //println("--->calculateColorInString->str:$str")
    val rederxTextColor = """38;05;([^;]+)""".toRegex()
    val rederxBgColor = """48;05;([^;]+)""".toRegex()

    //Для символов
    val rederxSymbolTextColor = """39;05;([^;]+)""".toRegex()
    val rederxSymbolBgColor = """49;05;([^;]+)""".toRegex()

    if (str == "0") {
        сurrentColor.color = defaultTextColor
        сurrentColor.bgColor = defaultBgColor
        сurrentColor.bold = false
        сurrentColor.italic = false
        сurrentColor.underline = false
        сurrentColor.flash = false
        return
    }

    if (str == "1") {
        console.messages.clear() // removeRange(0, colorline_and_text.lastIndex)
        console.print(" ")
        return
    }

    //Очистка для символов ESC[2m
    if (str == "2") {
        сurrentColor.color = tempColor.color
        сurrentColor.bgColor = tempColor.bgColor
        сurrentColor.bold = tempColor.bold
        return
    }

    var str1 = str
    var matchResult: MatchResult?

    //Цвет текста
    try {
        matchResult = rederxTextColor.find(str)
        if (matchResult != null) {
            val color = ColorPalette.color[matchResult.groupValues[1].toInt()] //Получили цвет по коду
            сurrentColor.color = color //println("--->calculateColorInString->codeColor:${color}")
            str1 = str.replace(matchResult.value, "")
        }
    } catch (e: Exception) {
        Timber.e("Ошибка блока 1")
    }

    try { //Цвет Фона
        matchResult = rederxBgColor.find(str1)
        if (matchResult != null) {
            val color = ColorPalette.color[matchResult.groupValues[1].toInt()] //Получили цвет по коду
            сurrentColor.bgColor =
                    color //println("--->calculateColorInString->currentBgColor:${color}")
            str1 = str1.replace(matchResult.value, "")
        }
    } catch (e: Exception) {
        Timber.tag("calculateColorInEsc").e("Ошибка блока 1")
    }

    //MARK: Bold
    if (str1.indexOf("01") != -1) сurrentColor.bold = true

    //MARK:Italic
    if (str1.indexOf("03") != -1) сurrentColor.italic = true

    //MARK: Uderline
    if (str1.indexOf("04") != -1) сurrentColor.underline = true

    //MARK: Reverse
    if (str1.indexOf("07") != -1) {
        val temp = сurrentColor.bgColor
        сurrentColor.bgColor = сurrentColor.color
        сurrentColor.color = temp
    }

    //MARK: Flash
    if (str1.indexOf("08") != -1) сurrentColor.flash = true


    var str2 = str //Цвет текста для символов
    try {
        matchResult = rederxSymbolTextColor.find(str)
        if (matchResult != null) {
            val color = ColorPalette.color[matchResult.groupValues[1].toInt()] //Получили цвет по коду
            tempColor.color = сurrentColor.color
            сurrentColor.color = color //println("--->calculateColorInString->codeColor:${color}")
            str2 = str.replace(matchResult.value, "")
        }
    } catch (e: Exception) {
        Timber.e("Ошибка блока 1")
    }

    try { //Цвет Фона
        matchResult = rederxSymbolBgColor.find(str2)
        if (matchResult != null) {
            val color = ColorPalette.color[matchResult.groupValues[1].toInt()] //Получили цвет по коду
            tempColor.bgColor = сurrentColor.bgColor
            сurrentColor.bgColor =
                    color //println("--->calculateColorInString->currentBgColor:${color}")
            str2 = str2.replace(matchResult.value, "")
        }
    } catch (e: Exception) {
        Timber.tag("calculateColorInEsc").e("Ошибка блока 1")
    }

    //    //MARK: Bold
    //    if (str2.indexOf("01") != -1)
    //    {
    //        tempColor.bold = сurrentColor.bold
    //        сurrentColor.bold = true
    //    }


}
