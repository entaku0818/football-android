package com.entaku.football.view.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.AndroidViewModel
import com.entaku.football.service.model.Team
import com.entaku.football.service.model.TeamsResponse
import com.entaku.football.repository.FootballRepository


class ProjectListViewModel(application: Application) : AndroidViewModel(application) {

    //監視対象のLiveData
    //UIが観察できるようにコンストラクタで取得したLiveDataを公開する
    val projectListObservable: LiveData<List<Team>>

    init {

        //Repositoryからインスタンスを取得し、getProjectListを呼び出し、LiveDataオブジェクトに。
        //変換が必要な場合、これをTransformationsクラスで単純に行うことができます。
        projectListObservable = FootballRepository.instance.getProjectList()
    }
}