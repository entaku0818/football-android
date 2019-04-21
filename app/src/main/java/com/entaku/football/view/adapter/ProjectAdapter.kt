package com.entaku.football.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.entaku.football.service.model.Team
import com.entaku.football.view.callback.ProjectClickCallback
import androidx.databinding.DataBindingUtil
import com.entaku.football.R
import com.entaku.football.databinding.ProjectListItemBinding

class ProjectAdapter() :
        RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>() {

    private var projectList: List<Team>? = null

    //現状との差分をListとしてRecyclerViewにセットする
    fun setProjectList(projectList: List<Team>) {

        if (this.projectList == null) {
            this.projectList = projectList

            //positionStartの位置からitemCountの範囲において、データの変更があったことを登録されているすべてのobserverに通知する。
            notifyItemRangeInserted(0, projectList.size)

        } else {

            //2つのListの差分を計算するユーティリティー。Support Library 24.2.0で追加された。
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@ProjectAdapter.projectList).size
                }

                override fun getNewListSize(): Int {
                    return projectList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return requireNotNull(this@ProjectAdapter.projectList)[oldItemPosition].id == projectList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = projectList[newItemPosition]
                    val old = projectList[oldItemPosition]

                    return project.id == old.id
                }
            })
            this.projectList = projectList

            //DiffUtilのメソッド=>差分を元にRecyclerViewAderpterのnotify系が呼ばれ、いい感じにアニメーションなどをやってくれます。
            result.dispatchUpdatesTo(this)
        }
    }

    //継承したインナークラスのViewholderをレイアウトとともに生成
    //bindするビューにコールバックを設定 -> ビューホルダーを返す
    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ProjectViewHolder {
        val binding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),

                        R.layout.project_list_item, parent,
                        false) as ProjectListItemBinding

        //binding.callback = projectClickCallback

        return ProjectViewHolder(binding)
    }

    //ViewHolderをDataBindする
    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.team = requireNotNull(projectList)[position]
        holder.binding.executePendingBindings()
    }

    //リストのサイズを返す
    override fun getItemCount(): Int {
        return if (projectList == null) 0 else requireNotNull(projectList).size
    }

    //インナークラスにViewHolderを継承し、project_list_item.xml に対する Bindingを設定
    open class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
