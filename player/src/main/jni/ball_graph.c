#include <stdio.h>
#include <jni.h>
#include "ball_graph.h"
#include "aes/aes.h"
#include "native_log.h"

GLint mTextureId = -1;
jboolean isHaveLicence = JNI_FALSE;
GLint viewWidth = 0;
GLint viewHeight = 0;

GLint onSurfaceCreated(jboolean singlePic) {
    glClearColor(0, 0, 0, 0.0);
    glClearDepthf(1.0);
    glEnable(GL_DEPTH_TEST);
    if (singlePic) {
        mTextureId = createTextureID();
    } else {
        mTextureId = createTextureIDForOES();
    }
    return mTextureId;
}

void onSurfaceChanged(GLint width, GLint height) {
    viewWidth = width;
    viewHeight = height;
    glViewport(0, 0, viewWidth, viewHeight);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(getFov(GL_TRUE), (GLfloat) viewWidth / (GLfloat) viewHeight, 0.1, 100);
    //gluLookAt(0, 0, 0,    0, 0, 1,    0, 1, 0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    LOGD("view width=%d, height=%d", viewWidth, viewHeight);
}

void onDrawFrame(GLfloat degreeX, GLfloat degreeY, GLfloat degreeZ, GLfloat fov, GLboolean isLeft) {
    setFov(fov, (GLfloat) viewWidth / (GLfloat) viewHeight, isLeft);
    setDegreeX(degreeX, isLeft);
    setDegreeY(degreeY, isLeft);
    setDegreeZ(degreeZ, isLeft);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(getFov(isLeft), (GLfloat) viewWidth / (GLfloat) viewHeight, 0.1, 100);
    gluLookAt(0, 0, 0, 0, 0, 1, 0, 1, 0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glRotatef(getDegreeY(isLeft), 1, 0, 0);
    glRotatef(getDegreeX(isLeft), 0, 1, 0);
    glRotatef(getDegreeZ(isLeft), 0, 0, 1);
    glClearColor(0, 0, 0, 0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
}

jstring aesEncode(JNIEnv *env, jstring str) {
    jboolean isCopy;
    const char *id = (*env)->GetStringUTFChars(env, str, &isCopy);
    char ciphertext_0[128] = {0};
    encode(id, ciphertext_0);
    return (*env)->NewStringUTF(env, ciphertext_0);
}

jboolean aesDecode(JNIEnv *env, jstring str1, jstring str2, jstring r1, jstring r2) {
    jboolean isCopy;
    const char *hardId = (*env)->GetStringUTFChars(env, str1, &isCopy);
    const char *androidId = (*env)->GetStringUTFChars(env, str2, &isCopy);
    const char *result1 = (*env)->GetStringUTFChars(env, r1, &isCopy);
    const char *result2 = (*env)->GetStringUTFChars(env, r2, &isCopy);
    if(InvCipher_server(hardId, androidId, result1, result2) == 0){
        isHaveLicence = JNI_TRUE;
    } else {
        isHaveLicence = JNI_FALSE;
    }
    return isHaveLicence;
}

jboolean hasLicence() {
    return isHaveLicence;
}