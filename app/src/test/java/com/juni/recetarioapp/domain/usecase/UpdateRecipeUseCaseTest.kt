package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.repository.UpdateRecipeRepository
import com.juni.recetarioapp.mocks.getRecipeMock
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateRecipeUseCaseTest {

    private lateinit var updateRecipeRepository: UpdateRecipeRepository
    private lateinit var updateRecipeUseCase: UpdateRecipeUseCase

    @Before
    fun setUp() {
        updateRecipeRepository = mockk(relaxed = true)
        updateRecipeUseCase = UpdateRecipeUseCase(updateRecipeRepository)
    }

    @Test
    fun `given a recipe when updateRecipe is call then updateRecipeRepository is called`() =
        runTest {
            val recipe = getRecipeMock()
            updateRecipeUseCase.updateRecipe(recipe)
            coVerify(exactly = 1) { updateRecipeRepository.updateRecipe(recipe) }
        }
}
