package com.nixstudio.githubuser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private val listUser : ArrayList<User>, private val context : Context) : RecyclerView.Adapter<UserAdapter.CardViewViewHolder>() {

    private lateinit var onItemClickCallback : OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data : User)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class CardViewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto : CircleImageView = itemView.findViewById(R.id.img_user_photo)
        var tvName : TextView = itemView.findViewById(R.id.tv_user_name)
        var tvUsername : TextView = itemView.findViewById(R.id.tv_user_username)
        var tvCompany : TextView = itemView.findViewById(R.id.tv_user_company)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_user, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CardViewViewHolder,
        position: Int
    ) {
        val user = listUser[position]

        val userPhotoId = context.resources.getIdentifier(user.avatar, "drawable", context.packageName)

        Glide.with(holder.itemView.context)
            .load(userPhotoId)
            .apply(RequestOptions().override(550, 550))
            .into(holder.imgPhoto)

        holder.tvName.text = user.name
        holder.tvUsername.text = user.username
        holder.tvCompany.text = user.company

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}