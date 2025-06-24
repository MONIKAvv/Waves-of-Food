package vv.monika.wavesoffood.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vv.monika.wavesoffood.NotificationBottomSheetFragment
import vv.monika.wavesoffood.databinding.NotificationItemBinding

class NotificationAdapter (private val notification: ArrayList<String>, private val notificationImage:ArrayList<Int>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> (){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotificationItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
       holder.bind(notification[position], notificationImage[position])
    }

    override fun getItemCount(): Int {
       return notification.size
    }
    inner class NotificationViewHolder (private val binding: NotificationItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(notificationDescription: String, notificationEmoji: Int) {
            binding.notificationTextView.text = notificationDescription
            binding.notificationImageView.setImageResource(notificationEmoji)
//            we need to call this adapter from notificationBottomSheet

        }

    }
}