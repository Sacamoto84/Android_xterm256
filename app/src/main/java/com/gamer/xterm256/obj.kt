package com.gamer.xterm256

import kotlinx.coroutines.channels.Channel

data class NetCommand(var cmd : String, var newString : Boolean =  false)

//Канал передачи
val channelNetworkIn = Channel<String>(Channel.UNLIMITED)

val channelLastString = Channel<NetCommand>(Channel.UNLIMITED)

val decoder = NetCommandDecoder(channelNetworkIn, channelLastString)
