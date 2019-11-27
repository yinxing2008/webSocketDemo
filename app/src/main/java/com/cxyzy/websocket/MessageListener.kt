package com.cxyzy.websocket

interface MessageListener {
    fun onConnectSuccess() // 连接成功
    fun onConnectFailed() // 连接失败
    fun onClose() // 关闭
    fun onMessage(text: String?)
}