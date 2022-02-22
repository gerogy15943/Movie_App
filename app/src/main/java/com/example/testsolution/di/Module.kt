package com.example.testsolution.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testsolution.BuildConfig
import com.example.testsolution.data.mapper.MovieResponseMapper
import com.example.testsolution.data.remote.service.MovieService
import com.example.testsolution.data.repository.MovieDataSourceImpl
import com.example.testsolution.domain.repository.MovieDataSource
import com.example.testsolution.domain.usecases.GetMoviesUseCase
import com.example.testsolution.presentation.viewmodels.FragmentListViewModel
import com.example.testsolution.presentation.viewmodels.ViewModelFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideMapper(): MovieResponseMapper{
        return MovieResponseMapper()
    }
}

@Module
class UseCase{
    @Provides
    @Singleton
    fun provideGetMoviesUseCase(dataSource: MovieDataSource): GetMoviesUseCase{
        return GetMoviesUseCase(dataSource)
    }
}

@Module
class DataModule{

    @Provides
    @Singleton
    fun provideDataSource(movieService: MovieService, mapper: MovieResponseMapper) : MovieDataSource{
        return MovieDataSourceImpl(movieService, mapper)
    }
}

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): MovieService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/movies/v2/reviews/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor {chain ->
                val newUrl = chain.request().url
                    .newBuilder()
                    .addQueryParameter("api-key", BuildConfig.NYTIMES_API_KEY)
                    .build()
                val newRequest = chain.request().newBuilder()
                    .url(newUrl)
                    .build()
                chain.proceed(newRequest)
            })
            .build()
    }
}

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FragmentListViewModel::class)
    abstract fun bindImagesListViewModel(fragmentListViewModel: FragmentListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)