package com.juni.recetarioapp.domain.usecase


import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
import com.juni.recetarioapp.mocks.getRecipeMock
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRecipeListUseCaseTest {

    private lateinit var repository: GetRecipeListRepository
    private lateinit var useCase: GetRecipeListUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = GetRecipeListUseCase(repository)
    }

    @Test
    fun `given getRecipeListRepository returns api failure when getList is called then emits error`() =
        runTest {
            val apiFailure = Failure.ApiFailure("")
            coEvery { repository.getListRecipe() } returns flow {
                emit(ResultType.Error<Failure>(apiFailure))
            }
            val result = useCase.getList().first()

            assert(result is ResultType.Error)

        }

    @Test
    fun `given getRecipeListRepository returns list of recipe when getList is called then emits success`() =
        runTest {
            val recipeList = listOf(getRecipeMock())
            coEvery { repository.getListRecipe() } returns flow {
                emit(ResultType.Success(recipeList))
            }
            val result = useCase.getList().first()

            assert(result is ResultType.Success)
        }
}