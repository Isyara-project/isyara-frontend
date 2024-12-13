package com.application.isyara.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J,\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000b0\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/application/isyara/data/repository/ProfileRepository;", "", "apiService", "Lcom/application/isyara/data/remote/ApiService;", "sessionManager", "Lcom/application/isyara/data/local/SessionManager;", "gson", "Lcom/google/gson/Gson;", "(Lcom/application/isyara/data/remote/ApiService;Lcom/application/isyara/data/local/SessionManager;Lcom/google/gson/Gson;)V", "getProfile", "Lkotlinx/coroutines/flow/Flow;", "Lcom/application/isyara/utils/state/Result;", "Lcom/application/isyara/data/model/ProfileData;", "parseError", "", "exception", "Lretrofit2/HttpException;", "updateProfile", "Lcom/application/isyara/data/model/UpdateProfileResponse;", "file", "Lokhttp3/MultipartBody$Part;", "fullname", "bio", "app_debug"})
public final class ProfileRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.remote.ApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.data.local.SessionManager sessionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public ProfileRepository(@com.application.isyara.data.di.RetrofitMain()
    @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.remote.ApiService apiService, @org.jetbrains.annotations.NotNull()
    com.application.isyara.data.local.SessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        super();
    }
    
    /**
     * Mendapatkan profil pengguna.
     *
     * @return [Flow] yang mengemisi [Result] dengan [ProfileData].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.ProfileData>> getProfile() {
        return null;
    }
    
    /**
     * Memperbarui profil pengguna.
     *
     * @param file File gambar profil (opsional).
     * @param fullname Nama lengkap pengguna.
     * @param bio Biografi pengguna.
     * @return [Flow] yang mengemisi [Result] dengan [UpdateProfileResponse].
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.application.isyara.utils.state.Result<com.application.isyara.data.model.UpdateProfileResponse>> updateProfile(@org.jetbrains.annotations.Nullable()
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull()
    java.lang.String fullname, @org.jetbrains.annotations.NotNull()
    java.lang.String bio) {
        return null;
    }
    
    /**
     * Menguraikan pesan kesalahan dari [HttpException].
     *
     * @param exception [HttpException] yang dilemparkan.
     * @return Pesan kesalahan yang diuraikan.
     */
    private final java.lang.String parseError(retrofit2.HttpException exception) {
        return null;
    }
}