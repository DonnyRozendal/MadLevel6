package nl.hva.madlevel6.core.di

import nl.hva.madlevel6.BuildConfig
import nl.hva.madlevel6.core.di.interceptor.AuthenticationInterceptor
import nl.hva.madlevel6.core.navigation.Navigator
import nl.hva.madlevel6.core.platform.NetworkHandler
import nl.hva.madlevel6.features.data.datasource.Api
import nl.hva.madlevel6.features.data.repositories.MovieRepository
import nl.hva.madlevel6.features.domain.usecases.DiscoverMovieUseCase
import nl.hva.madlevel6.features.presentation.list.ListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module {
    single<Navigator>()
}

val networkModule = module {
    single { createOkHttpClient() }
    single { createRetrofit(okHttpClient = get()) }
    single { createWebService(retrofit = get()) }
    single { NetworkHandler(context = get()) }
}

val repositoryModule = module {
    single<MovieRepository>()
}

val useCaseModule = module {
    factory<DiscoverMovieUseCase>()
}

val viewModelModule = module {
    viewModel<ListViewModel>()
}

val koinModules = listOf(
    applicationModule,
    networkModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)

fun createOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )

    return OkHttpClient.Builder()
        .addInterceptor(AuthenticationInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createWebService(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}