package com.evgeny.testdigitalnomads.ui.activity

import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.util.TAG_ACTIVITY_NEWS
import com.evgeny.testdigitalnomads.base.BaseActivity
import com.evgeny.testdigitalnomads.databinding.ActivityMainBinding
import com.evgeny.testdigitalnomads.util.launchActivity
import com.evgeny.testdigitalnomads.mvvm.vm.MainVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainVM: MainVM by viewModel()
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    //==============================================================================================

    override fun initMain() {
        binding.apply {
            lifecycleOwner = this@MainActivity
            ui = mainVM
        }

        initStartActivity(mainVM){
            when (it) {
                TAG_ACTIVITY_NEWS -> {
                    launchActivity<NewsActivity>()
                    finish()
                }
            }
        }
    }

}
