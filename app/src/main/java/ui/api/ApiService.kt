package ui.api

import ui.models.Car
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("cars")
    suspend fun getCars(): Response<List<Car>>

    @GET("cars/{id}")
    suspend fun getCarDetails(@Path("id") carId: Int): Response<Car>

    companion object {
        private const val BASE_URL = "https://your-api-url.com/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}