package com.rezapour.listofpeople.plugins.users_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.avatarfirst.avatargenlib.AvatarConstants
import com.avatarfirst.avatargenlib.AvatarGenerator
import com.bumptech.glide.Glide
import com.rezapour.listofpeople.R
import com.rezapour.listofpeople.databinding.RowUserListBinding
import com.rezapour.listofpeople.models.CustomersDomain
import com.rezapour.listofpeople.util.ImageUtil

class UserListAdapter(
    val listItem: ArrayList<CustomersDomain>,
    val onClick: (userId: Int) -> Unit
) :
    RecyclerView.Adapter<UserListAdapter.UserRowViewHolder>() {
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

    inner class UserRowViewHolder(private val binding: RowUserListBinding) :
        ViewHolder(binding.root) {
        fun onBind(customer: CustomersDomain) {
            with(binding) {
                userListRowName.text = customer.fullName
                userListRowGender.text = customer.gender
                userListRowPhone.text = "+${customer.phoneNumber.replace("-", " ")}"


                Glide.with(itemView.context)
                    .load(customer.imageUrl)
                    .error(ImageUtil.getAvatar(binding.root.context, customer.firstName))
                    .circleCrop()
                    .into(userListRowImage)
                binding.userListRowLayout.setOnClickListener { onClick(customer.id) }
            }
            customer.stickers.forEach { stiker ->
                when (stiker) {
                    "Fam" -> binding.userListRowLabelFam.visibility = VISIBLE
                    "Ban" -> binding.userListRowLabelBan.visibility = VISIBLE
                }
            }
        }
    }
}

