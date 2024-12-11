package com.application.isyara.utils.translate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/application/isyara/utils/translate/LandmarkerListener;", "", "onError", "", "error", "", "errorCode", "", "onResults", "resultBundle", "Lcom/application/isyara/utils/translate/ResultBundle;", "app_debug"})
public abstract interface LandmarkerListener {
    
    public abstract void onResults(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.translate.ResultBundle resultBundle);
    
    public abstract void onError(@org.jetbrains.annotations.NotNull()
    java.lang.String error, int errorCode);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}