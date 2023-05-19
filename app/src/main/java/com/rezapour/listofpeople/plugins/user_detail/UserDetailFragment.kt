package com.rezapour.listofpeople.plugins.user_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rezapour.listofpeople.R
import com.rezapour.listofpeople.databinding.FragmentUserDetailBinding
import com.rezapour.listofpeople.models.UserDomain
import com.rezapour.listofpeople.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailFragment : Fragment() {


    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFragment()
    }

    private fun setupFragment() {
        subscribeToViewModel()
        setupUi()
        loadData()
    }

    private fun setupUi() {
        binding.userDetailLswiper.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        viewModel.loadData(args.userId)
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        UiState.DefaultError -> onError(R.string.error_default_message)
                        is UiState.Error -> onError(uiState.message)
                        UiState.Loading -> loading()
                        is UiState.Success -> onSuccess(uiState.data)
                    }
                }
            }
        }
    }

    private fun onSuccess(user: UserDomain) {
        loading(false)
        with(binding) {
            Glide.with(requireContext())
                .load(user.imageUrl)
                .error(R.drawable.baseline_error_24)
                .circleCrop()
                .into(userDetailImage)

            userDetailName.text = "${user.firstName} ${user.lastName}"
            userDetailGender.text = user.gender
            userDetailPhone.text = "+${user.phoneNumber.replace("-", " ")}"
            UserDetailAddress.text = user.address.address
        }

        user.stickers.forEach { sticker ->
            when (sticker) {
                "Fam" -> binding.userDetailStickersFam.visibility = View.VISIBLE
                "Ban" -> binding.userDetailStickersBan.visibility = View.VISIBLE
            }

        }

    }

    private fun onError(messageId: Int) {
        loading(false)
        showSnackBar(messageId) {
            loadData()
        }
    }

    private fun loading(isLoading: Boolean = true) {
        showSwiper(isLoading)
    }

    private fun showSwiper(isRefreshing: Boolean) {
        binding.userDetailLswiper.isRefreshing = isRefreshing
    }

    private fun showSnackBar(messageId: Int, retry: () -> Unit) {
        Snackbar.make(binding.userDetailLCoordinator, messageId, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                retry()
            }
            .show();
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}