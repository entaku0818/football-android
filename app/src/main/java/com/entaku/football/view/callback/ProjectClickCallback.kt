package com.entaku.football.view.callback

import com.entaku.football.service.model.Team


/**
 * クリック操作を伝えるinterface
 * @link onClick(Project project) 詳細画面に移動
 */
interface ProjectClickCallback {
    fun onClick(project: Team)
}
