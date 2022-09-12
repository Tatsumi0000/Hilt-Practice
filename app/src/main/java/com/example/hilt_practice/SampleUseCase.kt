package com.example.hilt_practice

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface SampleUseCase {
    fun isGranted(): Boolean
}

class SampleUseCaseImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): SampleUseCase {

    @RequiresApi(33)
    override fun isGranted(): Boolean {
        return (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS,
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class SampleModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSampleUseCase(
        sampleUseCaseImpl: SampleUseCaseImpl
    ): SampleUseCase
}

//@Module
//@InstallIn(ViewModelComponent::class)
//class SampleModule {
//
//    @Provides
//    fun providesSampleUseCase(
//        @ApplicationContext applicationContext: Context
//    ): SampleUseCase {
//        return SampleUseCaseImpl(applicationContext)
//    }
//}