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
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardViewSick = view.findViewById<CardView>(R.id.cardViewSick)
        cardViewSick.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profileSickFragment)
        }

        val imageViewProfile = view.findViewById<ImageView>(R.id.imageViewProfile)
        val textViewProfile = view.findViewById<TextView>(R.id.textViewProfileMenu)
        val linearLayoutTasks = view.findViewById<LinearLayout>(R.id.linearLayoutTasks)
        val linearLayoutChat = view.findViewById<LinearLayout>(R.id.linearLayoutChat)
        val linearLayoutChart = view.findViewById<LinearLayout>(R.id.linearLayoutChart)

        imageViewProfile.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
        imageViewProfile.setImageResource(R.drawable.profile_dark)
        textViewProfile.setTextColor(Color.parseColor("#2B2D35"))

        linearLayoutTasks.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.tasksFragment)
        }

        linearLayoutChart.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.chartsFragment)
        }

        linearLayoutChat.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.chatsFragment)
        }

    }
}