package com.evgeny.testdigitalnomads.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.testdigitalnomads.R
import com.evgeny.testdigitalnomads.databinding.ItemNewsBinding
import com.evgeny.testdigitalnomads.model.DBNews
import com.evgeny.testdigitalnomads.mvvm.view.NewsView
import com.evgeny.testdigitalnomads.repository.network.NetState
import com.evgeny.testdigitalnomads.util.mlg
import kotlinx.android.synthetic.main.item_footer.view.*


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

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2

    private var state = NetState.LOADING

    //==============================================================================================

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            DATA_VIEW_TYPE ->
                NewsVH(DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false))
            else -> FooterVH(inflater.inflate(R.layout.item_footer, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsVH -> holder.bind(getItem(position))
            is FooterVH -> holder.bind(state)
        }
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == NetState.LOADING || state == NetState.ERROR)
    }

    fun setState(state: NetState) {
        this.state = state
        notifyItemChanged(super.getItemCount())
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

    private inner class FooterVH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(state: NetState?) {
            itemView.footer.setOnClickListener { newsView.btnRefresh(it) }

            when (state){
                NetState.ERROR -> {
                    itemView.footer_tv_refresh.visibility = View.VISIBLE
                    itemView.footer_pb.visibility = View.INVISIBLE
                }
                NetState.LOADING -> {
                    itemView.footer_tv_refresh.visibility = View.INVISIBLE
                    itemView.footer_pb.visibility = View.VISIBLE
                }

                NetState.LOADED -> {
                    itemView.footer_tv_refresh.visibility = View.INVISIBLE
                    itemView.footer_pb.visibility = View.INVISIBLE
                }
            }

//            itemView.footer_tv_refresh.visibility =
//                if (state == NetState.ERROR) View.VISIBLE else View.INVISIBLE
//
//            if (state == NetState.LOADING) itemView.footer_pb.visibility = View.VISIBLE
//            if (state == NetState.LOADED) itemView.footer_pb.visibility = View.INVISIBLE
        }
    }
}