package com.application.isyara.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ProfileData
import com.application.isyara.data.model.UpdateProfileResponse
import com.application.isyara.data.repository.ProfileRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profileState = MutableStateFlow<Result<ProfileData>>(Result.Idle)
    val profileState: StateFlow<Result<ProfileData>> = _profileState.asStateFlow()

    private val _updateProfileState = MutableStateFlow<Result<UpdateProfileResponse>>(Result.Idle)
    val updateProfileState: StateFlow<Result<UpdateProfileResponse>> =
        _updateProfileState.asStateFlow()

    /**
     * Mengambil profil pengguna dari repository dan memperbarui state.
     */
    fun fetchProfile() {
        profileRepository.getProfile()
            .onEach { result ->
                _profileState.value = result
            }
            .catch { e ->
                // Menangani kesalahan yang tidak tertangani di repository
                _profileState.value = Result.Error("Terjadi kesalahan: ${e.localizedMessage}")
            }
            .launchIn(viewModelScope)
    }

    /**
     * Memperbarui profil pengguna melalui repository dan memperbarui state.
     *
     * @param file File gambar profil (opsional).
     * @param fullname Nama lengkap pengguna.
     * @param bio Biografi pengguna.
     */
    fun updateProfile(
        file: MultipartBody.Part?,
        fullname: String,
        bio: String
    ) {
        profileRepository.updateProfile(file, fullname, bio)
            .onEach { result ->
                _updateProfileState.value = result
            }
            .catch { e ->
                // Menangani kesalahan yang tidak tertangani di repository
                _updateProfileState.value = Result.Error("Terjadi kesalahan: ${e.localizedMessage}")
            }
            .launchIn(viewModelScope)
    }

    /**
     * Mengatur ulang state updateProfile menjadi Idle.
     */
    fun resetUpdateProfileState() {
        _updateProfileState.value = Result.Idle
    }

    init {
        fetchProfile()
    }
}
