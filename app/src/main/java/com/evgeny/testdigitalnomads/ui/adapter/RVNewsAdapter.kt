package com.evgeny.testdigitalnomads.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.databinding.ItemNewsBinding
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView


class RVNewsAdapter(private val newsView: NewsView) :
    PagingDataAdapter<DBNews, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DBNews>() {

            override fun areItemsTheSame(oldNews: DBNews, newNews: DBNews) =
                oldNews.id == newNews.id

            override fun areContentsTheSame(oldNews: DBNews, newNews: DBNews) =
                oldNews == newNews
        }
    }

    //==============================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return NewsVH(DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsVH -> holder.bind(getItem(position))
        }
    }

    //==============================================================================================

    private inner class NewsVH(private val binding: ItemNewsBinding) :
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