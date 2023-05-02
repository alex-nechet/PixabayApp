package com.alexnechet.feature.images.list

import androidx.paging.PagingData
import androidx.paging.map
import com.alexnechet.CoroutinesTestRule
import com.alexnechet.domain.images.model.BasicImageInfo
import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.toBasicImageInfo
import com.alexnechet.domain.images.usecase.SearchImagesUseCase
import com.alexnechet.mockedImage
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
internal class ImageListViewModelTest {
    @get:Rule
    val textRule = CoroutinesTestRule()

    private val initialSearchWord = "Fruits"
    private lateinit var viewModel: ImageListViewModel
    private val searchImagesUseCase: SearchImagesUseCase = mock()


    @Test
    fun search_success() = runTest{
        val expected = PagingData.from(listOf(mockedImage))
        whenever(searchImagesUseCase(initialSearchWord)).thenReturn(flow { emit(expected) })
        whenever(searchImagesUseCase("")).thenReturn(flow { emit(PagingData.empty()) })
        viewModel = ImageListViewModel(initialSearchWord, searchImagesUseCase)
        viewModel.search("")
        val data = viewModel.data.first()
         assertEquals(data, PagingData.empty<BasicImageInfo>())
    }

    @Test
    fun initial_search_success() = runTest{
        val expected = PagingData.from(listOf(mockedImage))
        whenever(searchImagesUseCase(initialSearchWord)).thenReturn(flow { emit(expected) })
        viewModel = ImageListViewModel(initialSearchWord, searchImagesUseCase)
        val data = viewModel.data.first()
        data.map { assertEquals(it, mockedImage.toBasicImageInfo()) }
    }
}