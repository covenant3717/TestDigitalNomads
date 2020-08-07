package com.evgeny.testdigitalnomads.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.databinding.ItemNewsBinding
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.model.News
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.util.mlg
import com.evgeny.testdigitalnomads.util.updateDiffUtil


class RVNewsAdapter(private val newsView: NewsView) :
    PagedListAdapter<DBNews, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DBNews>() {

            override fun areItemsTheSame(oldNews: DBNews, newNews: DBNews) =
                oldNews.id == newNews.id

            override fun areContentsTheSame(oldNews: DBNews, newNews: DBNews) =
                oldNews == newNews
        }
    }

//    var list: List<DBNews> = listOf()
//        set(value) {
//            this.updateDiffUtil<DBNews>(list, value)
//            field = value
//        }

    //==============================================================================================

    override fun getItemCount() = super.getItemCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemNewsBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false)
        return BidVH(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val current = getItem(position)
        (holder as BidVH).bind(current)
    }

    //==============================================================================================

    private inner class BidVH(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(current: DBNews?) {
            current?.let {
                binding.apply {
                    item = current
                    ui = newsView
                    executePendingBindings()
                }
            }
        }
    }
}