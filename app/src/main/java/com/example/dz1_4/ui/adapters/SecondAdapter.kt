package com.example.dz1_4.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dz1_4.databinding.ItemSecondBinding
import com.example.dz1_4.ui.model.MainModel

class SecondAdapter(
    private var mainList: ArrayList<MainModel>,
    private val onClickListener: (name: MainModel) -> Unit
) : RecyclerView.Adapter<SecondAdapter.SecondViewHolder>() {

    inner class SecondViewHolder(private val binding: ItemSecondBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClickListener(mainList[bindingAdapterPosition])
            }

        }
        fun onBind(mainModel: MainModel) {
            Glide.with(binding.ivIcon.context)
                .load(mainModel.image)
                .into(binding.ivIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val binding = ItemSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SecondViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.onBind(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }
}