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
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexnechet.feature.images.R
import com.alexnechet.feature.images.databinding.FragmentImagesListBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ImageListFragment : Fragment() {

    private val viewModel: ImageListViewModel by viewModel()

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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.searchEdittext.doAfterTextChanged { text ->
            val searchText = text.toString().trim()
            viewModel.search(searchText)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val initialSearchWord = getString(R.string.default_search_word)
            binding.searchEdittext.setText(initialSearchWord)
            viewModel.search(initialSearchWord)
        }
        val imagesAdapter = ImagesListAdapter { }
        val loadStateFooter = ImagesListLoadAdapter { imagesAdapter.retry() }

        val adapter = imagesAdapter.withLoadStateFooter(
            footer = loadStateFooter
        )


        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = adapter
        imagesAdapter.addLoadStateListener {
            loadStateFooter.loadState = it.refresh
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.data.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    imagesAdapter.submitData(it)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}