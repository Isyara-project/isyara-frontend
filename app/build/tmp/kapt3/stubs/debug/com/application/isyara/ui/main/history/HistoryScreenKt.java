package com.application.isyara.ui.main.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001e\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007\u001a\u001e\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00102\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u00a8\u0006\u0014"}, d2 = {"DownloadedDictionaryHistoryCard", "", "item", "Lcom/application/isyara/utils/history/HistoryItem$DownloadedDictionary;", "onDelete", "Lkotlin/Function0;", "DownloadedDictionaryPictureHistoryCard", "Lcom/application/isyara/utils/history/HistoryItem$DownloadedDictionaryPicture;", "HistoryCard", "Lcom/application/isyara/utils/history/HistoryItem;", "HistoryScreen", "navController", "Landroidx/navigation/NavController;", "viewModel", "Lcom/application/isyara/viewmodel/history/HistoryViewModel;", "TranslationHistoryCard", "Lcom/application/isyara/utils/history/HistoryItem$Translation;", "formatTimestamp", "", "", "app_debug"})
public final class HistoryScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryScreen(@org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController, @org.jetbrains.annotations.NotNull()
    com.application.isyara.viewmodel.history.HistoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HistoryCard(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.history.HistoryItem item, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TranslationHistoryCard(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.history.HistoryItem.Translation item, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DownloadedDictionaryHistoryCard(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.history.HistoryItem.DownloadedDictionary item, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DownloadedDictionaryPictureHistoryCard(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.history.HistoryItem.DownloadedDictionaryPicture item, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatTimestamp(long $this$formatTimestamp) {
        return null;
    }
}