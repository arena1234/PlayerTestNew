#include "com_vr_player_ndk_NativeApi.h"
#include "ball_graph.h"
extern "C" {
jboolean isSinglePicture = JNI_FALSE;

jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeSetShowMode
        (JNIEnv *env, jobject thiz, jint showMode, jint resolutionRadio, jboolean isLeft) {
    return setShowMode(showMode, resolutionRadio, isLeft);
}

jint JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnSurfaceCreated
        (JNIEnv *env, jobject thiz, jboolean singlePicture) {
    isSinglePicture = singlePicture;
    return onSurfaceCreated(singlePicture);
}

void JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnSurfaceChanged
        (JNIEnv *env, jobject thiz, jint width, jint height) {
    onSurfaceChanged(width, height);
}

void JNICALL Java_com_vr_player_ndk_NativeApi_nativeOnDrawFrame
        (JNIEnv *env, jobject thiz, jfloat degreeX, jfloat degreeY, jfloat degreeZ, jfloat fov,
         jboolean isLeft) {
    onDrawFrame(degreeX, degreeY, degreeZ, fov, isLeft);
}

void JNICALL Java_com_vr_player_ndk_NativeApi_nativeDraw
        (JNIEnv *env, jobject thiz, jint textureId) {
    if (isSinglePicture) {
        draw(textureId);
    } else {
        drawForOES(textureId);
    }
}

jstring JNICALL Java_com_vr_player_ndk_NativeApi_nativeAesEncode(JNIEnv *env, jobject thiz,
                                                                 jstring id) {
    return aesEncode(env, id);
}

jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeAesDecode(JNIEnv *env, jobject thiz,
                                                                  jstring hardId, jstring androidId,
                                                                  jstring result1,
                                                                  jstring result2) {
    return aesDecode(env, hardId, androidId, result1, result2);
}

jboolean JNICALL Java_com_vr_player_ndk_NativeApi_nativeHasLicence(JNIEnv *env, jobject thiz) {
    return hasLicence();
}

jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeX
        (JNIEnv *env, jobject thiz, jboolean isLeft) {
    return getDegreeX(isLeft);
}

jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeY
        (JNIEnv *env, jobject thiz, jboolean isLeft) {
    return getDegreeY(isLeft);
}

jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetDegreeZ
        (JNIEnv *env, jobject thiz, jboolean isLeft) {
    return getDegreeZ(isLeft);
}

jfloat JNICALL Java_com_vr_player_ndk_NativeApi_nativeGetFov
        (JNIEnv *env, jobject thiz, jboolean isLeft) {
    return getFov(isLeft);
}

void JNICALL Java_com_vr_player_ndk_NativeApi_nativeResetDegree
        (JNIEnv *env, jobject thiz) {
    return resetDegree();
}
};