package com.alexnechet.feature.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexnechet.feature.common.debounce

class ImageListViewModel : ViewModel() {

    fun setSearchText(text: String) {
        debounce(scope = viewModelScope) { text: String ->

        }
    }
}