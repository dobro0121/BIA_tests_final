package com.example.bia_tests

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ChatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewChat = view.findViewById<ImageView>(R.id.imageViewChat)
        val textViewChat = view.findViewById<TextView>(R.id.textViewChatMenu)
        val linearLayoutTasks = view.findViewById<LinearLayout>(R.id.linearLayoutTasks)
        val linearLayoutChart = view.findViewById<LinearLayout>(R.id.linearLayoutChart)
        val linearLayoutProfile = view.findViewById<LinearLayout>(R.id.linearLayoutProfile)

        textViewChat.setTextColor(Color.parseColor("#2B2D35"))
        imageViewChat.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
        imageViewChat.setImageResource(R.drawable.chat_dark)

        linearLayoutTasks.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.tasksFragment)
        }

        linearLayoutChart.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.chartsFragment)
        }

        linearLayoutProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profileFragment)
        }
    }
}