package com.kotlin.retrrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.retrrofit.response.CommentResponse
import kotlinx.android.synthetic.main.item_post.view.*

class CommentAdapter(private val listComment: ArrayList<CommentResponse>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    inner class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(commentResponse: CommentResponse) {
            with(itemView) {
                val text = "postId: ${commentResponse.postId}\n" +
                        "id: ${commentResponse.id}\n" +
                        "name: ${commentResponse.name}\n" +
                        "email: ${commentResponse.email}\n" +
                        "body: ${commentResponse.body}"

                tvText.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    override fun getItemCount(): Int = listComment.size
}