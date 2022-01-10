package com.assemblermaticstudio.ioasysclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.utils.createDialog
import com.assemblermaticstudio.ioasysclient.utils.createProgressDialog
import com.assemblermaticstudio.ioasysclient.utils.hideSoftKeyboard
import com.assemblermaticstudio.ioasysclient.view.fragment.FragmentDirector
import com.assemblermaticstudio.ioasysclient.view.fragment.SearchFragment
import com.assemblermaticstudio.ioasysclient.view.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    lateinit var passwordEt: EditText
    lateinit var emailEt: EditText
    lateinit var loginBtn: TextView
    lateinit var login_errMsgTv: TextView
    var isPasswordHidden: Boolean = true

    lateinit var searchFragment: SearchFragment

    private val viewModel by viewModel<LoginViewModel>()
    private val dialog by lazy { createProgressDialog() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initFragments()
    }


    private fun initViews() {

        passwordEt = findViewById(R.id.passwordEt)
        emailEt = findViewById(R.id.emailEt)

        findViewById<AppCompatImageView>(R.id.passwordDisplayBtn).setOnClickListener {

            if (isPasswordHidden) {
                passwordEt.transformationMethod = null
                it.setBackgroundResource(R.drawable.ic_eye_off)
                isPasswordHidden = false

            } else {
                passwordEt.transformationMethod = PasswordTransformationMethod()
                it.setBackgroundResource(R.drawable.ic_eye_on)
                isPasswordHidden = true
            }
        }

        loginBtn = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            login(emailEt.text.toString(), passwordEt.text.toString())
        }

        login_errMsgTv = findViewById(R.id.login_errMsgTv)


        viewModel.output.observe(this) {
            when(it) {

                LoginViewModel.State.Loading -> dialog.show()

                is LoginViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.msg)
                    }.show()
                    dialog.dismiss()
                }

                is LoginViewModel.State.Success -> {
                    dialog.dismiss()
                    Toast.makeText(this, "Login OK", Toast.LENGTH_SHORT).show()
                    FragmentDirector.replace(this.supportFragmentManager, searchFragment)
                    viewModel.setIdleState()
                }
                else -> { viewModel.setIdleState() }
            }
        }
    }


    private fun login(email: String, password: String) {
        emailEt.hideSoftKeyboard()

        if (email.equals("") ) {
            login_errMsgTv.text = "Insira o seu E-mail."
            return
        }

        if (password.equals("")) {
            login_errMsgTv.text = "Insira a sua senha."
            return
        }

        login_errMsgTv.text = ""

        viewModel.login(email, password)
    }

    private fun initFragments() {
        searchFragment = SearchFragment.newInstance()
    }

}