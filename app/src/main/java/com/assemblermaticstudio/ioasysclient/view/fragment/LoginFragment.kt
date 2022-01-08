package com.assemblermaticstudio.ioasysclient.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.utils.createDialog
import com.assemblermaticstudio.ioasysclient.utils.createProgressDialog
import com.assemblermaticstudio.ioasysclient.utils.hideSoftKeyboard
import com.assemblermaticstudio.ioasysclient.view.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.net.NetworkInfo

import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService





class LoginFragment : Fragment() {

    lateinit var fragment: View

    lateinit var passwordEt: EditText
    lateinit var emailEt: EditText
    lateinit var loginBtn: TextView
    lateinit var login_errMsgTv: TextView
    var isPasswordHidden: Boolean = true

    private val viewModel by viewModel<LoginViewModel>()
    private val dialog by lazy { requireContext().createProgressDialog() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = inflater.inflate(R.layout.login_fragment, container, false)
        initViews()
        return fragment
    }

    private fun initViews() {

        passwordEt = fragment.findViewById(R.id.passwordEt)
        emailEt = fragment.findViewById(R.id.emailEt)

        fragment.findViewById<AppCompatImageView>(R.id.passwordDisplayBtn).setOnClickListener {

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

        loginBtn = fragment.findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            login(emailEt.text.toString(), passwordEt.text.toString())
        }

        login_errMsgTv = fragment.findViewById(R.id.login_errMsgTv)


        viewModel.output.observe(viewLifecycleOwner) {
            when(it) {
                LoginViewModel.State.Loading -> dialog.show()

                is LoginViewModel.State.Error -> {
                    requireContext().createDialog {
                        setMessage(it.msg)
                    }.show()
                    dialog.dismiss()
                }

                is LoginViewModel.State.Success -> {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "Login OK", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun login(email: String, password: String) {
        fragment.hideSoftKeyboard()

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


    companion object {
        fun newInstance() : LoginFragment = LoginFragment()
    }
}