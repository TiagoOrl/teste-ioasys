package com.assemblermaticstudio.ioasysclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assemblermaticstudio.ioasysclient.R
import com.assemblermaticstudio.ioasysclient.view.fragment.FragmentDirector
import com.assemblermaticstudio.ioasysclient.view.fragment.LoginFragment

class MainActivity : AppCompatActivity() {

    lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        FragmentDirector.show(supportFragmentManager, loginFragment)
    }

    private fun initFragments() {
        loginFragment = LoginFragment.newInstance()
    }
}