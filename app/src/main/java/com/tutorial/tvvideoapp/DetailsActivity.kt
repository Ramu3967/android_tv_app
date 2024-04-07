package com.tutorial.tvvideoapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.tutorial.tvvideoapp.api.MovieDetailsRepo
import com.tutorial.tvvideoapp.api.MovieDetailsViewModel
import com.tutorial.tvvideoapp.api.MoviesViewModelFactory
import com.tutorial.tvvideoapp.api.RetrofitUtil
import com.tutorial.tvvideoapp.databinding.ActivityDetailsBinding
import com.tutorial.tvvideoapp.models.moviedetails.MovieDetailsDataModel
import com.tutorial.tvvideoapp.utils.UtilFunctions.convertToHoursMinutes
import com.tutorial.tvvideoapp.utils.UtilFunctions.getImageUrl
import kotlinx.coroutines.launch

class DetailsActivity : FragmentActivity() {

    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val castFragment = com.tutorial.tvvideoapp.fragments.ListFragment()

        setUpCastFragment(castFragment)

        val movieId = intent.getIntExtra("id",0)
        // viewmodel init
        val movieRepo = MovieDetailsRepo(RetrofitUtil.getApiInstance())
        val viewModelFactory = MoviesViewModelFactory(movieRepo)
        val viewModel: MovieDetailsViewModel by viewModels {viewModelFactory }

        // make the api call
        lifecycleScope.launch {
            viewModel.getMovieDetailsById(movieId)
        }

        viewModel.movieDetailsLV.observe(this){
            binding.run {
                tvDetailsTitle.text = it.title
                tvDetailsDesc.text = it.overview
                tvDetailsSubtitle.text = use(it)
                Glide.with(this@DetailsActivity).load(getImageUrl(it.backdrop_path)).into(ivDetailsBanner)
            }
        }

//        viewModel.movieCastDetailsLV.observe(this){
//            // bind data to rowsupport frag
//            castFragment.bindCastData(it)
//        }



    }

    private fun setUpCastFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fl_cast_crew, fragment)
        ft.commit()
    }

    private fun use(details: MovieDetailsDataModel): String{
        val runtime = convertToHoursMinutes(details.runtime).let {
            "${it.first}h ${it.second}m"
        }
        val rating = String.format("%.2f",details.vote_average)
        val releasedYear = details.release_date.substring(0,4)
        return "IMDB:$rating $releasedYear $runtime"
    }
}