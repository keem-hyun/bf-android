package umc.hackathon.data.datasource

import umc.hackathon.model.Region

interface RegionDataSource {
    suspend fun getAllRegions(): List<Region>
}