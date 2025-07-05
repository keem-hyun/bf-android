package umc.hackathon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Qualifier
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import umc.hackathon.data.network.JusoAPI
import umc.hackathon.data.network.APIClient
import umc.hackathon.data.network.JobPostingService
import umc.hackathon.BuildConfig
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiRetrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("juso_base_url")
    fun provideJusoBaseUrl(): String = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/"
    
    @Provides
    @Singleton
    @Named("api_base_url")
    fun provideApiBaseUrl(): String = BuildConfig.API_BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(@Named("juso_base_url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    @Provides
    @Singleton
    @ApiRetrofit
    fun provideApiRetrofit(@Named("api_base_url") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): JusoAPI {
        return retrofit.create(JusoAPI::class.java)
    }
    
    @Provides
    @Singleton
    fun provideAPIClient(@ApiRetrofit retrofit: Retrofit): APIClient {
        return retrofit.create(APIClient::class.java)
    }
    
    @Provides
    @Singleton
    fun provideJobPostingService(apiClient: APIClient): JobPostingService {
        return JobPostingService(apiClient)
    }
}