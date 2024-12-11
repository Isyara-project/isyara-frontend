package com.application.isyara.utils.translate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u0000 92\u00020\u0001:\u00029:B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010\u001d\u001a\u00020\u00122\n\u0010\u001e\u001a\u00060\u001fj\u0002` H\u0002J\u0018\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0015H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\b\u0010&\u001a\u00020\u0012H\u0002J\u0010\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u000bH\u0002J\u0016\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-H\u0002J \u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u0002032\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u00104\u001a\u00020\u000b2\u0006\u00105\u001a\u00020+H\u0002J\b\u00106\u001a\u00020\u0012H\u0002J\u0006\u00107\u001a\u00020\u0012J\f\u00108\u001a\u000200*\u00020\u001aH\u0002R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/application/isyara/utils/translate/SignLanguageRecognizer;", "Ljava/lang/AutoCloseable;", "context", "Landroid/content/Context;", "listener", "Lcom/application/isyara/utils/translate/LandmarkerListener;", "config", "Lcom/application/isyara/utils/translate/SignLanguageRecognizer$Config;", "(Landroid/content/Context;Lcom/application/isyara/utils/translate/LandmarkerListener;Lcom/application/isyara/utils/translate/SignLanguageRecognizer$Config;)V", "classLabels", "", "", "[Ljava/lang/String;", "handLandmarker", "Lcom/google/mediapipe/tasks/vision/handlandmarker/HandLandmarker;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "close", "", "detectAsync", "mpImage", "Lcom/google/mediapipe/framework/image/MPImage;", "frameTime", "", "detectLiveStream", "imageProxy", "Landroidx/camera/core/ImageProxy;", "isFrontCamera", "", "handleDetectionError", "error", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "handleDetectionResult", "result", "Lcom/google/mediapipe/tasks/vision/handlandmarker/HandLandmarkerResult;", "input", "initializeHandLandmarker", "initializeTFLiteInterpreter", "loadModelFile", "Ljava/nio/MappedByteBuffer;", "modelPath", "preprocessLandmarks", "", "landmarks", "", "Lcom/google/mediapipe/tasks/components/containers/NormalizedLandmark;", "rotateAndFlipBitmap", "Landroid/graphics/Bitmap;", "bitmap", "rotationDegrees", "", "runInference", "inputCoords", "stopLiveStream", "stopTranslation", "createToBitmap", "Companion", "Config", "app_debug"})
public final class SignLanguageRecognizer implements java.lang.AutoCloseable {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private com.application.isyara.utils.translate.LandmarkerListener listener;
    @org.jetbrains.annotations.NotNull()
    private final com.application.isyara.utils.translate.SignLanguageRecognizer.Config config = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker handLandmarker;
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.Interpreter interpreter;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String[] classLabels;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DEFAULT_MODEL_ASSET_PATH = "hand_landmarker.task";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MODEL_ISYARA = "isyara.tflite";
    public static final int DELEGATE_CPU = 0;
    public static final float DEFAULT_HAND_DETECTION_CONFIDENCE = 0.8F;
    public static final float DEFAULT_HAND_TRACKING_CONFIDENCE = 0.8F;
    public static final float DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.8F;
    public static final int DEFAULT_NUM_HANDS = 1;
    public static final int OTHER_ERROR = 0;
    public static final int GPU_ERROR = 1;
    @org.jetbrains.annotations.NotNull()
    public static final com.application.isyara.utils.translate.SignLanguageRecognizer.Companion Companion = null;
    
    public SignLanguageRecognizer(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.application.isyara.utils.translate.LandmarkerListener listener, @org.jetbrains.annotations.NotNull()
    com.application.isyara.utils.translate.SignLanguageRecognizer.Config config) {
        super();
    }
    
    private final void initializeHandLandmarker() {
    }
    
    /**
     * Menginisialisasi TensorFlow Lite Interpreter
     */
    private final void initializeTFLiteInterpreter() {
    }
    
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    private final java.nio.MappedByteBuffer loadModelFile(java.lang.String modelPath) throws java.io.IOException {
        return null;
    }
    
    private final java.lang.String runInference(float[] inputCoords) {
        return null;
    }
    
    /**
     * Memproses frame dari live stream
     */
    public final void detectLiveStream(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy, boolean isFrontCamera) {
    }
    
    private final android.graphics.Bitmap createToBitmap(androidx.camera.core.ImageProxy $this$createToBitmap) {
        return null;
    }
    
    private final void detectAsync(com.google.mediapipe.framework.image.MPImage mpImage, long frameTime) {
    }
    
    private final void handleDetectionResult(com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult result, com.google.mediapipe.framework.image.MPImage input) {
    }
    
    private final void handleDetectionError(java.lang.RuntimeException error) {
    }
    
    /**
     * Preprocessing landmarks untuk inferensi
     */
    private final float[] preprocessLandmarks(java.util.List<? extends com.google.mediapipe.tasks.components.containers.NormalizedLandmark> landmarks) {
        return null;
    }
    
    private final android.graphics.Bitmap rotateAndFlipBitmap(android.graphics.Bitmap bitmap, int rotationDegrees, boolean isFrontCamera) {
        return null;
    }
    
    private final void stopLiveStream() {
    }
    
    @java.lang.Override()
    public void close() {
    }
    
    public final void stopTranslation() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/application/isyara/utils/translate/SignLanguageRecognizer$Companion;", "", "()V", "DEFAULT_HAND_DETECTION_CONFIDENCE", "", "DEFAULT_HAND_PRESENCE_CONFIDENCE", "DEFAULT_HAND_TRACKING_CONFIDENCE", "DEFAULT_MODEL_ASSET_PATH", "", "DEFAULT_NUM_HANDS", "", "DELEGATE_CPU", "GPU_ERROR", "MODEL_ISYARA", "OTHER_ERROR", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0007H\u00c6\u0003J\t\u0010-\u001a\u00020\tH\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\fH\u00c6\u0003J\t\u00100\u001a\u00020\u000eH\u00c6\u0003JY\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u00102\u001a\u00020\u00072\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\tH\u00d6\u0001J\t\u00105\u001a\u00020\u000eH\u00d6\u0001R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b\'\u0010(\u00a8\u00066"}, d2 = {"Lcom/application/isyara/utils/translate/SignLanguageRecognizer$Config;", "", "minHandDetectionConfidence", "", "minHandTrackingConfidence", "minHandPresenceConfidence", "isDetecting", "", "maxNumHands", "", "currentDelegate", "runningMode", "Lcom/google/mediapipe/tasks/vision/core/RunningMode;", "modelAssetPath", "", "(FFFZIILcom/google/mediapipe/tasks/vision/core/RunningMode;Ljava/lang/String;)V", "getCurrentDelegate", "()I", "setCurrentDelegate", "(I)V", "()Z", "setDetecting", "(Z)V", "getMaxNumHands", "setMaxNumHands", "getMinHandDetectionConfidence", "()F", "setMinHandDetectionConfidence", "(F)V", "getMinHandPresenceConfidence", "setMinHandPresenceConfidence", "getMinHandTrackingConfidence", "setMinHandTrackingConfidence", "getModelAssetPath", "()Ljava/lang/String;", "setModelAssetPath", "(Ljava/lang/String;)V", "getRunningMode", "()Lcom/google/mediapipe/tasks/vision/core/RunningMode;", "setRunningMode", "(Lcom/google/mediapipe/tasks/vision/core/RunningMode;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
    public static final class Config {
        private float minHandDetectionConfidence;
        private float minHandTrackingConfidence;
        private float minHandPresenceConfidence;
        private boolean isDetecting;
        private int maxNumHands;
        private int currentDelegate;
        @org.jetbrains.annotations.NotNull()
        private com.google.mediapipe.tasks.vision.core.RunningMode runningMode;
        @org.jetbrains.annotations.NotNull()
        private java.lang.String modelAssetPath;
        
        public Config(float minHandDetectionConfidence, float minHandTrackingConfidence, float minHandPresenceConfidence, boolean isDetecting, int maxNumHands, int currentDelegate, @org.jetbrains.annotations.NotNull()
        com.google.mediapipe.tasks.vision.core.RunningMode runningMode, @org.jetbrains.annotations.NotNull()
        java.lang.String modelAssetPath) {
            super();
        }
        
        public final float getMinHandDetectionConfidence() {
            return 0.0F;
        }
        
        public final void setMinHandDetectionConfidence(float p0) {
        }
        
        public final float getMinHandTrackingConfidence() {
            return 0.0F;
        }
        
        public final void setMinHandTrackingConfidence(float p0) {
        }
        
        public final float getMinHandPresenceConfidence() {
            return 0.0F;
        }
        
        public final void setMinHandPresenceConfidence(float p0) {
        }
        
        public final boolean isDetecting() {
            return false;
        }
        
        public final void setDetecting(boolean p0) {
        }
        
        public final int getMaxNumHands() {
            return 0;
        }
        
        public final void setMaxNumHands(int p0) {
        }
        
        public final int getCurrentDelegate() {
            return 0;
        }
        
        public final void setCurrentDelegate(int p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.mediapipe.tasks.vision.core.RunningMode getRunningMode() {
            return null;
        }
        
        public final void setRunningMode(@org.jetbrains.annotations.NotNull()
        com.google.mediapipe.tasks.vision.core.RunningMode p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getModelAssetPath() {
            return null;
        }
        
        public final void setModelAssetPath(@org.jetbrains.annotations.NotNull()
        java.lang.String p0) {
        }
        
        public Config() {
            super();
        }
        
        public final float component1() {
            return 0.0F;
        }
        
        public final float component2() {
            return 0.0F;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        public final boolean component4() {
            return false;
        }
        
        public final int component5() {
            return 0;
        }
        
        public final int component6() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.google.mediapipe.tasks.vision.core.RunningMode component7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.application.isyara.utils.translate.SignLanguageRecognizer.Config copy(float minHandDetectionConfidence, float minHandTrackingConfidence, float minHandPresenceConfidence, boolean isDetecting, int maxNumHands, int currentDelegate, @org.jetbrains.annotations.NotNull()
        com.google.mediapipe.tasks.vision.core.RunningMode runningMode, @org.jetbrains.annotations.NotNull()
        java.lang.String modelAssetPath) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}