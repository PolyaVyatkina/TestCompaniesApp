package com.molkos.testcompaniesapp.presentation.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.molkos.testcompaniesapp.R
import com.molkos.testcompaniesapp.databinding.FragmentMainBinding
import com.molkos.testcompaniesapp.domain.model.Company
import com.molkos.testcompaniesapp.glide.GlideApp
import com.molkos.testcompaniesapp.model.UiState
import com.molkos.testcompaniesapp.presentation.base.BaseFragment
import com.molkos.testcompaniesapp.presentation.info.InfoFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private val viewModel by viewModel<MainViewModel>()

    private val adapter by lazy {
        MainAdapter(
            glide = GlideApp.with(this),
            onItemClick = onItemClick
        )
    }

    private val onItemClick: (id: Int) -> Unit = { id ->
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.container, InfoFragment.newInstance(id))
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectDataFromViewModel()

        binding.rv.adapter = adapter
    }

    private fun collectDataFromViewModel() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.companies.collect { state ->
                    withContext(Dispatchers.Main) {
                        when (state) {
                            is UiState.Loading -> {
                                onLoading()
                            }
                            is UiState.Success -> {
                                val companies = state.data as List<Company>
                                adapter.setItems(companies)
                                onSuccess()
                            }
                            is UiState.Error -> {
                                onError(state.error)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onError(message: String) {
        with(binding) {
            rv.visibility = View.GONE
            loading.visibility = View.GONE
            error.text = message
            error.visibility = View.VISIBLE
        }
    }

    override fun onLoading() {
        with(binding) {
            rv.visibility = View.GONE
            loading.visibility = View.VISIBLE
            error.text = ""
            error.visibility = View.GONE
        }
    }

    override fun onSuccess() {
        with(binding) {
            rv.visibility = View.VISIBLE
            loading.visibility = View.GONE
            error.text = ""
            error.visibility = View.GONE
        }
    }
}