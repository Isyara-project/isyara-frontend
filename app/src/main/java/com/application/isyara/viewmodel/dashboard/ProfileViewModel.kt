package com.application.isyara.viewmodel.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.UpdateProfileResponse
import com.application.isyara.data.repository.ProfileRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<Result<ProfileData>>(Result.Idle)
    val profileState: StateFlow<Result<ProfileData>> = _profileState.asStateFlow()

    private val _updateProfileState = MutableStateFlow<Result<UpdateProfileResponse>>(Result.Idle)
    val updateProfileState: StateFlow<Result<UpdateProfileResponse>> =
        _updateProfileState.asStateFlow()

    /**
     * Mengambil data profil pengguna dari repository.
     */
    fun fetchProfile() {
        viewModelScope.launch {
            repository.getProfile()
                .collect { result ->
                    _profileState.value = result
                }
        }
    }

    /**
     * Memperbarui foto profil pengguna melalui repository.
     *
     * @param file File gambar profil (opsional).
     */
    fun updateProfile(
        file: MultipartBody.Part?
    ) {
        viewModelScope.launch {
            repository.updateProfile(file)
                .collect { result ->
                    _updateProfileState.value = result
                }
        }
    }

    /**
     * Mengatur ulang state pembaruan profil menjadi Idle.
     */
    fun resetUpdateProfileState() {
        _updateProfileState.value = Result.Idle
    }
}
