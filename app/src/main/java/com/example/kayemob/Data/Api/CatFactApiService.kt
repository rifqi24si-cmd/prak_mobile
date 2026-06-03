package com.example.kayemob.Data.Api

import com.example.kayemob.Data.Model.CatFactResponse
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactResponse
}
