package com.example.cruisemastersmad.api

import com.example.cruisemastersmad.models.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {
    @GET("cars")
    suspend fun getCars(): Response<List<Car>>

    @GET("cars/{id}")
    suspend fun getCarDetails(@Path("id") id: String): Response<Car>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<User>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @GET("bookings/{email}")
    suspend fun getBookings(@Path("email") email: String): Response<List<Booking>>

    @POST("bookings")
    suspend fun createBooking(@Body booking: Booking): Response<CreateResponse>

    @GET("purchases/{email}")
    suspend fun getPurchases(@Path("email") email: String): Response<List<Purchase>>

    @POST("purchases")
    suspend fun createPurchase(@Body purchase: Purchase): Response<CreateResponse>

    companion object {
        private const val BASE_URL = "http://10.0.2.2:3000/api/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

// Request/Response data classes
data class LoginRequest(val email: String, val password: String)
data class RegisterRequest(val name: String, val email: String, val password: String)
data class RegisterResponse(val message: String, val userId: Int)
data class CreateResponse(val message: String, val bookingId: Int? = null, val purchaseId: Int? = null)
