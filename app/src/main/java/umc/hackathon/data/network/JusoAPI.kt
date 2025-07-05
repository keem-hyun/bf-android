package umc.hackathon.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import umc.hackathon.data.network.model.response.RegCodeResponse

interface JusoAPI {
    /**
     * 행정구역 코드를 조회하는 API
     * @param pattern 검색할 코드 패턴 (예: "11*", "*00000000")
     * @param ignoreZero '동'만 조회할지 여부 (true일 경우)
     * @return API 응답
     */
    @GET("v1/regcodes")
    suspend fun getRegCodes(
        @Query("regcode_pattern") pattern: String,
        @Query("is_ignore_zero") ignoreZero: Boolean? = null
    ): RegCodeResponse
}