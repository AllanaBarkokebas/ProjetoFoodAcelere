package com.food.foodacelere.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.food.foodacelere.R
import kotlinx.android.synthetic.main.resister_componets.*
import android.content.Intent
import android.widget.CheckBox

class CadastroActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        lbllinktermos.setOnClickListener {
            val intent = Intent(this,TermosActivity::class.java)
            startActivity(intent)
        }


    }
}