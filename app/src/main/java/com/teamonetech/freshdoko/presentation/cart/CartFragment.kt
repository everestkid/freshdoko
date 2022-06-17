package com.teamonetech.freshdoko.presentation.cart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.adevinta.leku.*
import com.adevinta.leku.locale.SearchZoneRect
import com.google.android.gms.maps.model.LatLng
import com.teamonetech.freshdoko.databinding.FragmentCartBinding
import com.teamonetech.freshdoko.utils.ShoppingCart
import com.teamonetech.freshdoko.utils.getCartSummary
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment() {
   private lateinit var binding:FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLocation.setOnClickListener {
            val locationPickerIntent = LocationPickerActivity.Builder()
                .withSearchZone("en_NP")
                .withSearchZone(SearchZoneRect(LatLng(  27.49852672279832,84.9957275390625), LatLng(27.868216579514076,85.7208251953125)))
                .withGeolocApiKey("AIzaSyA4LgrrIc8h6I10Paok2bqcgJ5QRT0vvq0")
                .withDefaultLocaleSearchZone()
                .shouldReturnOkOnBackPressed()
                .withGooglePlacesApiKey("AIzaSyA4LgrrIc8h6I10Paok2bqcgJ5QRT0vvq0")
                .withSatelliteViewHidden()
                .withGoogleTimeZoneEnabled()
                .withVoiceSearchHidden()
                .withUnnamedRoadHidden()
                .build(requireActivity())

            startActivityForResult(locationPickerIntent, 2)
        }
        with(binding.rvCart) {
            adapter = CartAdapter(ShoppingCart.getCart().toList(), { it->
            },{ it ->
                val summary = getCartSummary(it)
                binding.tvCartTotal.text = "Total Rs.${summary.second}"
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RESULT****", "OK")
            if (requestCode == 1) {
                val latitude = data.getDoubleExtra(LATITUDE, 0.0)
                Log.d("LATITUDE****", latitude.toString())
                val longitude = data.getDoubleExtra(LONGITUDE, 0.0)
                Log.d("LONGITUDE****", longitude.toString())
                val address = data.getStringExtra(LOCATION_ADDRESS)

                Log.d("ADDRESS****", address.toString())
                val postalcode = data.getStringExtra(ZIPCODE)
                Log.d("POSTALCODE****", postalcode.toString())
                val bundle = data.getBundleExtra(TRANSITION_BUNDLE)

            } else if (requestCode == 2) {
                val latitude = data.getDoubleExtra(LATITUDE, 0.0)
                Log.d("LATITUDE****", latitude.toString())
                val longitude = data.getDoubleExtra(LONGITUDE, 0.0)
                Log.d("LONGITUDE****", longitude.toString())
                val address = data.getStringExtra(LOCATION_ADDRESS)
                Log.d("ADDRESS****", address.toString())
                val lekuPoi = data.getParcelableExtra<LekuPoi>(LEKU_POI)
                Log.d("LekuPoi****", lekuPoi.toString())

                binding.btnLocation.text = address

            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED")
        }
    }
}