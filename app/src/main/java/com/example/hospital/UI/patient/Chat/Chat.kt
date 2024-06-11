package com.example.hospital.UI.patient.Chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.chatapp.Chat
import com.example.chatapp.ChatAdapter
import com.example.hospital.databinding.FragmentChatBinding
import java.util.logging.SocketHandler

class Chat : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatAdapter: ChatAdapter
//    private val args: ChatArgs by navArgs()
    private val chatList = mutableListOf<Chat>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var userName = args.name
        var userName="ali"
        if (userName.isNotEmpty()) {

            chatAdapter = ChatAdapter()

            binding.rvChat.apply {
                adapter = chatAdapter
            }

            binding.btnSend.setOnClickListener {

            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}