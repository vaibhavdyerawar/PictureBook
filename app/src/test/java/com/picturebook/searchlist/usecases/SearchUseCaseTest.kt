package com.picturebook.searchlist.usecases

import com.picturebook.mock.MockDataProvider
import com.picturebook.repo.network.NetworkDataRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchUseCaseTest {

    @MockK
    private lateinit var repository: NetworkDataRepo

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test getSearchImages gives list of images`() = runBlocking {
        //Given
        val searchUseCase = SearchUseCase(repository)
        val mockImages = MockDataProvider.createImages()

        //When
        coEvery { repository.getSearchedImages(any(), any()) }
            .returns(flowOf(mockImages))

        //Invoke
        val imageListFlow = searchUseCase(1, "")

        //Then
        MatcherAssert.assertThat(imageListFlow, CoreMatchers.notNullValue())

        val imageListDataState = imageListFlow.first()
        MatcherAssert.assertThat(imageListDataState, CoreMatchers.notNullValue())

        MatcherAssert.assertThat(imageListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            imageListDataState.itemsList.size,
            CoreMatchers.`is`(mockImages.itemsList.size)
        )
    }

    @After
    fun tearDown() {
    }
}

