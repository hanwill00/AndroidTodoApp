package com.example.todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    var todoItems = mutableListOf<Todo>(
        Todo("11/12/2020", false, "Todo1"),
        Todo("11/11/2020", true, "Todo2")
    )

    val context: Context

    constructor(context: Context){
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.todo_row, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    public fun addTodo(todo: Todo){
        todoItems.add(todo)
        notifyItemInserted(todoItems.lastIndex)
    }


    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val currentTodo = todoItems[position]
        holder.tvDate.text = currentTodo.createDate
        holder.cbDone.isChecked = currentTodo.done
        holder.cbDone.text = currentTodo.todoText
        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }
    }

    private fun deleteTodo(position: Int){
        todoItems.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvDate = itemView.tvDate
        val cbDone = itemView.cbDone
        val btnDelete = itemView.btnDelete
    }

}