package com.application.isyara.utils.translate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/application/isyara/utils/translate/Dictionary;", "", "trie", "Lcom/application/isyara/utils/translate/Trie;", "(Lcom/application/isyara/utils/translate/Trie;)V", "insert", "", "word", "", "isWord", "", "Companion", "app_debug"})
public final class Dictionary {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.translate.Trie trie = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.utils.translate.Dictionary.Companion Companion = null;
    
    public Dictionary(@org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.translate.Trie trie) {
        super();
    }
    
    /**
     * Menambahkan kata ke kamus.
     */
    public final void insert(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
    }
    
    /**
     * Mengecek apakah kata ada dalam kamus.
     */
    public final boolean isWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/application/isyara/utils/translate/Dictionary$Companion;", "", "()V", "loadFromAssets", "Lcom/application/isyara/utils/translate/Dictionary;", "context", "Landroid/content/Context;", "assetFileName", "", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Memuat kamus dari file assets.
         * @param context Context untuk mengakses assets.
         * @param assetFileName Nama file kamus di assets.
         * @return Instance Dictionary.
         */
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Object loadFromAssets(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String assetFileName, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super com.application.isyara.utils.translate.Dictionary> $completion) {
            return null;
        }
    }
}