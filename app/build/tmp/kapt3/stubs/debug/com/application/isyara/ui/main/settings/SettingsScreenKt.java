package com.application.isyara.ui.main.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a \u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a0\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007\u001a\u001a\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007\u00a8\u0006\u0016"}, d2 = {"LogoutConfirmationDialog", "", "onConfirm", "Lkotlin/Function0;", "onDismiss", "ProfileSection", "viewModel", "Lcom/application/isyara/viewmodel/dashboard/ProfileViewModel;", "onEditAccountClick", "SettingsOption", "title", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "onClick", "isDestructive", "", "SettingsScreen", "navController", "Landroidx/navigation/NavController;", "authViewModel", "Lcom/application/isyara/viewmodel/auth/AuthViewModel;", "app_debug"})
public final class SettingsScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.application.isyara.viewmodel.auth.AuthViewModel authViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileSection(@org.jetbrains.annotations.NotNull()
    com.application.isyara.viewmodel.dashboard.ProfileViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onEditAccountClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SettingsOption(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.graphics.vector.ImageVector icon, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, boolean isDestructive) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LogoutConfirmationDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
}