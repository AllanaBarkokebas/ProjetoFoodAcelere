package com.food.foodacelere.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.food.foodacelere.R
import com.food.foodacelere.ui.CadastroActivity.Companion.TERM_ACCEPT
import com.food.foodacelere.ui.CadastroActivity.Companion.TERM_RESULT
import kotlinx.android.synthetic.main.activity_term.*
import kotlinx.android.synthetic.main.toolbar.*


class TermosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_term)

        setupToolbar()

        btn_concordo.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra(TERM_RESULT, TERM_ACCEPT)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.let {
            title = "Termos do Aplicativo"
        }
    }
}