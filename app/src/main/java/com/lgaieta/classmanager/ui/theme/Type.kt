package com.lgaieta.classmanager.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.lgaieta.classmanager.R

val Montserrat = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = Montserrat),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = Montserrat),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = Montserrat),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = Montserrat),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = Montserrat),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = Montserrat),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = Montserrat),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = Montserrat),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = Montserrat),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = Montserrat),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = Montserrat),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = Montserrat),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = Montserrat),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = Montserrat),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = Montserrat)
)