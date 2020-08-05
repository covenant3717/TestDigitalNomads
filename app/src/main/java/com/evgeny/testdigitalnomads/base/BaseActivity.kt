package com.evgeny.testdigitalnomads.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.evgeny.testdigitalnomads.R


abstract class BaseActivity : AppCompatActivity() {


    //==============================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, android.R.anim.fade_out)

        initMain()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.fadeout)
        clear()
    }

    //==============================================================================================

    protected open fun initMain() {
        // do something
    }

    protected fun initStartActivity(vm: BaseVM, onResult: (tag_activity: String) -> Unit) {
        // метод вернет лямбду и тэг для открытия конкретной activity
        vm.startActivity.observe(this, Observer {
            it?.let { it1 -> onResult(it1) }
        })
    }

    // binding method is providing DataBindingUtil
    protected inline fun <reified T : ViewDataBinding> binding(@LayoutRes resId: Int): Lazy<T> =
        lazy { DataBindingUtil.setContentView<T>(this, resId) }


    private fun clear() {
        // clear something
    }
}