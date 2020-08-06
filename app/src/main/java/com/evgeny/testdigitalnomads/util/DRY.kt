package com.evgeny.testdigitalnomads.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.Observable
import com.evgeny.testdigitalnomads.App
import com.evgeny.testdigitalnomads.R
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*


// DRY - don`t repeat yourself


//================================================================================= EXTENSIONS ↓ ↓ ↓

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

fun Context.toastShort(text: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.toastLong(text: String?, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

fun Toolbar.customInit(
    ctx: Context,
    background: Int = android.R.color.black,
    title: String = "",
    titleColor: Int = android.R.color.white,
    setMainIcon: Boolean = false,
    mainIconDrawable: Int = R.drawable.ic_arrow_back,
    mainIconColor: Int = android.R.color.white
): Toolbar {
    (ctx as AppCompatActivity).setSupportActionBar(this)

    // бекграунд тулбара и прозрачность на 0
    val colorBack = ResourcesCompat.getColor(ctx.resources, background, null)
    this.setBackgroundColor(colorBack)
    this.background.alpha = 255

    // заголовок
    (ctx.supportActionBar)?.title = title

    // цвет заголовка
    val colorTittle = ResourcesCompat.getColor(ctx.resources, titleColor, null)
    this.setTitleTextColor(colorTittle)

    // настройка иконки
    if (setMainIcon) {
        // показать иконку назад/гамбургер
        (ctx.supportActionBar)?.setDisplayHomeAsUpEnabled(true)
        (ctx.supportActionBar)?.setDisplayShowHomeEnabled(true)

        // программно задаю цвет иконке
        var drawable = ResourcesCompat.getDrawable(ctx.resources, mainIconDrawable, null)
        val color = ResourcesCompat.getColor(ctx.resources, mainIconColor, null)
        drawable = drawable?.let { DrawableCompat.wrap(it) }
        drawable?.let { DrawableCompat.setTint(it, color) }
        (ctx.supportActionBar)?.setHomeAsUpIndicator(drawable)
    }

    return this
}

// расширение, которое фиксит уродливый OnPropertyChangedCallback в плане компактности.
// А то он при разворачивании ну капец какой страшный
inline fun <reified T : Observable> T.addOnPropertyChanged(crossinline callback: (T) -> Unit) =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable?, i: Int) = callback(observable as T)
    }.also { addOnPropertyChangedCallback(it) }

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

//==================================================================================== METHODS ↓ ↓ ↓

fun getStringRes(stringRes: Int): String {
    return App.appCtx.resources.getString(stringRes)
}

fun getColorRes(colorRes: Int): Int {
    return App.appCtx.resources.getColor(colorRes)
}

fun getCurrentDate(datePattern: String): String {
    val sdf = SimpleDateFormat(datePattern, Locale.getDefault())
    return sdf.format(Date())
}

fun isConnected(): Boolean {
    val cm = App.appCtx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun Any.toJson() = Gson().toJson(this) ?: ""

fun mlg(str: String, tag: String = "ml") {
    Log.i(tag, str)
}

//============================================================================ PROJECT METHODS ↓ ↓ ↓



