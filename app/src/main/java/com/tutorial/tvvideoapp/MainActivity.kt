package com.tutorial.tvvideoapp

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.tutorial.tvvideoapp.databinding.ActivityMainBinding
import com.tutorial.tvvideoapp.fragments.HomeFragment
import com.tutorial.tvvideoapp.utils.UtilFunctions

class MainActivity : FragmentActivity(), View.OnKeyListener {

    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    private var SIDE_MENU = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        changeFragment(HomeFragment())

        binding.llSideMenu.children.forEach {
            it.setOnKeyListener(this)
        }
//        binding.tvHomeSearch.setOnKeyListener(this)
//        binding.tvHomeSearch.setOnKeyListener(this)
//        binding.tvHomeSearch.setOnKeyListener(this)
    }

    private fun changeFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_container,fragment)
        ft.commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onKey(p0: View?, keyCode: Int, p2: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_DPAD_LEFT ->{
                if(!SIDE_MENU) {
                    openMenu()
                    SIDE_MENU = true
                }
            }
            KeyEvent.KEYCODE_DPAD_RIGHT ->{
                if(SIDE_MENU){
                    SIDE_MENU = false
                    closeMenu()
                }
            }
        }
        return false
    }


    private fun openMenu() {
        binding.bflNavbar.requestLayout()
        binding.bflNavbar.layoutParams.width = UtilFunctions.getWidthInPercent(this,30)
    }

    private fun closeMenu() {
        binding.bflNavbar.requestLayout()
        binding.bflNavbar.layoutParams.width = UtilFunctions.getWidthInPercent(this,8)
    }

}