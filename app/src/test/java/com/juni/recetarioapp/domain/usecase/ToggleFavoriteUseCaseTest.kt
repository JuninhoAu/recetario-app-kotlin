package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.repository.FavoriteRepository
import com.juni.recetarioapp.mocks.getRecipeMock
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    private lateinit var favoriteRepository: FavoriteRepository
    private lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    @Before
    fun setUp() {
        favoriteRepository = mockk(relaxed = true)
        toggleFavoriteUseCase = ToggleFavoriteUseCase(favoriteRepository)
    }

    @Test
    fun `given a recipe when updateRecipe is call then updateRecipeRepository is called`() =
        runTest {
            val recipe = getRecipeMock()
            toggleFavoriteUseCase.updateRecipe(recipe)
            coVerify(exactly = 1) { favoriteRepository.updateRecipe(recipe) }
        }
}
