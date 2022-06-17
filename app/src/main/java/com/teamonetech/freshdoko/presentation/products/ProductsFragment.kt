package com.teamonetech.freshdoko.presentation.products

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.teamonetech.freshdoko.data.commun.onError
import com.teamonetech.freshdoko.data.commun.onLoading
import com.teamonetech.freshdoko.data.commun.onSuccess
import com.teamonetech.freshdoko.databinding.FragmentProductsBinding
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        initObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductsList(1)
//        binding.rvRickAndMorty.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.include.tvCartViewCart.setOnClickListener {
            findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToCartFragment())
//            findNavController().navigate()
//            val intent = Intent(this,CartActivity::class.java)
//            startActivity(intent)
        }
    }

    private fun initObserver() {
        viewModel.resultListCharacters.observe(viewLifecycleOwner) {
            it.onSuccess { list ->
                binding.progressCircular.hide()
                setListCharacters(list)
            }.onError { error ->
                binding.progressCircular.hide()
                when (error.messageResource) {
                    is Int -> requireActivity().toast(getString(error.messageResource))
                    is Error? -> {
                        error.messageResource?.let { errorMessage -> requireActivity().toast(errorMessage.message ?:"Errpr") }
                    }
                }
            }.onLoading {
                binding.progressCircular.show()
            }
        }
    }

    private fun setListCharacters(list: List<ProductsModel>) {
        with(binding.rvRickAndMorty) {
            itemAnimator = null

            adapter = ProductsAdapter(list, { it->
                Snackbar.make(
                    binding.coordinator,
                    "${it.name} added to your cart",
                    Snackbar.LENGTH_LONG
                ).show()
            },{ it ->

                if(it.isEmpty())
                    binding.include.cvMainCart.visibility = View.GONE
                else { binding.include.cvMainCart.visibility = View.VISIBLE
                val summaryCart = getCartSummary(it)
                binding.include.tvCartPriceQuantity.text = "${summaryCart.first} items | ${summaryCart.second}"}
            })

        }


    }

//    private fun goToDetailsActivity(singleCharacterModel: SingleCharacterModel) {
//        Intent(this, DetailsActivity::class.java).apply {
//            putExtra(CHARACTER_EXTRA, singleCharacterModel)
//            startActivity(this)
//        }
//    }
}