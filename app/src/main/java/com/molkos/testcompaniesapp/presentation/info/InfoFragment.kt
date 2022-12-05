package com.molkos.testcompaniesapp.presentation.info

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.molkos.testcompaniesapp.databinding.FragmentInfoBinding
import com.molkos.testcompaniesapp.domain.model.CompanyInfo
import com.molkos.testcompaniesapp.glide.GlideApp
import com.molkos.testcompaniesapp.model.UiState
import com.molkos.testcompaniesapp.presentation.base.BaseFragment
import com.molkos.testcompaniesapp.utils.IMAGES_HOST
import com.molkos.testcompaniesapp.utils.MAPS_APP_PACKAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {

    companion object {
        fun newInstance(id: Int): InfoFragment = InfoFragment().apply {
            arguments = Bundle().apply {
                putInt(COMPANY_ID, id)
            }
        }

        private const val COMPANY_ID = "company_id_key"
    }

    private val viewModel by viewModel<InfoViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val id = arguments?.getInt(COMPANY_ID) ?: 0
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.loadInfo(id)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectDataFromViewModel()
    }

    private fun collectDataFromViewModel() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.info.collect { state ->
                    withContext(Dispatchers.Main) {
                        when (state) {
                            is UiState.Loading -> {
                                onLoading()
                            }
                            is UiState.Success -> {
                                applyData(state.data as CompanyInfo)
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

    private fun applyData(info: CompanyInfo) {
        with(binding) {
            name.text = info.name
            description.text = info.description

            if (info.phone.isNotEmpty()) {
                phone.text = info.phone
                phone.setOnClickListener {
                    startActivityWithIntent(Intent.ACTION_DIAL, Uri.parse("tel:${info.phone}"))
                }
            } else phoneInfo.visibility = View.GONE

            if (info.website.isNotEmpty()) {
                website.text = info.website
                website.setOnClickListener {
                    startActivityWithIntent(Intent.ACTION_VIEW, Uri.parse(prepareUrl(info.website)))
                }
            } else websiteInfo.visibility = View.GONE

            if (info.latitude != 0.0 && info.longitude != 0.0) {
                val gmIntentUri = Uri.parse("geo:0,0?q=${info.latitude},${info.longitude}(${info.name})")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmIntentUri)
                mapIntent.setPackage(MAPS_APP_PACKAGE)
                showLocation.setOnClickListener {
                    mapIntent.resolveActivity(requireActivity().packageManager)?.let {
                        startActivity(mapIntent)
                    }
                }
            } else showLocation.visibility = View.GONE

            GlideApp.with(requireContext())
                .load("$IMAGES_HOST${info.imageUrl}")
                .override(SIZE_ORIGINAL)
                .into(image)
        }
    }

    private fun startActivityWithIntent(action: String, uri: Uri) {
        val intent = Intent(action, uri)
        startActivity(intent)
    }

    private fun prepareUrl(website: String): String {
        var url = website
        if (!url.startsWith("www."))
            url = "www.$url"
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://$url"
        return url
    }

    override fun onError(message: String) {
        with(binding) {
            info.visibility = View.GONE
            loading.visibility = View.GONE
            error.text = message
            error.visibility = View.VISIBLE
        }
    }

    override fun onLoading() {
        with(binding) {
            info.visibility = View.GONE
            loading.visibility = View.VISIBLE
            error.text = ""
            error.visibility = View.GONE
        }
    }

    override fun onSuccess() {
        with(binding) {
            info.visibility = View.VISIBLE
            loading.visibility = View.GONE
            error.text = ""
            error.visibility = View.GONE
        }
    }


}