package com.teamonetech.freshdoko.presentation.login.otp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamonetech.freshdoko.MainActivity

import com.teamonetech.freshdoko.R
import com.teamonetech.freshdoko.databinding.FragmentOtpBinding
import com.teamonetech.freshdoko.presentation.login.LoginActivity


class OtpFragment : Fragment() {
lateinit var binding:FragmentOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOtpVerify.setOnClickListener {
            val intent = Intent(requireContext(),MainActivity::class.java)
            startActivity(intent)
        }

    }
    companion object {

        @JvmStatic
        fun newInstance() =
            OtpFragment()
    }


}