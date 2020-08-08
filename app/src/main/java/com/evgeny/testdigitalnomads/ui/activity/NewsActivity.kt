package com.evgeny.testdigitalnomads.ui.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseActivity
import com.evgeny.testdigitalnomads.databinding.ActivityNewsBinding
import com.evgeny.testdigitalnomads.mvvm.vm.NewsVM
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.util.visible
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity() {

    private val newsVM: NewsVM by viewModel()
    private val binding: ActivityNewsBinding by binding(R.layout.activity_news)

    //==============================================================================================

    override fun initMain() {
        binding.apply {
            lifecycleOwner = this@NewsActivity
            ui = newsVM
            newsAdapter = newsVM.newsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        newsVM.clear()
    }


}