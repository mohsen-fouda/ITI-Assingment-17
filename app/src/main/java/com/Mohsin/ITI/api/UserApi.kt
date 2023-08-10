package com.Mohsin.ITI.api

import com.Mohsin.ITI.model.ResponseUsersList
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
    @GET("/api/users")
    suspend fun getUser():Response<ResponseUsersList>
}