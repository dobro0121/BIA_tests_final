package com.example.bia_tests

import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NewTasksAdapter: ListAdapter<Task, NewTasksAdapter.OrderViewHolder>(ItemDiffUtil())  {
    class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textViewAssembly = itemView.findViewById<TextView>(R.id.textViewAssembly)
        private val textViewDateTime = itemView.findViewById<TextView>(R.id.textViewDateTime)
        private val textViewStoreValue = itemView.findViewById<TextView>(R.id.textViewStoreValue)
        private val textViewShiftValue = itemView.findViewById<TextView>(R.id.textViewShiftValue)
        private val textViewSpecialityValue = itemView.findViewById<TextView>(R.id.textViewSpecialityValue)
        private val buttonAccept = itemView.findViewById<Button>(R.id.buttonAccept)
        private val buttonDecline = itemView.findViewById<Button>(R.id.buttonDecline)
        private val textViewWorkAccept = itemView.findViewById<TextView>(R.id.textViewWorkAccept)
        private val textViewAnswer = itemView.findViewById<TextView>(R.id.textViewAnswer)
        private val textViewWorkDecline = itemView.findViewById<TextView>(R.id.textViewWorkDecline)
        private val imageViewClose = itemView.findViewById<ImageView>(R.id.imageViewClose)

        fun onBind(task:Task) {
            textViewAssembly.text = task.typeOfTask
            textViewDateTime.text = task.dateTime
            textViewStoreValue.text = task.store
            textViewShiftValue.text = task.shift
            textViewSpecialityValue.text = task.speciality
            buttonAccept.setOnClickListener {
                buttonDecline.visibility = View.GONE
                buttonAccept.visibility = View.GONE
                textViewAnswer.visibility = View.VISIBLE
                Handler().postDelayed({
                    textViewAnswer.visibility = View.GONE
                    textViewWorkAccept.visibility = View.VISIBLE
                }, 2000)
            }
            buttonDecline.setOnClickListener {
                buttonDecline.visibility = View.GONE
                buttonAccept.visibility = View.GONE
                textViewWorkDecline.visibility = View.VISIBLE
                imageViewClose.visibility = View.VISIBLE
            }
            imageViewClose.setOnClickListener {
                //должно перемещаться в историю
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_new_task, parent, false)
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

