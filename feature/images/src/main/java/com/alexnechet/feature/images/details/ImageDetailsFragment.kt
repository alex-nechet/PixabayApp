package com.alexnechet.feature.images.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.alexnechet.domain.images.model.ImageInfo
import com.alexnechet.feature.common.isVisible
import com.alexnechet.feature.common.loadImage
import com.alexnechet.feature.images.databinding.FragmentImageDetailsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ImageDetailsFragment : Fragment() {
    private val args: ImageDetailsFragmentArgs by navArgs()
    private val viewModel: ImageDetailsViewModel by viewModel { parametersOf(args.IdImageId) }

    private var _binding: FragmentImageDetailsBinding? = null
    private val binding: FragmentImageDetailsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { state ->
                    handleUIChanges(state)
                    when (state) {
                        is ImageDetailsUIState.Error -> handleError(state.message)
                        is ImageDetailsUIState.Success -> handleResult(state.data)
                        ImageDetailsUIState.Loading -> {
                            //no-op
                        }
                    }
                }
        }
    }

    private fun handleUIChanges(state: ImageDetailsUIState) {
        with(binding) {
            progress?.isVisible(state is ImageDetailsUIState.Loading)
            errorText?.isVisible(state is ImageDetailsUIState.Error)
            content?.isVisible(state is ImageDetailsUIState.Success)
        }
    }

    private fun handleError(message: String) {
        binding.errorText?.text = message
    }

    private fun handleResult(data: ImageInfo) {
        with(binding) {
            userImage?.loadImage(
                context = requireContext(),
                url = data.basicInfo.userImageUrl,
                isCircleShape = true
            )

            title?.text = data.basicInfo.userName
            tagsList?.text = data.basicInfo.tags
            image.loadImage(context = requireContext(), url = data.largeImageUrl) {
                imageLoadingProgress.isVisible(it)
            }

            likes?.text = data.likes
            comments?.text = data.comments
            downloads?.text = data.downloads
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}