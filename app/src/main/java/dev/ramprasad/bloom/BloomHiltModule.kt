package dev.ramprasad.bloom

import android.app.Application
import androidx.compose.runtime.ComposeCompilerApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant

@Module
@InstallIn(ViewModelComponent::class)
object BloomHiltModule {

    @Provides
    @ViewModelScoped
    fun provideGardenThemesListLiveData(): MutableLiveData<List<GardenTheme>> {
        return MutableLiveData<List<GardenTheme>>()
    }

    @Provides
    @ViewModelScoped
    fun providePlantsListLiveData(): MutableLiveData<List<Plant>> {
        return MutableLiveData<List<Plant>>()
    }

    /*@Provides
    @ViewModelScoped
    fun <T> provideLiveData(): MutableLiveData<T> {
        return MutableLiveData<T>()
    }*/
}
