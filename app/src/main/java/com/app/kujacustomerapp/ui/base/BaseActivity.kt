package com.app.kujacustomerapp.ui.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.CheckResult
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.kujacustomerapp.R
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
        viewModelSetup()
        initView()
    }

    @get:LayoutRes
    protected abstract val contentView: Int
    protected abstract fun viewModelSetup()
    protected abstract fun initView()

    /**
     * @param fragment       = fragment object
     * @param isAddBackStack = add to back in stack or not, true to add
     * @param tag            = fragment tag id, to identify fragments in fragment manager [FragmentManager.findFragmentByTag]
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun switchFragment(
        fragment: Fragment?,
        isAddBackStack: Boolean,
        tag: String
    ) {
        switchFragment(fragment, isAddBackStack, tag, null, true)
    }

    /**
     * @param fragment       = fragment object
     * @param isAddBackStack = add to back in stack or not, true to add
     * @param tag            = fragment tag id, to identify fragments in fragment manager [FragmentManager.findFragmentByTag]
     * @param playAnimation  true to enable transition animation
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun switchFragment(
        fragment: Fragment?,
        isAddBackStack: Boolean,
        tag: String,
        playAnimation: Boolean
    ) {
        switchFragment(fragment, isAddBackStack, tag, null, playAnimation)
    }

    /**
     * @param fragment       = fragment object
     * @param isAddBackStack = add to back in stack or not, true to add
     * @param tag            = fragment tag id, to identify fragments in fragment manager [FragmentManager.findFragmentByTag]
     * @param backStackTag   to pop multiple fragments [FragmentManager.popBackStack]
     * @param playAnimation  true to enable transition animation
     */
    @SuppressLint("NewApi")
    fun switchFragment(
        fragment: Fragment?,
        isAddBackStack: Boolean,
        tag: String,
        backStackTag: String?,
        playAnimation: Boolean
    ) {
        val fragmentTransaction =
            createFragmentTransaction(fragment, isAddBackStack, tag, backStackTag, playAnimation)
        if (!supportFragmentManager.isStateSaved) fragmentTransaction.commit()
    }

    @CheckResult
    fun createFragmentTransaction(
        fragment: Fragment?,
        isAddBackStack: Boolean,
        tag: String,
        backStackTag: String?,
        playAnimation: Boolean
    ): FragmentTransaction {
        val fragmentTransaction =
            supportFragmentManager.beginTransaction()
        /*if (playAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                    R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }*/fragmentTransaction.replace(R.id.container, fragment!!, tag)
        if (isAddBackStack) fragmentTransaction.addToBackStack(backStackTag)
        return fragmentTransaction
    }

    /**
     * safely pop back stack
     *
     * @return true if [FragmentManager.popBackStack] called
     */
    private fun popBackStack(): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0 &&
            !supportFragmentManager.isStateSaved
        ) {
            supportFragmentManager.popBackStack()
            return true
        }
        return false
    }

    fun popBackStack(tag: String): Boolean {
        if (supportFragmentManager.backStackEntryCount > 0 &&
            !supportFragmentManager.isStateSaved
        ) {
            supportFragmentManager.popBackStack(
                tag,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
            return true
        }
        return false
    }
}