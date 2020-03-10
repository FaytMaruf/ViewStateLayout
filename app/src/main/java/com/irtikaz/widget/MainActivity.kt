package com.irtikaz.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_loading.setOnClickListener {
            layout_viewstate.showLoading()
        }

        val listenerContent = View.OnClickListener {
            layout_viewstate.showContent()
        }

        btn_content.setOnClickListener { layout_viewstate.showContent() }

        btn_error.setOnClickListener {
            val img = getDrawable(R.drawable.ic_eform_riwayat)
            layout_viewstate.showErrorWithImage(img,"Tidak ada Jaringan","Silakan cek kembali koneksi anda.", null, null)
        }

        btn_custom.setOnClickListener {
            layout_viewstate.customErrorView = R.layout.custom_error
            layout_viewstate.showError()
        }
    }
}
