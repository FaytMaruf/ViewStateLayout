package com.irtikaz.view.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.button.MaterialButton
import com.irtikaz.view.R
import kotlinx.android.synthetic.main.viewstate_error.view.*
import kotlinx.android.synthetic.main.viewstate_loading.view.*
import java.util.*
import kotlin.collections.ArrayList

class ViewStateLayout
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), StateView{


    companion object {
        private const val STATE_CONTENT = "s_content"
        private const val STATE_LOADING = "s_loading"
        private const val STATE_EMPTY = "s_empty"
        private const val STATE_ERROR = "s_error"
    }

    private var inflater: LayoutInflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var view: View

    //required views for content
    private var contentViews = ArrayList<View>()

    //required views for empty
    private lateinit var emptyLayout: View

    //required views for loading
    private lateinit var loadingLayout: View
    private lateinit var loading: ProgressBar

    //required views for error
    private lateinit var errorLayout: View
    private lateinit var btnError: MaterialButton
    private lateinit var txtErrorTitle: TextView
    private lateinit var txtErrorMessage: TextView

    private var state = STATE_CONTENT


    override fun showContent() {
        setState(STATE_CONTENT, null, null, null, null)
    }

    override fun showError(title: String, message: String, buttonText: String,
                           listener: View.OnClickListener) {
        setState(STATE_ERROR, title, message, buttonText, listener)

    }

    override fun showErrorWithImage(img: Drawable, message: String, buttonText: String,
                                    listener: View.OnClickListener) {
        setState(STATE_ERROR, null, null, null, listener)
    }

    override fun showLoading() {
        setState(STATE_LOADING, null, null, null, null)
    }

    private fun setState(state: String, title: String?, message: String?, buttonText: String?, listener: View.OnClickListener?) {

        this.state = state
        hideAllViews()

        when (state) {
            STATE_CONTENT -> {
                setContentVisibility(true, Collections.emptyList())
            }
            STATE_LOADING -> {
                setContentVisibility(false, Collections.emptyList())
                setupLoadingView()
            }
            STATE_EMPTY -> {
            }
            STATE_ERROR -> {
                setContentVisibility(false, Collections.emptyList())
                setupErrorView()

                txtErrorTitle.text = title
                txtErrorMessage.text = message
                btnError.text = buttonText
                btnError.setOnClickListener(listener)
            }
        }
    }

    private fun setContentVisibility(visible: Boolean, skipIds: List<Int>) {
        for (v in contentViews) {
            if (!skipIds.contains(v.id)) {
                v.visibility = if (visible) VISIBLE else GONE
            }
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        if (child?.tag == null || (child.tag != STATE_LOADING && child.tag != STATE_EMPTY &&
                    child.tag != STATE_ERROR)) {
            contentViews.add(child!!)
        }
    }

    override fun currentState(): String {
        return state
    }

    private fun setupLoadingView() {
        if (!::loadingLayout.isInitialized) {
            view = inflater.inflate(R.layout.viewstate_loading, null)
            loadingLayout = view.layout_loading
            loadingLayout.tag = STATE_LOADING

            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT).apply {
            }
            if(view.parent == null) {
                addView(loadingLayout, layoutParams)
            }

        } else {
            loadingLayout.visibility = VISIBLE
        }
    }

    private fun setupErrorView() {
        if (!::errorLayout.isInitialized) {
            view = inflater.inflate(R.layout.viewstate_error, null)

            errorLayout = view.layout_error
            txtErrorTitle = view.txt_error_title
            txtErrorMessage = view.txt_error_msg
            btnError = view.btn_action

            errorLayout.tag = STATE_ERROR

            val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT).apply {
            }

            if(view.parent != null) {
                removeAllViews()
            }

            if(view.parent == null) {
                addView(errorLayout, layoutParams)
            }


        } else {
            errorLayout.visibility = VISIBLE
        }
    }

    private fun hideAllViews() {
        hideLoading()
        hideError()
    }

    private fun hideLoading() {
        if (::loadingLayout.isInitialized) {
            loadingLayout.visibility = GONE
        }
    }

    private fun hideError() {
        if (::errorLayout.isInitialized) {
            errorLayout.visibility = GONE
        }
    }
}