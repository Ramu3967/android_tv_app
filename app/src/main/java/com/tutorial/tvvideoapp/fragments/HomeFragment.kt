package com.tutorial.tvvideoapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tutorial.tvvideoapp.R
import com.tutorial.tvvideoapp.databinding.FragmentHomeBinding
import com.tutorial.tvvideoapp.models.Detail
import com.tutorial.tvvideoapp.models.MoviesDataModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragment: Fragment() {
    lateinit var listFragment: ListFragment

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()

        // static data
        val gson = Gson()
        val i: InputStream = requireContext().assets.open("movies.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList = gson.fromJson(br, MoviesDataModel::class.java)

        listFragment.bindData(dataList)
        listFragment.clickLogicSetter(logic = { updateBanner(it) })
    }

    fun updateBanner(movieDetails: Detail){
        binding.run{
            tvMovieTitle.text = movieDetails.original_title
            tvMovieSubtitle.text = "Language: ${movieDetails.original_language}"
            tvMovieDescription.text = movieDetails.overview
            val url = "https://www.themoviedb.org/t/p/w500" + movieDetails.backdrop_path
            Glide.with(this@HomeFragment).load(url).into(imgBanner)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}