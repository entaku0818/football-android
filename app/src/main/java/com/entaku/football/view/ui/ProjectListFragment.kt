package com.entaku.football.view.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.entaku.football.MainActivity
import com.entaku.football.R
import com.entaku.football.databinding.FragmentProjectListBinding
import com.entaku.football.view.adapter.ProjectAdapter
import com.entaku.football.view.callback.ProjectClickCallback
import com.entaku.football.view.viewModel.ProjectListViewModel


/**
 *Project一覧のFragment
 */

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ProjectListFragment : Fragment() {

    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null

    //callbackに操作イベントを設定
//    private val projectClickCallback = object : ProjectClickCallback {
//        override fun onClick(project: Project) {
//            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
//                (activity as MainActivity).show(project)
//            }
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //dataBinding用のレイアウトリソースをセット
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        //イベントのcallbackをadapterに伝達
        projectAdapter = ProjectAdapter()

        //上記adapterをreclclerViewに適用
        requireNotNull(binding).projectList.adapter = projectAdapter
        //Loading開始
        requireNotNull(binding).isLoading = true
        //rootViewを取得
        return requireNotNull(binding).root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
        //監視を開始
        observeViewModel(viewModel)
    }

    //observe開始
    private fun observeViewModel(viewModel: ProjectListViewModel) {

        //データが更新されたらアップデートするように、LifecycleOwnerを紐付け、ライフサイクル内にオブザーバを追加
        //オブザーバーは、STARTED かRESUMED状態である場合にのみ、イベントを受信する
        viewModel.projectListObservable.observe(this, Observer { projects ->
            if (projects != null) {
                requireNotNull(binding).isLoading = false
                projectAdapter?.setProjectList(projects)
            }
        })
    }
}
