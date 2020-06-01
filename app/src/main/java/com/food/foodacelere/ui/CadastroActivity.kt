package com.food.foodacelere.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.food.foodacelere.R
import kotlinx.android.synthetic.main.resister_componets.*
import android.content.Intent
import android.widget.CheckBox
import kotlinx.android.synthetic.main.toolbar.*

class CadastroActivity : AppCompatActivity() {

    companion object {
        private const val RESULT_TERM_CODE = 1
        const val TERM_ACCEPT = "ACCEPT"
        const val TERM_RESULT = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        setupToolbar()

        lbllinktermos.setOnClickListener {
            val intent = Intent(this, TermosActivity::class.java)
            startActivityForResult(intent, RESULT_TERM_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            when (resultCode) {
                Activity.RESULT_OK -> {
                    checkbox.isChecked = true
                }
                Activity.RESULT_CANCELED -> {

                }
            }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.let {
            title = "Cadastro"
        }
    }
}