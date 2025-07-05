package umc.hackathon.data.datasource

import umc.hackathon.data.network.JusoAPI
import umc.hackathon.model.Region
import javax.inject.Inject

class DefaultRegionDataSource @Inject constructor(private val jusoAPI: JusoAPI) : RegionDataSource {
    override suspend fun getAllRegions(): List<Region> {
        val response = jusoAPI.getRegCodes("*00000000")
        return response.regcodes.map { regCode ->
            Region(
                code = regCode.code,
                name = regCode.name
            )
        }
    }
}