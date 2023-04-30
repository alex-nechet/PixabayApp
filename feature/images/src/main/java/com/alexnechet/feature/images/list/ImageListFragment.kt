package com.alexnechet.feature.images.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexnechet.feature.images.databinding.FragmentImagesListBinding
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageListFragment : Fragment() {

    private val viewModel: ImageListViewModel by viewModel()

    private var _binding: FragmentImagesListBinding? = null
    private val binding: FragmentImagesListBinding
        get() = _binding!!

    private val imagesAdapter = ImagesListAdapter { }
    private val loadStateFooter = ImagesListLoadAdapter { imagesAdapter.retry() }
    private val adapter = imagesAdapter.withLoadStateFooter(
        footer = loadStateFooter
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchEdittext.doAfterTextChanged { text ->
            val searchText = text.toString().trim()
            viewModel.search(searchText)
        }
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter
        imagesAdapter.addLoadStateListener {
            loadStateFooter.loadState = it.refresh
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is ImageListState.RequestState -> viewModel.getImages(state.request)
                    is ImageListState.ResponseState -> {

                        imagesAdapter.submitData(state.data)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}