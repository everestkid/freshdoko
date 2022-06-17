package com.teamonetech.freshdoko.presentation.login.mobile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.apollographql.apollo3.api.Error
import com.teamonetech.freshdoko.MainActivity
import com.teamonetech.freshdoko.data.commun.onError
import com.teamonetech.freshdoko.data.commun.onLoading
import com.teamonetech.freshdoko.data.commun.onSuccess
import com.teamonetech.freshdoko.databinding.FragmentLoginEmailBinding
import com.teamonetech.freshdoko.presentation.products.ProductsFragment
import com.teamonetech.freshdoko.utils.toast
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class EmailLoginFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentLoginEmailBinding

    private val viewModel: EmailLoginViewModel by viewModels()

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
        binding = FragmentLoginEmailBinding.inflate(inflater,container,false);
        initObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnEmailLogin.setOnClickListener {
            val email = binding.tilEmail.editText?.text.toString().trim()
            val password = binding.tilPassword.editText?.text.toString().trim()
            viewModel.loginUser(email,password)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MobileLoginFragment()
    }


    private fun initObserver() {
        viewModel.resultLogin.observe(viewLifecycleOwner) {
            it.onSuccess { list ->
//                binding.progressCircular.hide()
//                setListCharacters(list)
                val intent = Intent(requireContext(),MainActivity::class.java)
                startActivity(intent)
            }.onError { error ->
//                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> requireActivity().toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> requireActivity().toast(errorMessage.message ?:"Errpr") }
                    }
                }
            }.onLoading {
//                binding.progressCircular.show()
            }
        }
    }
}