package com.alexnechet.domain.images.usecase

import androidx.paging.PagingData
import com.alexnechet.domain.images.model.Image
import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.mockedImage
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SearchImagesUseCaseTest {
    private val imagesRepository: ImagesRepository = mock()
    private val query = "cats"


    @Test
    fun case_empty_list() = runTest {
        whenever(imagesRepository.getImages(query)).thenReturn(flow { emit(PagingData.empty()) })
        val useCase = SearchImagesUseCase(imagesRepository)
        val result = useCase(query)
        assertEquals(result.last(), PagingData.empty<Image>())
    }

    @Test
    fun case_list_of_items() = runTest {
        val data = PagingData.from(listOf(mockedImage))
        whenever(imagesRepository.getImages(query)).thenReturn(flow { emit(data)})
        val useCase = SearchImagesUseCase(imagesRepository)
        val result = useCase(query)
        assertEquals(result.last(), data)
    }

    @Test
    fun case_exception_handled_correctly() = runTest {
        val message = "Error"
        whenever(imagesRepository.getImages(query)).thenReturn(flow { throw IllegalStateException(message)})
        val useCase = SearchImagesUseCase(imagesRepository)
        val result = useCase(query){
            assertEquals(message, it.message)
        }
        assertEquals(result.last(), PagingData.empty<Image>())
    }
}