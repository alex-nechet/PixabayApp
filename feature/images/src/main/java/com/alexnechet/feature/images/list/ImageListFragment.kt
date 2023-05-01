package com.alexnechet.feature.images.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexnechet.feature.images.databinding.FragmentImagesListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ImageListFragment : Fragment() {
    private val args: ImageListFragmentArgs by navArgs()
    private val viewModel: ImageListViewModel by viewModel { parametersOf(args.IdInitialSearch) }


    private var _binding: FragmentImagesListBinding? = null
    private val binding: FragmentImagesListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupSearch()
        setupAdapter()
    }

    private fun setupSearch() {
        val initialSearchWord = args.IdInitialSearch
        with(binding) {
            searchEdittext.setText(initialSearchWord)
            searchEdittext.doAfterTextChanged { text ->
                val searchText = text.toString().trim()
                viewModel.search(searchText)
            }
        }
    }

    private fun setupAdapter() {
        val imagesAdapter = ImagesListAdapter { imageId ->
            val directions = ImageListFragmentDirections
                .actionImagesListFragmentToImageDetailsFragment(imageId)
            findNavController().navigate(directions)
        }

        val loadStateFooter = ImagesListLoadAdapter { imagesAdapter.retry() }
        val adapter = imagesAdapter.withLoadStateFooter(footer = loadStateFooter)
        with(binding) {
            list.layoutManager = LinearLayoutManager(requireContext())
            list.adapter = adapter
            imagesAdapter.addLoadStateListener {
                loadStateFooter.loadState = it.refresh
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { imagesAdapter.submitData(it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}