package com.application.isyara.ui.main.settings

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var newName by remember { mutableStateOf("") }
    var newBio by remember { mutableStateOf("") }

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

    val profileState by profileViewModel.profileState.collectAsState()
    val updateProfileState by profileViewModel.updateProfileState.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()
    }

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

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
        ) {
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
                    profileImageUri?.let { uri: Uri? ->
                        AsyncImage(
                            model = uri,
                            contentDescription = "Foto Profil",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(Color.Gray),
                            placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                            contentScale = ContentScale.Crop
                        )
                    } ?: (profileState as? Result.Success)?.data?.picture?.let { pictureUrl ->
                        AsyncImage(
                            model = pictureUrl,
                            contentDescription = "Foto Profil",
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(Color.Gray),
                            placeholder = painterResource(id = R.drawable.ic_image_placeholder),
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

            // Kolom untuk input Nama Lengkap Baru dan Bio Baru
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    value = (profileState as? Result.Success)?.data?.fullname ?: "",
                    onValueChange = {},
                    label = { Text("Nama Lengkap") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Black,
                        disabledLabelColor = Color.Gray,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                // Kolom untuk memperbarui Nama Lengkap
                TextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Nama Lengkap Baru") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                TextField(
                    value = (profileState as? Result.Success)?.data?.bio ?: "",
                    onValueChange = {},
                    label = { Text("Bio") },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    maxLines = 3,
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Black,
                        disabledLabelColor = Color.Gray,
                        disabledIndicatorColor = Color.Transparent
                    )
                )

                // Kolom untuk memperbarui Bio
                TextField(
                    value = newBio,
                    onValueChange = { newBio = it },
                    label = { Text("Bio Baru") },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3,
                    placeholder = { Text("Ceritakan sedikit tentang dirimu...") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol untuk menyimpan perubahan
            Button(
                onClick = {
                    // Validasi input
                    if (newName.isBlank()) {
                        Toast.makeText(
                            context,
                            "Nama Lengkap Baru tidak boleh kosong.",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@Button
                    }

                    // Menyiapkan MultipartBody.Part untuk gambar
                    val filePart = profileImageUri?.let { uri ->
                        uri.toMultipartBodyPart(context, "file")
                    }

                    // Menyiapkan data profil yang diperbarui
                    val updatedName = newName
                    val updatedBio = newBio

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

    val inputStream = contentResolver.openInputStream(uri) ?: return false
    val sizeInBytes = inputStream.available().toLong()
    inputStream.close()
    val sizeInMB = sizeInBytes / (1024 * 1024)

    val isValidSize = sizeInMB <= 5

    return isValidType && isValidSize
}
