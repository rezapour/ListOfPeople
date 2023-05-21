package com.rezapour.listofpeople.plugins.users_list

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rezapour.listofpeople.databinding.RowUserListBinding
import com.rezapour.listofpeople.models.Customers
import com.rezapour.listofpeople.util.ImageUtil

class UserListAdapter(
    val listItem: ArrayList<Customers>,
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

    fun addItems(items: List<Customers>) {
        listItem.clear()
        listItem.addAll(items)
        notifyDataSetChanged()
    }

    inner class UserRowViewHolder(private val binding: RowUserListBinding) :
        ViewHolder(binding.root) {
        fun onBind(customer: Customers) {
            with(binding) {
                userListRowName.text = customer.fullName
                userListRowGender.text = customer.gender
                userListRowPhone.text = "+${customer.phoneNumber.replace("-", " ")}"

                ImageUtil.loadImage(
                    context = itemView.context,
                    url = customer.imageUrl,
                    error = ImageUtil.getAvatar(binding.root.context, customer.firstName),
                    imageView = userListRowImage
                )

                binding.userListRowLayout.setOnClickListener { onClick(customer.id) }
            }
            binding.userListRowLabelFam.visibility = GONE
            binding.userListRowLabelBan.visibility = GONE
            customer.stickers.forEach { stiker ->
                when (stiker) {
                    "Fam" -> binding.userListRowLabelFam.visibility = VISIBLE
                    "Ban" -> binding.userListRowLabelBan.visibility = VISIBLE
                }
            }
        }
    }
}

