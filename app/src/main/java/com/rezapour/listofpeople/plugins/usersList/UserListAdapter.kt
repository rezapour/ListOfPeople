package com.rezapour.listofpeople.plugins.usersList

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rezapour.listofpeople.R
import com.rezapour.listofpeople.databinding.RowUserListBinding
import com.rezapour.listofpeople.models.CustomersDomain

class UserListAdapter(val listItem: ArrayList<CustomersDomain>) :
    RecyclerView.Adapter<UserRowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRowViewHolder {
        val binding = RowUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserRowViewHolder(binding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: UserRowViewHolder, position: Int) {
        holder.onBind(listItem[position])
    }

    fun addItem(items: List<CustomersDomain>) {
        listItem.clear()
        listItem.addAll(items)
        notifyDataSetChanged()
    }
}

class UserRowViewHolder(private val binding: RowUserListBinding) : ViewHolder(binding.root) {
    fun onBind(customer: CustomersDomain) {
        with(binding) {
            userListRowName.text = "${customer.firstName} ${customer.lastName}"
            userListRowGender.text = customer.gender
            userListRowPhone.text = "+${customer.phoneNumber.replace("-", " ")}"

            Glide.with(itemView.context)
                .load(customer.imageUrl)
                .error(R.drawable.baseline_error_24)
                .circleCrop()
                .into(userListRowImage)
        }
        customer.stickers.forEach { stiker ->
            when (stiker) {
                "Fam" -> binding.userListRowLabelFam.visibility = VISIBLE
                "Ban" -> binding.userListRowLabelBan.visibility = VISIBLE
            }
        }
    }
}