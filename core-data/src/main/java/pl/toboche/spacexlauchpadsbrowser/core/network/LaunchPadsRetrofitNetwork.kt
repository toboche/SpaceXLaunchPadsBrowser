package pl.toboche.spacexlauchpadsbrowser.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import pl.toboche.core.data.BuildConfig
import pl.toboche.spacexlauchpadsbrowser.core.network.model.LaunchPadEntity
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

private interface RetrofitNiANetworkApi {
    @GET(value = "/v3/launchpads")
    suspend fun getTopics(
        @Query("id") ids: List<String>?,
    ): NetworkResponse<List<LaunchPadEntity>>
}

@kotlinx.serialization.Serializable
private data class NetworkResponse<T>(
    val data: T
)

private const val NiABaseUrl = BuildConfig.BACKEND_URL

@Singleton
class LaunchPadsRetrofitNetwork(
    private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json

) {
    private val networkApi = Retrofit.Builder()
        .baseUrl(NiABaseUrl)
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(RetrofitNiANetworkApi::class.java)

}