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
            layout_viewstate.showError("Tidak ada Jaringan","Silakan cek kembali koneksi anda.", "Coba Lagi", listenerContent)
        }
    }
}
