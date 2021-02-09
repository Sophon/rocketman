package com.example.rocketman.rocket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rocketman.databinding.FragmentRocketsDataBinding
import com.example.rocketman.domain.networking.SpaceXApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber

class RocketsDataFragment: Fragment() {

    private lateinit var binding: FragmentRocketsDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRocketsDataBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeApiCall()
    }

    private fun makeApiCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/v4/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val api = retrofit.create(SpaceXApi::class.java)

        api.getRockets().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Timber.d("SpaceApi: response received -> ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.e("SpaceApi: failed to fetch rockets")
            }
        })

        api.getRocket("5e9d0d95eda69973a809d1ec").enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Timber.d("SpaceApi: response received -> ${response.body()}")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.e("SpaceApi: failed to fetch that rocket")
            }
        })
    }
}