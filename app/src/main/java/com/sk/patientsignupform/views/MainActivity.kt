package com.sk.patientsignupform.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.sk.patientsignupform.R
import com.sk.patientsignupform.databinding.ActivityMainBinding
import com.sk.patientsignupform.views.fragments.CalendarFragment
import com.sk.patientsignupform.views.fragments.ProfileFragment
import com.sk.patientsignupform.views.fragments.RegisterFragment

class MainActivity : AppCompatActivity() {

    /**
     * Holds binding object
     */
    private lateinit var binding: ActivityMainBinding

    /**
     * Holds current fragment.
     */
    private var mCurrentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        switchFragment(RegisterFragment())
        setBottomNavigationItemSelectedListener()
    }

    private fun switchFragment(fragment: Fragment) {
        mCurrentFragment = supportFragmentManager.findFragmentById(binding.flContainer.id)
        try {
            if (fragment.javaClass.name != mCurrentFragment?.javaClass?.name ?: "") {
                mCurrentFragment = fragment
                supportFragmentManager.beginTransaction()
                    .replace(binding.flContainer.id, fragment, fragment::class.java.name)
                    .addToBackStack(fragment::class.java.name).commit()
            }
        } catch (exception: Exception) {

        }
    }

    override fun onBackPressed() {
        if (this::binding.isInitialized) {
            mCurrentFragment = supportFragmentManager.findFragmentById(binding.flContainer.id)
            if (mCurrentFragment is RegisterFragment || mCurrentFragment is ProfileFragment ||
                mCurrentFragment is CalendarFragment) {
                this.finishAffinity()
            } else {
                if (supportFragmentManager.backStackEntryCount > 1) {
                    supportFragmentManager.popBackStack()
                } else {
                    val fragmentList = supportFragmentManager.fragments
                    if (fragmentList.isNotEmpty()) {
                        if (fragmentList.size == 1 &&
                            mCurrentFragment?.javaClass?.name == fragmentList[0].javaClass.name
                        ) {
                            finish()
                        } else {
                            super.onBackPressed()
                        }
                    } else {
                        super.onBackPressed()
                    }
                }
            }
        }
    }

    /**
     * Sets bottom navigation item selected listener.
     */
    private fun setBottomNavigationItemSelectedListener() {
        binding.bnvMain.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionHome -> {
                    switchFragment(RegisterFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.actionCalendar -> {
                    switchFragment(CalendarFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.actionProfile -> {
                    switchFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            else -> Unit
        }
        return false
    }
}