package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val loginFragment = LoginFragment()

//        val anotherLoginObj = LoginFragment.newInstance("a", "v")

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .add(R.id.frame_layout_master, loginFragment)
                .addToBackStack(null)
                .commit()
        }

        // variant 2
//        fragmentManager.findFragmentById(R.id.frame_layout_master)?.let {
//            fragmentManager.beginTransaction()
//                .replace(R.id.frame_layout_master, loginFragment)
//                .commit()
//            return
//        }
//
//        fragmentManager.beginTransaction().add(R.id.frame_layout_master, loginFragment).commit()
    }

}