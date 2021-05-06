package dev.ramprasad.bloom.dependencyinjection

import androidx.lifecycle.MutableLiveData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.ramprasad.bloom.database.GardenTheme
import dev.ramprasad.bloom.database.Plant

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

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
