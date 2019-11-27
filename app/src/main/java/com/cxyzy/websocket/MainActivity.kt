package com.cxyzy.websocket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), MessageListener {
    private val serverUrl = "ws://192.168.18.145:8086/socketServer/abc"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebSocketManager.init(serverUrl, this)
        connectBtn.setOnClickListener {
            thread {
                kotlin.run {
                    WebSocketManager.connect()
                }
            }
        }
        clientSendBtn.setOnClickListener {
            if (WebSocketManager.sendMessage("客户端发送")) {
                addText("客户端发送\n")
            }
        }
        closeConnectionBtn.setOnClickListener {
            WebSocketManager.close()
        }
    }

    override fun onConnectSuccess() {
        addText("连接成功\n")
    }

    override fun onConnectFailed() {
        addText("连接失败\n")
    }

    override fun onClose() {
        addText("关闭成功\n")
    }

    override fun onMessage(text: String?) {
        addText("接收消息：$text\n")
    }

    private fun addText(text: String?) {
        runOnUiThread {
            contentEt.text.append(text)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        WebSocketManager.close()
    }
}
