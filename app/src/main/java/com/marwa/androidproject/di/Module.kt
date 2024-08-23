package com.marwa.androidproject.di

import com.marwa.androidproject.data.datasource.remote.api.ApiService
import com.marwa.androidproject.data.datasource.remote.api.AuthInterceptor
import com.marwa.androidproject.data.datasource.remote.api.RetrofitClient
import com.marwa.androidproject.data.datasource.remote.interfaces.MovieRemoteDS
import com.marwa.androidproject.data.datasource.remote.remote_repository.MovieRemoteDSImpl
import com.marwa.androidproject.domain.repository.MovieRepository
import com.marwa.androidproject.domain.repository.impl.MovieRepositoryImpl
import com.marwa.androidproject.domain.usecases.GetNowShowingUseCase
import com.marwa.androidproject.domain.usecases.GetPopularUseCase
import com.marwa.androidproject.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get(),get()) }
}
val useCaseModule = module {
    factory { GetNowShowingUseCase(get()) }
    factory { GetPopularUseCase(get()) }
}
val repositoryModule = module {
    single {
        MovieRepositoryImpl(get(),) as MovieRepository
    }
}
val datasourceModule = module {
    single {
        MovieRemoteDSImpl(get()) as MovieRemoteDS
    }
}

val networkModule = module {
    single { AuthInterceptor() }
    single { RetrofitClient.provideOkHttp(get()) }
    single { RetrofitClient.provideRetrofit(get()) }
    single { ApiService.createService(get()) }

}

