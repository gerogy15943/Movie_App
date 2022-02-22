package com.example.testsolution.presentation.fragments

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.testsolution.R
import com.example.testsolution.databinding.FragmentSplashScreenBinding
import com.example.testsolution.presentation.activity.MainActivity


class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        (activity as MainActivity).actionBarActivity?.hide()
        val animator = ObjectAnimator.ofFloat(binding.imgViewSplash, "rotation", 0.0f, 360f )
        animator.duration = 4000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.interpolator = AnticipateOvershootInterpolator()
        animator.start()
        Handler().postDelayed({
            this.findNavController().navigate(R.id.action_splashScreenFragment_to_fragmentList)
        }, 4000)

        return binding.root
    }
}