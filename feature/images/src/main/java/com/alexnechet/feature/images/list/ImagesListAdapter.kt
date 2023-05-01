package com.alexnechet.feature.images.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.feature.common.loadImage
import com.alexnechet.feature.images.R
import com.alexnechet.feature.images.databinding.ItemListBinding

class ImagesListAdapter(
    private val action: (imageId: Long) -> Unit
) : PagingDataAdapter<BasicImageInfo, ImagesListAdapter.UserViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        val binding = ItemListBinding.bind(view)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val info = getItem(position) ?: return
        holder.bind(info, action)
    }

    class UserViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: BasicImageInfo, action: (imageId: Long) -> Unit) {
            with(binding) {
                title.text = image.userName
                userImage.loadImage(userImage.context, image.userImageUrl, true)
                postImage.loadImage(postImage.context, image.previewUrl)
                tagsList.text = image.tags
            }

            itemView.setOnClickListener {
                // Triggers click upwards to the adapter on click
                if (layoutPosition != RecyclerView.NO_POSITION) {
                    action.invoke(image.id)
                }
            }

        }
    }

    class ImagesDiffCallback : DiffUtil.ItemCallback<BasicImageInfo>() {
        override fun areItemsTheSame(oldItem: BasicImageInfo, newItem: BasicImageInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BasicImageInfo, newItem: BasicImageInfo): Boolean {
            return oldItem == newItem
        }
    }
}
