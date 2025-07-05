package umc.hackathon.domain

import umc.hackathon.model.Region

interface RegionRepository {
    suspend fun getAllRegions(): List<Region>
}