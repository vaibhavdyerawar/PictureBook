package com.picturebook.searchlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.picturebook.MainCoroutinesRule
import com.picturebook.mock.MockDataProvider
import com.picturebook.model.FetchImageData
import com.picturebook.model.ResponseStatusCallbacks
import com.picturebook.searchlist.usecases.SearchUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchListViewModelTest {

    private lateinit var viewModel: SearchListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var imageSearchUseCase: SearchUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

 /*   @Test
    fun `test searchImagesFromServer() returns list of images`() = runBlocking {
        // Given
        val imagesListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchImageData>>>(relaxed = true)

        // When
        coEvery { imageSearchUseCase.invoke(any(), any()) }
            .returns(flowOf(MockDataProvider.createImages()))

        // Invoke
        viewModel = SearchListViewModel(imageSearchUseCase)
        viewModel.searchImagesFromServer("yellow flowers")
        viewModel.searchResponse.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageSearchUseCase.invoke(any(), any()) }
        verify { imagesListObserver.onChanged(match { it.data != null }) }
        verify { imagesListObserver.onChanged(match { it.data?.itemsList?.size == 1 }) }
    }*/

    @Test
    fun `test loadNextPagePhotos() returns next page of list of images`() = runBlocking {
        // Given
        val imagesListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchImageData>>>(relaxed = true)

        // When
        coEvery { imageSearchUseCase.invoke(any(), any()) }
            .returns(flowOf(MockDataProvider.createImages()))

        // Invoke
        viewModel = SearchListViewModel(imageSearchUseCase)
        viewModel.loadNextPagePhotos()
        viewModel.searchResponse.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageSearchUseCase.invoke(any(), any()) }
        verify { imagesListObserver.onChanged(match { it.data != null }) }
        verify { imagesListObserver.onChanged(match { it.data?.itemsList?.size == 1 }) }
    }

    @Test
    fun `test retry() returns list of images on failure`() = runBlocking {
        // Given
        val imagesListObserver =
            mockk<Observer<ResponseStatusCallbacks<FetchImageData>>>(relaxed = true)

        // When
        coEvery { imageSearchUseCase.invoke(any(), any()) }
            .returns(flowOf(MockDataProvider.createImages()))

        // Invoke
        viewModel = SearchListViewModel(imageSearchUseCase)
        viewModel.retry()
        viewModel.searchResponse.observeForever(imagesListObserver)

        // Then
        coVerify(exactly = 1) { imageSearchUseCase.invoke(any(), any()) }
        verify { imagesListObserver.onChanged(match { it.data != null }) }
        verify { imagesListObserver.onChanged(match { it.data?.itemsList?.size == 1 }) }
    }
}