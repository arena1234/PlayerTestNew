#ifndef JRDEXPORT_BALL_GRAPH_H
#define JRDEXPORT_BALL_GRAPH_H

#include <GLES/gl.h>
#include <GLES/glext.h>
#include <jni.h>
#include "gl_util.h"

extern jboolean isHaveLicence;
GLint onSurfaceCreated(GLboolean isSingle);
void onSurfaceChanged(GLint w, GLint h);
void onDrawFrame(GLfloat degreeX, GLfloat degreeY, GLfloat degreeZ, GLfloat zoom, GLboolean isLeft);
jstring aesEncode(JNIEnv* env, jstring str);
jboolean aesDecode(JNIEnv* env, jstring str1, jstring str2, jstring r1, jstring r2);
jboolean hasLicence();

#endif //JRDEXPORT_BALL_GRAPH_H
