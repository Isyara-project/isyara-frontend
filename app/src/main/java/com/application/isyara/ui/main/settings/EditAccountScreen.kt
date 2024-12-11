package com.application.isyara.ui.main.settings

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.main.AppHeaderMain
import com.application.isyara.viewmodel.main.ProfileViewModel

@Composable
fun EditAccountScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var newName by remember { mutableStateOf("") }
    var newBio by remember { mutableStateOf("") }

    val profileState by profileViewModel.profile.collectAsState()
    val updateProfileState by profileViewModel.updateProfileState.collectAsState()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                profileImageUri = uri
            }
        }
    )

    // Ambil profil saat pertama kali masuk ke layar
    LaunchedEffect(Unit) {
        profileViewModel.fetchProfile()  // untuk mengambil profil
    }

    when (profileState) {
        is Result.Idle -> {
            Toast.makeText(LocalContext.current, "Memuat profil data...", Toast.LENGTH_SHORT).show()
        }

        is Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Error -> {
            Toast.makeText(LocalContext.current, "Gagal memuat profil", Toast.LENGTH_SHORT).show()
        }

        is Result.Success -> {
            val profileData = (profileState as Result.Success).data
            name = profileData.fullname // set nama dari profil yang diambil
            bio = profileData.bio ?: "Bio anda masih kosong"
        }
    }

    // Menampilkan status update profil
    when (updateProfileState) {
        is Result.Idle -> {
            Toast.makeText(LocalContext.current, "Menyimpan perubahan...", Toast.LENGTH_SHORT).show()
        }
        is Result.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Error -> {
            Toast.makeText(LocalContext.current, "Gagal memperbarui profil", Toast.LENGTH_SHORT)
                .show()
        }

        is Result.Success -> {
            val response = (updateProfileState as Result.Success).data
            Toast.makeText(LocalContext.current, response.message, Toast.LENGTH_SHORT).show()
        }
    }

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

        // Kolom untuk input Nama Lengkap Baru dan Bio Baru
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Menampilkan Nama Lengkap dari profil yang sudah ada
            TextField(
                value = name,
                onValueChange = {},
                label = { Text("Nama Lengkap") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

            // Kolom untuk memperbarui Nama Lengkap
            TextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Nama Lengkap Baru") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )

            // Kolom untuk memperbarui Bio
            TextField(
                value = bio,
                onValueChange = {},
                label = { Text("Bio") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = false
            )

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
                profileViewModel.updateProfile(context, profileImageUri, newName, newBio)
                navController.popBackStack()
            },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF008E9B))
        ) {
            Text(text = "Simpan Perubahan", fontSize = 16.sp, color = Color.White)
        }
    }
}
