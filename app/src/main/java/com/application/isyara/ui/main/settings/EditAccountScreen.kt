package com.application.isyara.ui.main.settings

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.application.isyara.utils.settings.toMultipartBodyPart
import com.application.isyara.utils.state.Result
import com.application.isyara.viewmodel.main.ProfileViewModel

@Composable
fun EditAccountScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    // State untuk gambar profil yang dipilih
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }

    // State untuk input nama dan bio
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    // Launcher untuk memilih gambar
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                // Validasi tipe dan ukuran file
                val isValid = validateImage(context, uri)
                if (isValid) {
                    profileImageUri = uri
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

            // Konten berdasarkan state profil
            when (profileState) {
                is Result.Loading -> {
                    // Indikator loading saat profil sedang dimuat
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFF008E9B))
                    }
                }

                is Result.Success -> {
                    val profileData = (profileState as Result.Success).data

                    // Mengisi state input dengan data profil saat ini
                    LaunchedEffect(profileData) {
                        name = profileData.fullname
                        bio = profileData.bio ?: ""
                    }

                    // UI untuk Foto Profil
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color(0xFFE1F5FE), CircleShape)
                                .border(2.dp, Color(0xFF008E9B), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            profileImageUri?.let { uri ->
                                AsyncImage(
                                    model = uri,
                                    contentDescription = "Foto Profil",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .background(Color.Gray),
                                    placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                                    error = painterResource(id = R.drawable.ic_error),
                                    contentScale = ContentScale.Crop
                                )
                            } ?: profileData.picture?.let { pictureUrl ->
                                AsyncImage(
                                    model = pictureUrl,
                                    contentDescription = "Foto Profil",
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .background(Color.Gray),
                                    placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                                    error = painterResource(id = R.drawable.ic_error),
                                    contentScale = ContentScale.Crop
                                )
                            } ?: Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Foto Profil",
                                modifier = Modifier.size(80.dp),
                                tint = Color(0xFF008E9B)
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

                    Spacer(modifier = Modifier.height(16.dp))

                    // Kolom untuk input Nama Lengkap dan Bio
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Input Nama Lengkap
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Nama Lengkap") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color(0xFF008E9B),
                                unfocusedIndicatorColor = Color.Gray
                            )
                        )

                        // Input Bio
                        TextField(
                            value = bio,
                            onValueChange = { bio = it },
                            label = { Text("Bio") },
                            singleLine = false,
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3,
                            placeholder = { Text("Ceritakan sedikit tentang dirimu...") },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color(0xFF008E9B),
                                unfocusedIndicatorColor = Color.Gray
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Tombol untuk menyimpan perubahan
                    Button(
                        onClick = {
                            // Validasi input
                            if (name.isBlank()) {
                                Toast.makeText(
                                    context,
                                    "Nama Lengkap tidak boleh kosong.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            // Menyiapkan MultipartBody.Part untuk gambar
                            val filePart = profileImageUri?.let { uri ->
                                uri.toMultipartBodyPart(context, "file")
                            }

                            // Menyiapkan data profil yang diperbarui
                            val updatedName = name
                            val updatedBio = bio

                            profileViewModel.updateProfile(
                                file = filePart,
                                fullname = updatedName,
                                bio = updatedBio
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
                    // State Idle atau lainnya, bisa dibiarkan kosong atau diisi sesuai kebutuhan
                }
            }
        }
    }
}

/**
 * Fungsi untuk memvalidasi tipe dan ukuran gambar.
 * Mengembalikan true jika valid, false jika tidak.
 */
fun validateImage(context: Context, uri: Uri): Boolean {
    val contentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri) ?: return false
    val isValidType = mimeType in listOf("image/jpeg", "image/png", "image/jpg")
    val cursor = contentResolver.query(uri, null, null, null, null)
    var sizeInBytes: Long = 0
    cursor?.use {
        if (it.moveToFirst()) {
            val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)
            if (sizeIndex != -1) {
                sizeInBytes = it.getLong(sizeIndex)
            }
        }
    }

    val sizeInMB = sizeInBytes / (1024 * 1024)
    val isValidSize = sizeInMB <= 5

    return isValidType && isValidSize
}
