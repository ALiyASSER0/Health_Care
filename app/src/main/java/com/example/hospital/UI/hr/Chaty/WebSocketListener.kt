import android.util.Log
import com.example.chatapp.Chat
import com.google.gson.Gson
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import org.json.JSONObject
import java.net.URI

class MyWebSocketClient(serverUri: URI) : WebSocketClient(serverUri) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.e("TAG", "new connection opened")
    }

    override fun onMessage(message: String) {
        val gson = Gson()
        val data = JSONObject(message)
        Log.d("TAG", "onMessage:${data} ")
        val chatFromJson = gson.fromJson(message, Chat::class.java)
        Log.d("Ali", "Here: ${chatFromJson.message}")
        if (data.getString("type") == "01013455141") {
            // Handle your message here
            Log.d("WebSocket", "Message: ${data.getString("message")}")
        }

    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.e("TAG", "closed with exit code $code and reason $reason")
    }
    fun sendMessage(message: String) {
        if (isOpen) {
            Log.e("TAG", "sendMessage:${message} ", )
            send(message)
        }
    }

    override fun onError(ex: Exception?) {
        Log.e("TAG", "an error occurred: $ex")
    }


}
