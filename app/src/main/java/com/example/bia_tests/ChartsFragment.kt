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

class ChartsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewChart = view.findViewById<ImageView>(R.id.imageViewChart)
        val textViewChart = view.findViewById<TextView>(R.id.textViewChartMenu)
        val linearLayoutTasks = view.findViewById<LinearLayout>(R.id.linearLayoutTasks)
        val linearLayoutChat = view.findViewById<LinearLayout>(R.id.linearLayoutChat)
        val linearLayoutProfile = view.findViewById<LinearLayout>(R.id.linearLayoutProfile)

        textViewChart.setTextColor(Color.parseColor("#2B2D35"))
        imageViewChart.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
        imageViewChart.setImageResource(R.drawable.chart_dark)

        linearLayoutTasks.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.tasksFragment)
        }

        linearLayoutChat.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.chatsFragment)
        }

        linearLayoutProfile.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.profileFragment)
        }

    }
}