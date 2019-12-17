package com.example.myapplication.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewCheckErrorTitleBinding

class CheckErrorTitleView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ErrorTitleView {

    private var viewBinding: ViewCheckErrorTitleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_check_error_title,
            this,
            true
    )

    init {
        context.withStyledAttributes(attrs, R.styleable.CheckErrorTitleView) {
            viewBinding.tvTitle.hint = getString(R.styleable.CheckErrorTitleView_title)
        }
    }

    override fun setTitle(title: CharSequence?) {
        viewBinding.apply {
            if (title.isNullOrEmpty()) {
                tvTitle.visibility = View.GONE
            } else {
                tvTitle.visibility = View.VISIBLE
            }
            tvTitle.text = title
        }
    }

    override fun setWarning(error: CharSequence?) {
        viewBinding.apply {
            if (error.isNullOrEmpty()) {
                ivWarning.visibility = View.GONE
                tvErrorMessage.visibility = View.GONE
            } else {
                ivWarning.visibility = View.VISIBLE
                tvErrorMessage.visibility = View.VISIBLE
            }
            tvErrorMessage.text = error
        }
    }
}