package com.faraji.opeque.core.data.remote

import com.faraji.opeque.core.domain.response.MenuResponse
import retrofit2.Response
import retrofit2.http.GET

interface MenuApi {

    @GET("menu.json")
    suspend fun getMenu() : Response<MenuResponse>
}