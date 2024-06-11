package com.example.hospital.UI.hr.Chaty

import MyWebSocketClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.Chat
import com.example.chatapp.ChatAdapter
import com.example.hospital.Const
import com.example.hospital.databinding.ActivityChatyBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import org.java_websocket.exceptions.WebsocketNotConnectedException
import org.json.JSONObject
import java.net.URI


@AndroidEntryPoint
class Chaty : AppCompatActivity() {
    private var _binding:ActivityChatyBinding?=null
    private val binding get() = _binding!!
    private lateinit var chatAdapter: ChatAdapter
    private  val chatyViewModel: ChatyViewModel by viewModels()
    private val okHttpClient=OkHttpClient()
    private val chatList = mutableListOf<Chat>()
  //  private var webSocket:WebSocket?=null
  private var client:MyWebSocketClient?= null
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityChatyBinding.inflate(layoutInflater)
        setContentView(binding.root)

//       webSocketListener= WebSocketListener(chatyViewModel)

//        chatyViewModel.socketStatus.observe(this){
//            binding.tvMessage.text=if(it) "Connect" else "DisConnect"
//        }
      var text=""

//        chatyViewModel.message.observe(this){
//            text+="${if(it.first) "You: " else "Other: "} ${it.second} \n"
//        binding.tvMessage.text=text
//        }
binding.createRoom.setOnClickListener {
    chatyViewModel.createRoom()
}

        chatyViewModel.liveData.observe(this){
           val roomName=it.data.room_name
        }
        binding.connect.setOnClickListener {
             client = MyWebSocketClient(URI("wss://gp-mvz0.onrender.com/chat/01013455141-01013455143/?token=zIiwidXNlcl9pZCI6NTd9.ZwPvIH1zg2dLGC22x6wWDccC99bEWOlfoY_1kyx1urY"))
            client?.connect()

        }

        binding.disconnect.setOnClickListener {
            Log.e("TAG", "berrrr: ")
            client?.close()
        }

        binding.sent.setOnClickListener {
            if(binding.editTextText.text.toString().trim().isNotEmpty()){
                try {
                    // Assuming 'webSocketClient' is your WebSocket client instance
                    if (client?.isOpen == true) {
                        val chat = Chat(
                            type = "send_message",
                            message = binding.editTextText.text.toString()
                        )
                        val gson = Gson()
                        val jsonString = gson.toJson(chat)
                        val jsonObject=JSONObject()
                        jsonObject.put("type","send_message")
                        jsonObject.put("message",binding.editTextText.text.toString())
                        client!!.sendMessage(jsonString)

                        jsonObject.put("isSend",true)
                    }
                } catch (e: WebsocketNotConnectedException) {
                    // Handle the exception, maybe try to reconnect
                    Log.e("TAG", " e.printStackTrace(): ${ e.printStackTrace()}", )
                }





            }else{
                Toast.makeText(this,"Enter something here...",Toast.LENGTH_SHORT).show()
            }
        }

        binding.clear.setOnClickListener {
            binding.tvMessage.text=""
        }

    }


    private fun createRequest(roomName: String): Request {
        Log.e("TAG", "createRequest:01101703640-01013455143 ")
        val baseWebSocketUrl = "ws://gp-mvz0.onrender.com/chat/01013455143-01013455141/"

        return Request.Builder().url(baseWebSocketUrl).build()
    }

}
