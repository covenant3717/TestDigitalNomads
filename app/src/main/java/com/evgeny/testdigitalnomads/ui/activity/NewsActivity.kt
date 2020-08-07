package com.evgeny.testdigitalnomads.ui.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseActivity
import com.evgeny.testdigitalnomads.databinding.ActivityNewsBinding
import com.evgeny.testdigitalnomads.mvvm.vm.NewsVM
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity() {

    private val newsVM: NewsVM by viewModel()
    private val binding: ActivityNewsBinding by binding(R.layout.activity_news)

    private var newsAdapter: RVNewsAdapter? = null

    //==============================================================================================

    override fun initMain() {
        binding.apply {
            lifecycleOwner = this@NewsActivity
            ui = newsVM
        }

        initRvNews()

    }
    
    //==============================================================================================

    private fun initRvNews() {
        newsAdapter = RVNewsAdapter(newsVM)
        news_rv.adapter = newsAdapter

        lifecycleScope.launch {
            newsVM.getPagedNewsList().observe(this@NewsActivity, Observer {
                newsAdapter?.submitList(it)
            })
        }
    }
    


}