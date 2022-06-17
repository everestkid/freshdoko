package com.teamonetech.freshdoko.presentation.login.mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamonetech.freshdoko.R
import com.teamonetech.freshdoko.databinding.FragmentMobileLoginBinding
import com.teamonetech.freshdoko.presentation.login.otp.OtpFragment
import com.teamonetech.freshdoko.presentation.products.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class MobileLoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMobileLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMobileLoginBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {

        val fm = requireActivity().supportFragmentManager
            val fragmentTransition = fm.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainerView, OtpFragment())
            fragmentTransition.commit()

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MobileLoginFragment()
    }
}