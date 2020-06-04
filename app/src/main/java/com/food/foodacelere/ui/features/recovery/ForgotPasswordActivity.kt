package com.food.foodacelere.ui.features.recovery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.food.foodacelere.R
import kotlinx.android.synthetic.main.toolbar.*

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)


        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.let {
            title = "Recuperar senha"
        }
    }
}
