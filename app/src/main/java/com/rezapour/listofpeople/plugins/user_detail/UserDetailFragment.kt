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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rezapour.listofpeople.R
import com.rezapour.listofpeople.assets.AppConstants
import com.rezapour.listofpeople.databinding.FragmentUserDetailBinding
import com.rezapour.listofpeople.models.User
import com.rezapour.listofpeople.util.ImageUtil
import com.rezapour.listofpeople.util.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.MessageFormat

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserDetailViewModel by viewModels()
    private val args: UserDetailFragmentArgs by navArgs()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setupFragment()
    }

    private fun setupFragment() {
        subscribeToViewModel()
        setupUi()
        loadData()
    }

    private fun setupUi() {
        with(binding) {
            userDetailLswiper.setOnRefreshListener {
                loadData()
            }

            userDetailBackButton.setOnClickListener {
                navController.popBackStack()
            }

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

    private fun onSuccess(user: User) {
        loading(false)
        with(binding) {
            ImageUtil.loadImage(
                context = requireContext(),
                url = MessageFormat.format(
                    AppConstants.STATIC_MAP_URL,
                    user.currentLongitude,
                    user.currentLatitude
                ),
                error = requireContext().getDrawable(R.drawable.baseline_error_24)!!,
                imageView = userDetailLocation
            )

            ImageUtil.loadImage(
                context = requireContext(),
                url = user.imageUrl,
                error = ImageUtil.getAvatar(binding.root.context, user.firstName),
                imageView = userDetailImage
            )

            userDetailName.text = user.fullName
            userDetailGender.text = user.gender
            userDetailPhone.text = "+${user.phoneNumber.replace("-", " ")}"
            userDetailAddress.text = user.address.address
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