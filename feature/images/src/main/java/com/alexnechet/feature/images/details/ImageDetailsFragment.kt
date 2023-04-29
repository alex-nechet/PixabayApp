package com.alexnechet.feature.images.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alexnechet.feature.images.databinding.FragmentImageDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ImageDetailsFragment : Fragment() {
    private val viewModel : ImageDetailsViewModel by viewModel()

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}