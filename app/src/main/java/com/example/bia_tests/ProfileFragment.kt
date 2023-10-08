package com.example.bia_tests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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

        val linearLayoutTasks = view.findViewById<LinearLayout>(R.id.linearLayoutTasks)
        val linearLayoutChat = view.findViewById<LinearLayout>(R.id.linearLayoutChat)
        val linearLayoutChart = view.findViewById<LinearLayout>(R.id.linearLayoutChart)

        linearLayoutTasks.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.tasksFragment)
        }

        linearLayoutChat.setOnClickListener {
            // Navigation.findNavController(view).navigate(R.id.chatsFragment) - Фрагмент с чатами не создан
        }

        linearLayoutChart.setOnClickListener {
            //Navigation.findNavController(view).navigate(R.id.chartFragment) - Фрагмент с графиками не создан
        }
    }
}