package com.juni.recetarioapp.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_prefs")


class OnboardingPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore

    companion object {
        private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding")
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
}