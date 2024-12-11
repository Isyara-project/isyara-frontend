package com.application.isyara.viewmodel.main

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.ProfileUpdateResponse
import com.application.isyara.data.repository.ProfileRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profile = MutableStateFlow<Result<ProfileData>>(Result.Loading)
    val profile: StateFlow<Result<ProfileData>> get() = _profile
    private val _updateProfileState =
        MutableStateFlow<Result<ProfileUpdateResponse>>(Result.Loading)
    val updateProfileState: StateFlow<Result<ProfileUpdateResponse>> get() = _updateProfileState

    fun fetchProfile() {
        viewModelScope.launch {
            profileRepository.getProfile().collectLatest { result ->
                _profile.value = result
            }
        }
    }

    fun updateProfile(context: Context, fileUri: Uri?, fullname: String, bio: String) {
        viewModelScope.launch {
            profileRepository.updateProfile(context, fileUri, fullname, bio)
                .collectLatest { result ->
                    _updateProfileState.value = result
                }
        }
    }
}
