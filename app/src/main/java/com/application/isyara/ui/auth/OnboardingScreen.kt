package com.application.isyara.ui.auth

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.application.isyara.navigation.NavRoute
import com.application.isyara.utils.auth.OnboardingPage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = OnboardingPage.pages
    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 0) {
            delay(5000L)
            coroutineScope.launch {
                pagerState.animateScrollToPage(1)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f),
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Crossfade(
                    targetState = page,
                    animationSpec = tween(durationMillis = 500), label = ""
                ) { currentPage ->
                    OnboardingPageContent(page = pages[currentPage])
                }

                // Indikator halaman
                Spacer(modifier = Modifier.height(16.dp))
                PageIndicator(
                    currentPage = pagerState.currentPage,
                    pageCount = pages.size
                )
            }
        }

        // Navigasi Lanjut untuk halaman pertama
        if (pagerState.currentPage == 0) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Lanjut")
            }
        } else if (pagerState.currentPage == pages.lastIndex) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        // Navigasi ke halaman login
                        navController.navigate(NavRoute.Login.route)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Masuk")
                }
                Button(
                    onClick = {
                        // Navigasi ke halaman register
                        navController.navigate(NavRoute.Register.route)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = "Belum punya akun? daftar")
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun PageIndicator(currentPage: Int, pageCount: Int) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until pageCount) {
            val size = animateFloatAsState(
                targetValue = if (i == currentPage) 12f else 8f,
                label = "PageIndicatorSize"
            ).value

            val positionOffset = animateDpAsState(
                targetValue = if (i == currentPage) 0.dp else 4.dp,
                label = "PageIndicatorPosition"
            ).value

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = positionOffset,
                        vertical = 4.dp
                    )
                    .size(size.dp)
                    .background(
                        color = if (i == currentPage) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(navController = rememberNavController())
}
