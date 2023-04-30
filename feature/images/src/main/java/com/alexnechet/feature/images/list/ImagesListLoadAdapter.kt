package com.alexnechet.feature.images.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alexnechet.feature.images.databinding.ItemLoadStateBinding

class ImagesListLoadAdapter(
    private val retryAction: () -> Unit
) : LoadStateAdapter<ImagesListLoadAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState, retryAction)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val binding = ItemLoadStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(
        private val binding: ItemLoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState, retryAction: () -> Unit) {
            with(binding) {
                retry.isVisible = loadState is LoadState.Error
                errorMessage.isVisible = loadState is LoadState.Error
                progress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                    errorMessage.text = loadState.error.localizedMessage
                    retry.setOnClickListener { retryAction() }
                }
            }
        }
    }
}
