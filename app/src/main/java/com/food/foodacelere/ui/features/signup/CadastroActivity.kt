package com.food.foodacelere.ui.features.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.food.foodacelere.R
import com.food.foodacelere.ui.app.*
import com.food.foodacelere.ui.app.Validations.Companion.validateBirthDate
import com.food.foodacelere.ui.app.Validations.Companion.validateEmail
import com.food.foodacelere.ui.app.Validations.Companion.validateName
import com.food.foodacelere.ui.app.Validations.Companion.validatePassword
import com.food.foodacelere.ui.app.Validations.Companion.validatePhone
import com.food.foodacelere.ui.features.store.HomeStoreActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.resister_componets.*
import kotlinx.android.synthetic.main.toolbar.*


class CadastroActivity : AppCompatActivity() {

    lateinit var viewModel: CadastroViewModel
    lateinit var mSharedPreferences: SecurityPreferences

    companion object {
        private const val RESULT_TERM_CODE = 1
        const val TERM_ACCEPT = "ACCEPT"
        const val TERM_RESULT = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        mSharedPreferences = SecurityPreferences(this)

        viewModel = ViewModelProvider(this).get(CadastroViewModel::class.java)


        setupToolbar()
        lbllinktermos.setOnClickListener {
            val intent = Intent(this, TermosActivity::class.java)
            startActivityForResult(
                intent,
                RESULT_TERM_CODE
            )
        }

        txtSenha.setError(
            "Digite uma senha com pelo menos 8 caracteres entre números e letras maiúscula e menúscula",
            null
        )

        setupObserver()

        btnCadastrar.setOnClickListener {
            hideKeyboard()
            onSubmitUserInfo()
        }

        txtCpf.addTextChangedListener(Mask.mask("###.###.###-##", txtCpf))
        txtTelefone.addTextChangedListener(Mask.mask("(##)####-####", txtTelefone))
        txtDataNasc.addTextChangedListener(Mask.mask("##/##/####", txtDataNasc))


    }

    private fun hideKeyboard() {
        val view = btnCadastrar
        if(view != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }


    private fun setupObserver() {
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is LoadingData -> {
                    Toast.makeText(this, "CARREGANDO", Toast.LENGTH_SHORT).show()
                }
                is SuccessData -> {
                    storeStringsOnSharedPreferences(it.user)
                    callHomeStore()
                    Toast.makeText(this, "SUCESSO", Toast.LENGTH_SHORT).show()
                }
                is ErrorData -> {
                    Toast.makeText(this, "ERROR:${it.errorMessage}", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun callHomeStore() {
        val intent = Intent(this, HomeStoreActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading() {
        containerLoadingResister.visibility = View.VISIBLE
        resister_appbar.visibility = View.GONE
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

    private fun onSubmitUserInfo() {
        val fullNameUser = txtNomeCompleto.text.toString()
        val emailUser = txtEmail.text.toString()
        val phoneUser = txtTelefone.text.toString()
        val birthDayUser = txtDataNasc.text.toString()
        val documentUser = txtCpf.text.toString()
        val passwordUser = txtSenha.text.toString()
        val confirmPassword = txtconfirmarSenha.text.toString()
        val acceptTermsUser = checkbox.isChecked

        var validToResister = true

        val user = User(
            fullNameUser,
            emailUser,
            phoneUser,
            birthDayUser,
            documentUser,
            passwordUser,
            acceptTermsUser,
            ""
        )

        if (!emailUser.validateEmail()) {
            txtEmail.error = "Email inválido"
            validToResister = false
        }
        if (!passwordUser.validatePassword()) {
            txtSenha.error =
                "Digite uma senha com pelo menos 8 caracteres entre números e letras maiúscula e menúscula"
            validToResister = false
        }

        if (confirmPassword != passwordUser || confirmPassword.isEmpty()) {
            txtconfirmarSenha.error = "As senhas não conincidem"
            validToResister = false
        }

        if (!fullNameUser.validateName()) {
            txtNomeCompleto.error = "Nome inválido"
            validToResister = false
        }

        if (!CPFUtil.myValidateCPF(documentUser)) {
            txtCpf.error = "CPF inválido"
            validToResister = false
        }

        if (!phoneUser.validatePhone()) {
            txtTelefone.error = "Numero inválido"
            validToResister = false
        }

        if (!birthDayUser.validateBirthDate()) {
            txtDataNasc.error = "Data inválida"
            validToResister = false
        }

        if (!acceptTermsUser) {
            Toast.makeText(
                this,
                "É necessário acertar os termos para fazer o cadastro",
                Toast.LENGTH_SHORT
            ).show()
            validToResister = false
        }

        if (validToResister) {
            showLoading()
            viewModel.resisterNewUser(user)

        }
    }

    private fun storeStringsOnSharedPreferences(user: User) {
        mSharedPreferences.storeString(Constants.KEY.USER_ID, user.userId)
        mSharedPreferences.storeString(Constants.KEY.USER_NAME, user.fullName)
        mSharedPreferences.storeString(Constants.KEY.USER_EMAIL, user.email)
        mSharedPreferences.storeString(Constants.KEY.USER_PHONE, user.phoneNumber)
        mSharedPreferences.storeString(Constants.KEY.USER_BIRTH, user.birthDay)
        mSharedPreferences.storeString(Constants.KEY.USER_DOCUMENT, user.document)
        mSharedPreferences.storeString(Constants.KEY.USER_PASSW, user.passowrd)
    }
}