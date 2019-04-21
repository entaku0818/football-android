package com.entaku.football.repository

import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.entaku.football.service.model.FootBallService
import com.entaku.football.service.model.Team
import com.entaku.football.service.model.TeamsResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.Callback


class FootballRepository//コンストラクタでRetrofitインスタンスを生成
private constructor() {

    //Retrofitインターフェース
    private val footBallService: FootBallService

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(FootBallService.HTTPS_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        footBallService = retrofit.create(FootBallService::class.java)
    }

    //APIにリクエストし、レスポンスをLiveDataで返す(一覧)
    fun getProjectList(): LiveData<List<Team>> {
        val data = MutableLiveData<List<Team>>()

        //Retrofitで非同期リクエスト->Callbackで(自分で実装したModel)型ListのMutableLiveDataにセット
        footBallService.getTeamList().enqueue(object : Callback<TeamsResponse>{
            override fun onResponse(call: Call<TeamsResponse>, @Nullable response: Response<TeamsResponse>) {
                Log.d("TeamsResponse", response.toString())
                val response: TeamsResponse? = response.body()
                Log.d("TeamsResponse", response?.teams.toString())
                data.postValue(response?.teams)
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                Log.d("TeamsResponse", t.toString())
                data.postValue(null)
            }
        })

        return data
    }


    companion object {

        //staticに提供できるRepository
        private var footballRepository: FootballRepository? = null

        //singletonでRepositoryインスタンスを取る
        //synchronized : オブジェクトに鍵をかけて、他のスレッドに邪魔されないようにして作業をする
        val instance: FootballRepository
            @Synchronized get() {
                if (footballRepository == null) {
                    footballRepository = FootballRepository()
                }
                return footballRepository as FootballRepository
            }
    }

}

