package com.assemblermaticstudio.ioasysclient.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.assemblermaticstudio.ioasysclient.R

object FragmentDirector {
    fun show(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(R.anim.enter_slide, R.anim.exit_slide, R.anim.enter_slide, R.anim.exit_slide)
            .add(R.id.fragment_container, fragment).commit()
    }

    fun replace(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .setCustomAnimations(R.anim.enter_slide, R.anim.exit_slide, R.anim.enter_slide, R.anim.exit_slide)
            .commit()
    }
}