#ifndef JNIPLAYER_COM_VR_PLAYER_NDK_NATIVEAPI_H
#define JNIPLAYER_COM_VR_PLAYER_NDK_NATIVEAPI_H

#include<jni.h>

#ifdef __cplusplus
extern"C" {
#endif

JNIEXPORT jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeSetShowMode
    (JNIEnv *, jobject, jint, jint, jboolean);
JNIEXPORT jint JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnSurfaceCreated
    (JNIEnv *, jobject, jboolean);
JNIEXPORT void JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnSurfaceChanged
    (JNIEnv *, jobject, jint, jint);
JNIEXPORT void JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnDrawFrame
    (JNIEnv *, jobject, jfloat, jfloat, jfloat, jfloat, jboolean);
JNIEXPORT void JNICALL Java_com_vr_player_ndk_NativeApi_nativeDraw
    (JNIEnv *, jobject, jint);
JNIEXPORT jstring JNICALL Java_com_vr_player_ndk_NativeApi_nativeAesEncode
    (JNIEnv *, jobject, jstring);
JNIEXPORT jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeAesDecode
    (JNIEnv *, jobject, jstring, jstring, jstring, jstring);
JNIEXPORT jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeHasLicence
    (JNIEnv *, jobject);
JNIEXPORT jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeX
    (JNIEnv *, jobject, jboolean);
JNIEXPORT jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeY
    (JNIEnv *, jobject, jboolean);
JNIEXPORT jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeZ
    (JNIEnv *, jobject, jboolean);
JNIEXPORT jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetFov
    (JNIEnv *, jobject, jboolean);
JNIEXPORT void JNICALL Java_com_vr_player_ndk_NativeApi_nativeResetDegree
    (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif //JNIPLAYER_COM_VR_PLAYER_NDK_NATIVEAPI_H
