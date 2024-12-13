package com.application.isyara.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.UpdateProfileResponse
import com.application.isyara.data.repository.ProfileRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<Result<ProfileData>>(Result.Idle)
    val profileState: StateFlow<Result<ProfileData>> = _profileState

    private val _updateProfileState = MutableStateFlow<Result<UpdateProfileResponse>>(Result.Idle)
    val updateProfileState: StateFlow<Result<UpdateProfileResponse>> = _updateProfileState

    fun fetchProfile() {
        viewModelScope.launch {
            profileRepository.getProfile().collect { result ->
                _profileState.value = result
            }
        }
    }

    fun updateProfile(
        file: MultipartBody.Part?,
        fullname: String,
        bio: String
    ) {
        viewModelScope.launch {
            profileRepository.updateProfile(file, fullname, bio).collect { result ->
                _updateProfileState.value = result
            }
        }
    }

    fun resetUpdateProfileState() {
        _updateProfileState.value = Result.Idle
    }
}
