package umc.hackathon.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import umc.hackathon.data.repository.DefaultRegionRepository
import umc.hackathon.data.repository.DefaultTestRepository
import umc.hackathon.domain.RegionRepository
import umc.hackathon.domain.TestRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {
    @Binds
    @Singleton
    internal abstract fun bindTestRepository(
        testRepositoryImpl: DefaultTestRepository
    ): TestRepository

    @Binds
    @Singleton
    internal abstract fun bindRegionRepository(
        regionRepositoryImpl: DefaultRegionRepository
    ): RegionRepository
}