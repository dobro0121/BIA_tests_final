package com.example.bia_tests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HistoryTasksAdapter: ListAdapter<Task, HistoryTasksAdapter.OrderViewHolder>(HistoryTasksAdapter.ItemDiffUtil()) {
    class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewAssembly = itemView.findViewById<TextView>(R.id.textViewAssembly)
        private val textViewDateTime = itemView.findViewById<TextView>(R.id.textViewDateTime)
        private val textViewStoreValue = itemView.findViewById<TextView>(R.id.textViewStoreValue)
        private val textViewShiftValue = itemView.findViewById<TextView>(R.id.textViewShiftValue)
        private val textViewSpecialityValue = itemView.findViewById<TextView>(R.id.textViewSpecialityValue)
        private val buttonChat = itemView.findViewById<Button>(R.id.buttonChat)
        private val textViewWorkDecline = itemView.findViewById<TextView>(R.id.textViewWorkDecline)
        private val textViewWorkExcluded = itemView.findViewById<TextView>(R.id.textViewWorkExcluded)

        fun onBind(task:Task) {
            textViewAssembly.text = task.typeOfTask
            textViewDateTime.text = task.dateTime
            textViewStoreValue.text = task.store
            textViewShiftValue.text = task.shift
            textViewSpecialityValue.text = task.speciality
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history_task, parent, false)
        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = currentList[position]
        holder.onBind(order)
    }

    class ItemDiffUtil: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem
        }
    }
}