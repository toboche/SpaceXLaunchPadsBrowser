package pl.toboche.spacexlauchpadsbrowser.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.toboche.core.data.BuildConfig
import pl.toboche.spacexlauchpadsbrowser.core.network.model.LaunchPadEntity
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitLaunchPadsANetworkApi {
    @GET(value = "/v3/launchpads")
    suspend fun getLaunchPads(): List<LaunchPadEntity>
}

private const val BaseUrl = BuildConfig.BACKEND_URL

@Singleton
class LaunchPadsRetrofitNetwork @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json

) : LaunchPadsNetwork {
    private val networkApi = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build()
        )
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(RetrofitLaunchPadsANetworkApi::class.java)

    override suspend fun getLaunchPads(): List<LaunchPadEntity> {
        return networkApi.getLaunchPads()
    }

}