package com.ncs.ncstakeawaytask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ncs.ncstakeawaytask.databinding.EachItemBinding
import kotlin.random.Random

class Adapter(
    private val dataList: MutableList<User>,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.initials.text = dataList[position].name.substring(0, 1)
        holder.binding.name.text = dataList[position].name
        holder.binding.email.text = dataList[position].email
        holder.binding.phNum.text = dataList[position].phoneNum
    }

    fun filterList(filteredList: List<User>) {
        dataList.clear()
        dataList.addAll(filteredList)
        notifyDataSetChanged()
    }


    inner class ViewHolder(val binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}