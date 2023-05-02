package com.alexnechet.domain.images.usecase

import com.alexnechet.domain.images.repository.ImagesRepository
import com.alexnechet.mockedImage
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class GetImageByIdUseCaseTest {
    private val imagesRepository: ImagesRepository = mock()
    private val imageId = 15L

    @Test
    fun case_success() = runTest {
        whenever(imagesRepository.getImageById(imageId)).thenReturn(Result.success(mockedImage))
        val useCase = GetImageByIdUseCase(imagesRepository)
        val result = useCase(imageId)
        assertEquals(result, mockedImage)
    }

    @Test
    fun case_not_found() = runTest {
        whenever(imagesRepository.getImageById(imageId)).thenReturn(null)
        val useCase = GetImageByIdUseCase(imagesRepository)
        val result = useCase(imageId)
        assertEquals(result, null)
    }

    @Test
    fun case_error() = runTest {
        val message = "Error"
        whenever(imagesRepository.getImageById(imageId))
            .thenReturn(Result.failure(IllegalStateException(message)))

        val useCase = GetImageByIdUseCase(imagesRepository)
        val result = useCase(imageId){
            assertEquals(it.message, message)
        }

        assertEquals(result, null)
    }
}