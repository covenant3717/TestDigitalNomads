package com.evgeny.testdigitalnomads.ui.activity

import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseActivity
import com.evgeny.testdigitalnomads.databinding.ActivityNewsBinding
import com.evgeny.testdigitalnomads.mvvm.vm.NewsVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity() {

    private val newsVM: NewsVM by viewModel()
    private val binding: ActivityNewsBinding by binding(R.layout.activity_news)

    //==============================================================================================

    override fun initMain() {
//        binding.apply {
//            lifecycleOwner = this@NewsActivity
//            ui = newsVM
//            newsAdapter = newsVM.newsAdapter
//        }

        binding.apply {
            lifecycleOwner = this@NewsActivity
            ui = newsVM
            newsAdapter = newsVM.newsAdapter2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        newsVM.clear()
    }


}