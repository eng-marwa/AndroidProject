package com.marwa.androidproject.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.marwa.androidproject.base.BaseException
import com.marwa.androidproject.base.ViewState
import com.marwa.androidproject.data.model.MovieResponse
import com.marwa.androidproject.databinding.FragmentHomeBinding
import com.marwa.androidproject.presentation.home.now_showing.NowShowingAdapter
import com.marwa.androidproject.presentation.home.popular.PopularAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var nowShowingAdapter: NowShowingAdapter
    private lateinit var popularAdapter: PopularAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.showingNowMoves.collect {
                when (it) {
                    is ViewState.Loaded -> onSuccess(it.data!!)
                    is ViewState.Error -> onFailure(it.error)
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            viewModel.popularMovies.collect {
                when (it) {
                    is ViewState.Loaded -> onPopularDataSuccess(it.data!!)
                    is ViewState.Error -> onPopularDataFailure(it.error)
                    else -> {}
                }
            }
        }
    }

    private fun onPopularDataFailure(error: BaseException?) {
        Toast.makeText(requireContext(), "${error?.statusMessage}", Toast.LENGTH_SHORT).show()

    }

    private fun onPopularDataSuccess(data: MovieResponse) {
        popularAdapter.setData(data.results)
    }

    private fun onFailure(error: BaseException?) {
        Toast.makeText(requireContext(), "${error?.statusMessage}", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess(data: MovieResponse) {
        if (::nowShowingAdapter.isInitialized) {
            nowShowingAdapter.setData(data.results)
        }
    }

    private fun initViews() {
        viewModel.getNowShowingMovies()
        viewModel.getPopularMovies()
        setupNowShowingRv()
        setupPopularRv()
    }

    private fun setupPopularRv() {
        popularAdapter = PopularAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.popularLayout.rvPopular.layoutManager = layoutManager
        binding.popularLayout.rvPopular.adapter = popularAdapter
    }

    private fun setupNowShowingRv() {
        nowShowingAdapter = NowShowingAdapter()
        binding.nowShowingLayout.rvNowShowing.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.nowShowingLayout.rvNowShowing.adapter = nowShowingAdapter
    }

}