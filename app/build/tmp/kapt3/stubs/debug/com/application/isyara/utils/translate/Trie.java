package com.application.isyara.utils.translate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/application/isyara/utils/translate/Trie;", "", "()V", "root", "Lcom/application/isyara/utils/translate/Trie$TrieNode;", "insert", "", "word", "", "remove", "", "current", "index", "", "search", "startsWith", "prefix", "TrieNode", "app_debug"})
public final class Trie {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.translate.Trie.TrieNode root = null;
    
    public Trie() {
        super();
    }
    
    /**
     * Menambahkan kata ke Trie.
     */
    public final void insert(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
    }
    
    /**
     * Mencari apakah kata ada dalam Trie.
     */
    public final boolean search(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return false;
    }
    
    /**
     * Mengecek apakah ada kata yang dimulai dengan prefix tertentu.
     */
    public final boolean startsWith(@org.jetbrains.annotations.NotNull()
    java.lang.String prefix) {
        return false;
    }
    
    /**
     * Menghapus kata dari Trie.
     * @return True jika node dapat dihapus, False jika tidak.
     */
    public final boolean remove(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return false;
    }
    
    /**
     * Helper rekursif untuk menghapus kata dari Trie.
     */
    private final boolean remove(com.application.isyara.utils.translate.Trie.TrieNode current, java.lang.String word, int index) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00000\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/application/isyara/utils/translate/Trie$TrieNode;", "", "()V", "children", "", "", "getChildren", "()Ljava/util/Map;", "isEndOfWord", "", "()Z", "setEndOfWord", "(Z)V", "app_debug"})
    static final class TrieNode {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Map<java.lang.Character, com.application.isyara.utils.translate.Trie.TrieNode> children = null;
        private boolean isEndOfWord = false;
        
        public TrieNode() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.Map<java.lang.Character, com.application.isyara.utils.translate.Trie.TrieNode> getChildren() {
            return null;
        }
        
        public final boolean isEndOfWord() {
            return false;
        }
        
        public final void setEndOfWord(boolean p0) {
        }
    }
}