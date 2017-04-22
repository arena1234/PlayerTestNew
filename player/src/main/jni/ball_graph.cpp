#include <jni.h>
#include "ball_graph.h"
#include "aes/licence.h"
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

jstring aesEncode(JNIEnv *env, jstring id) {
    jboolean isCopy;
    const char *str = env->GetStringUTFChars(id, &isCopy);
    char ciphertext_0[128] = {0};
    encode(str, ciphertext_0);
    return env->NewStringUTF(ciphertext_0);
}

jboolean aesDecode(JNIEnv *env, jstring str1, jstring str2, jstring r1, jstring r2) {
    if(str1 != NULL && str2 != NULL && r1 != NULL && r2 != NULL) {
        jboolean isCopy;
        const char *hardId = env->GetStringUTFChars(str1, &isCopy);
        const char *androidId = env->GetStringUTFChars(str2, &isCopy);
        const char *result1 = env->GetStringUTFChars(r1, &isCopy);
        const char *result2 = env->GetStringUTFChars(r2, &isCopy);
        isHaveLicence = isAllow(hardId, androidId, result1, result2);
    } else{
        isHaveLicence = JNI_FALSE;
    }
    return isHaveLicence;
}

jboolean hasLicence() {
    return isHaveLicence;
}