package com.juni.recetarioapp.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_prefs")


class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding")
        private val FAVORITES_RECIPES = stringSetPreferencesKey("favorite")

    }

    fun isOnboardingCompleted(): Flow<Boolean> {
        return dataStore.data.map { prefs ->
            prefs[ONBOARDING_COMPLETED] ?: false
        }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { prefs ->
            prefs[ONBOARDING_COMPLETED] = completed
        }
    }

    fun getFavoriteIds(): Flow<Set<String>> =
        dataStore.data.map { it[FAVORITES_RECIPES] ?: emptySet() }

    suspend fun addFavoriteId(recipesId: Set<String>) {
        dataStore.edit { prefs ->
            prefs[FAVORITES_RECIPES] = recipesId
        }
    }

    suspend fun removeFavoriteId(recipeId: String) {
        dataStore.edit { prefs ->
            val current = prefs[FAVORITES_RECIPES] ?: emptySet()
            prefs[FAVORITES_RECIPES] = current - recipeId
        }
    }
}