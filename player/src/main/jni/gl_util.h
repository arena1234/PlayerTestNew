#ifndef JRDEXPORT_GL_UTIL_H
#define JRDEXPORT_GL_UTIL_H

#include <GLES/gl.h>
#include <GLES/glext.h>
#include <math.h>

#ifndef SHOW_MODE
#define SHOW_MODE
#define SHOW_MODE_ORIGINAL              0
#define SHOW_MODE_SPHERE_FRONT          1
#define SHOW_MODE_SPHERE_FRONT_BACK     2
#define SHOW_MODE_SPHERE_UP             3
#define SHOW_MODE_SPHERE_DOWN           4
#define SHOW_MODE_SPHERE_VR             5
#define SHOW_MODE_CYLINDER_UP_DOWN      6
#define SHOW_MODE_PLANE_UP_DOWN         7

#define RESOLUTION_4K                   0
#define RESOLUTION_1080P                1
#define RESOLUTION_720P                 2

#endif  //SHOW_MODE

void gluPerspective(GLfloat fovy, GLfloat aspect, GLfloat zNear, GLfloat zFar);
void gluLookAt(
        GLfloat eyeX, GLfloat eyeY, GLfloat eyeZ,
        GLfloat centerX, GLfloat centerY, GLfloat centerZ,
        GLfloat upX, GLfloat upY, GLfloat upZ);
void draw(GLint textureId);
void drawForOES(GLint textureId);
GLint createTextureIDForOES();
GLint createTextureID();
GLboolean setShowMode(GLint mode, GLint resolution, GLboolean isLeft);
GLfloat getDegreeX(GLboolean isLeft);
GLfloat getDegreeY(GLboolean isLeft);
GLfloat getDegreeZ(GLboolean isLeft);
GLfloat getFov(GLboolean isLeft);
void setDegreeX(GLfloat x, GLboolean isLeft);
void setDegreeY(GLfloat y, GLboolean isLeft);
void setDegreeZ(GLfloat z, GLboolean isLeft);
void setFov(GLfloat fov, GLfloat ratio, GLboolean isLeft);
void resetDegree();

#endif //JRDEXPORT_GL_UTIL_H
