package com.application.isyara.ui.main.settings

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.application.isyara.R
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.utils.settings.MultipartData
import com.application.isyara.utils.settings.toMultipartData
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.dashboard.ProfileViewModel
import timber.log.Timber


@Composable
fun EditAccountScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    // State untuk gambar profil yang dipilih
    var profileImageData by remember { mutableStateOf<MultipartData?>(null) }

    // Launcher untuk memilih gambar
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                // Validasi tipe dan ukuran file
                val isValid = validateImage(context, uri)
                if (isValid) {
                    val multipartData = uri.toMultipartData(context, "file")
                    if (multipartData != null) {
                        profileImageData = multipartData
                        Timber.d("Selected Image URI: ${multipartData.file.absolutePath}")
                        Toast.makeText(context, "Gambar dipilih.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Gagal memproses gambar.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        context,
                        "File harus berupa gambar (.jpg, .jpeg, .png) dan ukuran maksimal 5MB.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    )

    // Mengumpulkan state dari ViewModel
    val profileState by profileViewModel.profileState.collectAsState()
    val updateProfileState by profileViewModel.updateProfileState.collectAsState()

    // Mengambil profil saat composable pertama kali dimuat
    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()
    }

    // Menangani hasil dari update profil
    LaunchedEffect(updateProfileState) {
        when (updateProfileState) {
            is Result.Success -> {
                Toast.makeText(context, "Profil berhasil diperbarui.", Toast.LENGTH_SHORT).show()
                profileViewModel.resetUpdateProfileState()
                navController.popBackStack()

                // Hapus file sementara setelah berhasil upload
                profileImageData?.file?.delete()
                profileImageData = null

                // Mengambil profil lagi untuk memperbarui UI
                profileViewModel.fetchProfile()
            }

            is Result.Error -> {
                Toast.makeText(
                    context,
                    (updateProfileState as Result.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                profileViewModel.resetUpdateProfileState()
            }

            else -> {}
        }
    }

    // Layout utama
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
        ) {
            // Header
            AppHeaderMain(
                title = "Edit Akun",
                onBackClick = { navController.popBackStack() },
                backgroundColor = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF008E9B),
                        Color(0xFF00B4D8)
                    )
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (profileState) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF008E9B))
                    }
                }

                is Result.Success -> {
                    val profileData = (profileState as Result.Success).data
                    LaunchedEffect(profileData) {
                        Timber.d("Fetched Profile Data: $profileData")
                        Timber.d("Picture URL: ${profileData.picture}")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(Color(0xFFE1F5FE), CircleShape)
                                .border(2.dp, Color(0xFF008E9B), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            val correctedPictureUrl = profileData.picture?.replace(
                                "https://storage.cloud.google.com/",
                                "https://storage.googleapis.com/"
                            )

                            val pictureUrlWithTimestamp = correctedPictureUrl?.let {
                                "$it?timestamp=${System.currentTimeMillis()}"
                            }

                            val imageModel = profileImageData?.file ?: pictureUrlWithTimestamp

                            LaunchedEffect(imageModel) {
                                Timber.d("AsyncImage Model: $imageModel")
                            }

                            AsyncImage(
                                model = imageModel,
                                contentDescription = "Foto Profil",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(Color.Gray),
                                placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                                error = painterResource(id = R.drawable.ic_error),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    // Tombol untuk mengganti foto profil
                    TextButton(
                        onClick = {
                            imagePickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Ganti Foto Profil",
                            color = Color(0xFF008E9B)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol untuk menyimpan perubahan
                    Button(
                        onClick = {
                            val multipartData = profileImageData
                            val filePart = multipartData?.part

                            if (filePart == null) {
                                Toast.makeText(
                                    context,
                                    "Silakan pilih foto profil terlebih dahulu.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            profileViewModel.updateProfile(
                                file = filePart
                            )
                        },
                        enabled = updateProfileState !is Result.Loading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Simpan Perubahan",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                            if (updateProfileState is Result.Loading) {
                                Spacer(modifier = Modifier.width(8.dp))
                                CircularProgressIndicator(
                                    color = Color.White,
                                    strokeWidth = 2.dp,
                                    modifier = Modifier
                                        .size(16.dp)
                                )
                            }
                        }
                    }
                }

                is Result.Error -> {
                    // Menampilkan pesan kesalahan saat profil gagal dimuat
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = (profileState as Result.Error).message,
                            color = Color.Red,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Button(
                            onClick = { profileViewModel.fetchProfile() },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
                        ) {
                            Text(text = "Coba Lagi", color = Color.White)
                        }
                    }
                }

                else -> {
                }
            }
        }
    }
}

/**
 * Fungsi untuk memvalidasi tipe dan ukuran gambar.
 * Mengembalikan true jika valid, false jika tidak.
 */
fun validateImage(context: android.content.Context, uri: Uri): Boolean {
    val contentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri) ?: return false
    val isValidType = mimeType in listOf("image/jpeg", "image/png", "image/jpg")
    val cursor = contentResolver.query(uri, null, null, null, null)
    var sizeInBytes: Long = 0
    cursor?.use {
        if (it.moveToFirst()) {
            val sizeIndex = it.getColumnIndex(android.provider.OpenableColumns.SIZE)
            if (sizeIndex != -1) {
                sizeInBytes = it.getLong(sizeIndex)
            }
        }
    }

    val sizeInMB = sizeInBytes / (1024 * 1024)
    val isValidSize = sizeInMB <= 5

    return isValidType && isValidSize
}