package com.entaku.football.service.model

import retrofit2.http.*
import retrofit2.Call


internal interface FootBallService {

    //一覧
    @Headers("X-Auth-Token: 9703d46d0a6c4e3f8f76ca1d17dcc150")
    @GET("/v2/competitions/PL/teams")
    fun getTeamList(): Call<TeamsResponse>

    companion object {

        //Retrofitインターフェース(APIリクエストを管理)
        val HTTPS_API_URL = "http://api.football-data.org/"
    }
}