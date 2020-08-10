package com.evgeny.testdigitalnomads.ui.activity

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.base.BaseActivity
import com.evgeny.testdigitalnomads.databinding.ActivityNewsBinding
import com.evgeny.testdigitalnomads.mvvm.vm.NewsVM
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsAdapter
import com.evgeny.testdigitalnomads.ui.adapter.RVNewsLoadStateAdapter
import com.evgeny.testdigitalnomads.util.gone
import com.evgeny.testdigitalnomads.util.visible
import kotlinx.android.synthetic.main.activity_news.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : BaseActivity() {

    private val newsVM: NewsVM by viewModel()
    private val binding: ActivityNewsBinding by binding(R.layout.activity_news)

    private lateinit var newsAdapter: RVNewsAdapter

    //==============================================================================================

    override fun initMain() {
        binding.apply {
            lifecycleOwner = this@NewsActivity
            ui = newsVM
        }

        initRVNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        newsVM.clear()
    }

    //==============================================================================================

    private fun initRVNews() {
        newsAdapter = RVNewsAdapter(newsVM)

        news_rv.apply {
            // bind data-adapter and footer-adapter to RV
            adapter = newsAdapter.withLoadStateFooter(
                footer = RVNewsLoadStateAdapter { newsAdapter.retry() }
            )
        }

        initNewsPagedFlow()
        initLoadStateListener()
    }

    private fun initNewsPagedFlow() {
        // observe to data-news and set them to adapter
        lifecycleScope.launch {
            newsVM.newsFlow.collectLatest { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }
    }

    private fun initLoadStateListener() {
        // get load state and change UI

        newsAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                news_pb.visible()
            } else {
                news_pb.gone()

                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
//                    Toast.makeText(this, it.error.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}