package com.example.bia_tests

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

class TasksFragment : Fragment() {

    private lateinit var recyclerViewNewTasks: RecyclerView
    private lateinit var recyclerViewHistoryTasks: RecyclerView
    private lateinit var newTasksAdapter: NewTasksAdapter
    private lateinit var historyTasksAdapter: HistoryTasksAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewNewTasks = view.findViewById<RecyclerView>(R.id.recyclerViewNewTasks)
        recyclerViewHistoryTasks = view.findViewById<RecyclerView>(R.id.recyclerViewNHistoryTasks)
        recyclerViewNewTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewHistoryTasks.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewNewTasks.setHasFixedSize(true)
        recyclerViewHistoryTasks.setHasFixedSize(true)
        newTasksAdapter = NewTasksAdapter()
        historyTasksAdapter = HistoryTasksAdapter()

        recyclerViewNewTasks.adapter = newTasksAdapter
        recyclerViewHistoryTasks.adapter = historyTasksAdapter

        val lisOfNewTasks = listOf<Task>(
            Task("1", "Сборка", "08.10.23·(9:00 - 21:00)", "Москва-Север-1", "Дневная смена", "Сборщик"),
            Task("2", "Сборка", "09.10.23·(21:00 - 9:00)", "Москва-Юг-1", "Ночная смена", "Сборщик"),
            Task("3", "Сборка", "10.10.23·(9:00 - 21:00)", "Москва-Юг-3", "Дневная смена", "Сборщик"),
            Task("4", "Сборка", "11.10.23·(21:00 - 9:00)", "Москва-Север-2", "Ночная смена", "Сборщик"),
            Task("5", "Сборка", "15.10.23·(9:00 - 21:00)", "Москва-Восток-1", "Дневная смена", "Сборщик"))

        val lisOfHistoryTasks = listOf<Task>(
            Task("1", "Сборка", "08.10.23·(9:00 - 21:00)", "Москва-Север-1", "Дневная смена", "Сборщик"),
            Task("2", "Сборка", "09.10.23·(21:00 - 9:00)", "Москва-Юг-1", "Ночная смена", "Сборщик"),
            Task("3", "Сборка", "10.10.23·(9:00 - 21:00)", "Москва-Юг-3", "Дневная смена", "Сборщик"),
            Task("4", "Сборка", "11.10.23·(21:00 - 9:00)", "Москва-Север-2", "Ночная смена", "Сборщик"),
            Task("5", "Сборка", "15.10.23·(9:00 - 21:00)", "Москва-Восток-1", "Дневная смена", "Сборщик"))

        newTasksAdapter.submitList(lisOfNewTasks)
        historyTasksAdapter.submitList(lisOfHistoryTasks)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    if(tab.position == 1) {
                        recyclerViewNewTasks.visibility = View.GONE
                        recyclerViewHistoryTasks.visibility = View.VISIBLE
                    } else if (tab.position == 0) {
                        recyclerViewNewTasks.visibility = View.VISIBLE
                        recyclerViewHistoryTasks.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })


        // Слишком много кода, можно и в отдельный метод вынести
        val imageViewTasks = view.findViewById<ImageView>(R.id.imageViewTask)
        val imageViewChart = view.findViewById<ImageView>(R.id.imageViewChart)
        val imageViewChat = view.findViewById<ImageView>(R.id.imageViewChat)
        val imageViewProfile = view.findViewById<ImageView>(R.id.imageViewProfile)
        val textViewTasks = view.findViewById<TextView>(R.id.textViewTaskMenu)
        val textViewChart = view.findViewById<TextView>(R.id.textViewChartMenu)
        val textViewChat = view.findViewById<TextView>(R.id.textViewChatMenu)
        val textViewProfile = view.findViewById<TextView>(R.id.textViewProfileMenu)
        val linearLayoutTasks = view.findViewById<LinearLayout>(R.id.linearLayoutTasks)
        val linearLayoutChat = view.findViewById<LinearLayout>(R.id.linearLayoutChat)
        val linearLayoutChart = view.findViewById<LinearLayout>(R.id.linearLayoutChart)
        val linearLayoutProfile = view.findViewById<LinearLayout>(R.id.linearLayoutProfile)

        linearLayoutTasks.setOnClickListener {
            textViewTasks.setTextColor(Color.parseColor("#2B2D35"))
            textViewChart.setTextColor(Color.parseColor("#7A7B85"))
            textViewChat.setTextColor(Color.parseColor("#7A7B85"))
            textViewProfile.setTextColor(Color.parseColor("#7A7B85"))
            imageViewTasks.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
            imageViewTasks.setImageResource(R.drawable.task_dark)
            imageViewChat.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChat.setImageResource(R.drawable.chat)
            imageViewChart.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChart.setImageResource(R.drawable.chart)
            imageViewProfile.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewProfile.setImageResource(R.drawable.profile)
        }

        linearLayoutChart.setOnClickListener {
            textViewTasks.setTextColor(Color.parseColor("#7A7B85"))
            textViewChat.setTextColor(Color.parseColor("#7A7B85"))
            textViewChart.setTextColor(Color.parseColor("#2B2D35"))
            textViewProfile.setTextColor(Color.parseColor("#7A7B85"))
            imageViewTasks.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewTasks.setImageResource(R.drawable.task)
            imageViewChat.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChat.setImageResource(R.drawable.chat)
            imageViewChart.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
            imageViewChart.setImageResource(R.drawable.chart_dark)
            imageViewProfile.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewProfile.setImageResource(R.drawable.profile)
            //Navigation.findNavController(view).navigate(R.id.chartFragment) - Фрагмент с графиками не создан
        }

        linearLayoutChat.setOnClickListener {
            textViewTasks.setTextColor(Color.parseColor("#7A7B85"))
            textViewChart.setTextColor(Color.parseColor("#7A7B85"))
            textViewChat.setTextColor(Color.parseColor("#2B2D35"))
            textViewProfile.setTextColor(Color.parseColor("#7A7B85"))
            imageViewTasks.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewTasks.setImageResource(R.drawable.task)
            imageViewChat.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
            imageViewChat.setImageResource(R.drawable.chat_dark)
            imageViewChart.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChart.setImageResource(R.drawable.chart)
            imageViewProfile.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewProfile.setImageResource(R.drawable.profile)
            // Navigation.findNavController(view).navigate(R.id.chatsFragment) - Фрагмент с чатами не создан
        }

       linearLayoutProfile.setOnClickListener {
            textViewTasks.setTextColor(Color.parseColor("#7A7B85"))
            textViewChart.setTextColor(Color.parseColor("#7A7B85"))
            textViewChart.setTextColor(Color.parseColor("#7A7B85"))
            textViewProfile.setTextColor(Color.parseColor("#2B2D35"))
            imageViewTasks.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewTasks.setImageResource(R.drawable.task)
            imageViewChat.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChat.setImageResource(R.drawable.chat)
            imageViewChart.setBackgroundResource(R.drawable.frame_white_fill)
            imageViewChart.setImageResource(R.drawable.chart)
            imageViewProfile.setBackgroundResource(R.drawable.frame_ligth_gray_blue_fill)
            imageViewProfile.setImageResource(R.drawable.profile_dark)
            Navigation.findNavController(view).navigate(R.id.profileFragment)
        }
    }

}