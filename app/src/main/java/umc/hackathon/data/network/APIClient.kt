package umc.hackathon.data.network

import retrofit2.http.*
import umc.hackathon.data.network.model.response.BaseResponse

interface APIClient {
    
    @GET
    suspend fun <T> get(
        @Url url: String,
        @QueryMap queries: Map<String, String> = emptyMap()
    ): BaseResponse<T>
    
    @POST
    suspend fun <T, R> post(
        @Url url: String,
        @Body body: R? = null
    ): BaseResponse<T>
    
    @PUT
    suspend fun <T, R> put(
        @Url url: String,
        @Body body: R? = null
    ): BaseResponse<T>
    
    @DELETE
    suspend fun <T> delete(
        @Url url: String
    ): BaseResponse<T>
    
    @PATCH
    suspend fun <T, R> patch(
        @Url url: String,
        @Body body: R? = null
    ): BaseResponse<T>
}