package com.entaku.football

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.entaku.football.view.ui.ProjectListFragment
import com.entaku.football.view.ui.TAG_OF_PROJECT_LIST_FRAGMENT


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            //プロジェクト一覧のFragment
            val fragment = ProjectListFragment()

            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment, TAG_OF_PROJECT_LIST_FRAGMENT)
                .commit()
        }
    }

}


