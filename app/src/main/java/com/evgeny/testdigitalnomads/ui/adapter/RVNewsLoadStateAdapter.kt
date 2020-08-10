package com.evgeny.testdigitalnomads.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evgeny.testdigitalnomads.R
import kotlinx.android.synthetic.main.item_footer.view.*

/**
 * Created by Evgeny Kuksov 10.08.2020
 */
class RVNewsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RVNewsLoadStateAdapter.LoadStateViewHolder>() {

    //==============================================================================================

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.itemView.footer_pb
        val btnRetry = holder.itemView.footer_btn_refresh
        val txtErrorMessage = holder.itemView.footer_tv_msg

        btnRetry.isVisible = loadState !is LoadState.Loading
        txtErrorMessage.isVisible = loadState !is LoadState.Loading
        progress.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            txtErrorMessage.text = loadState.error.localizedMessage
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_footer, parent, false)
        )
    }

    //==============================================================================================

    class LoadStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view)
}