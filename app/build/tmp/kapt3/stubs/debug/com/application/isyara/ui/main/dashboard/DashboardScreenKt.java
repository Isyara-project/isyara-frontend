package com.application.isyara.ui.main.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\b\u0010\b\u001a\u00020\u0001H\u0007\u001a$\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a,\u0010\f\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001aL\u0010\u0013\u001a\u00020\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000eH\u0007\u001a\u0012\u0010\"\u001a\u00020\u00012\b\b\u0002\u0010#\u001a\u00020$H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006%"}, d2 = {"DashboardScreen", "", "onSearchClick", "Lkotlin/Function0;", "navController", "Landroidx/navigation/NavController;", "profileViewModel", "Lcom/application/isyara/viewmodel/dashboard/ProfileViewModel;", "HotNewsCarousel", "LogoutConfirmationDialog", "onConfirm", "onDismiss", "ProfileCardSmall", "fullName", "", "pictureUrl", "viewModel", "Lcom/application/isyara/viewmodel/auth/AuthViewModel;", "QuickAccessCard", "QuickAccessIcon", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "label", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentDescription", "onClick", "QuickAccessIcon-Bx497Mc", "(Landroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;JLjava/lang/String;Lkotlin/jvm/functions/Function0;)V", "StatisticItem", "title", "value", "UsageStatistics", "usageStatisticViewModel", "Lcom/application/isyara/ui/viewmodel/UsageStatisticViewModel;", "app_debug"})
public final class DashboardScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSearchClick, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.application.isyara.viewmodel.dashboard.ProfileViewModel profileViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ProfileCardSmall(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    java.lang.String fullName, @org.jetbrains.annotations.Nullable()
    java.lang.String pictureUrl, @org.jetbrains.annotations.NotNull()
    com.application.isyara.viewmodel.auth.AuthViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LogoutConfirmationDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HotNewsCarousel() {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void UsageStatistics(@org.jetbrains.annotations.NotNull()
    com.application.isyara.ui.viewmodel.UsageStatisticViewModel usageStatisticViewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StatisticItem(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuickAccessCard(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
    }
}