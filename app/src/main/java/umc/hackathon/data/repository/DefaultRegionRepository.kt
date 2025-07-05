package umc.hackathon.data.repository

import umc.hackathon.data.datasource.RegionDataSource
import umc.hackathon.domain.RegionRepository
import umc.hackathon.model.Region
import javax.inject.Inject

internal class DefaultRegionRepository @Inject constructor(private val regionDataSource: RegionDataSource) :
    RegionRepository {
    override suspend fun getAllRegions(): List<Region> {
        return regionDataSource.getAllRegions()
    }

}