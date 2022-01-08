package com.assemblermaticstudio.ioasysclient.di

import android.util.Log
import com.assemblermaticstudio.ioasysclient.repository.ApiAccess
import com.assemblermaticstudio.ioasysclient.service.ApiConnection
import com.assemblermaticstudio.ioasysclient.view.LoginViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Modules {

    private const val OK_HTTP = "OkHttp"


    fun load() {
        loadKoinModules(networkModules() + repositoryModules() + presentationModule())
    }


    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<ApiConnection>(get(), get())
            }
        }
    }


    private fun repositoryModules() : Module {
        return module {
            single<ApiAccess> { ApiAccess(get()) }
        }
    }


    private fun presentationModule() : Module {
        return module {
            viewModel { LoginViewModel(get()) }
        }
    }


    private inline fun <reified T> createService(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): T {
        return Retrofit.Builder()
            .baseUrl("https://empresas.ioasys.com.br/api/v1/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build().create(T::class.java)
    }
}