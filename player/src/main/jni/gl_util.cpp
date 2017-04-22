#include "gl_util.h"
#include "map/map.h"
#include "ball_graph.h"
#include "native_log.h"

const GLfloat PI = 3.141592;

GLint mMode = SHOW_MODE_ORIGINAL;
GLint mResolution = RESOLUTION_4K;

GLfloat mDegreeXMin = 0;
GLfloat mDegreeXMax = 0;
GLfloat mDegreeXFinalMin = 0;
GLfloat mDegreeXFinalMax = 0;
GLfloat mDegreeXDefault = 0;
GLfloat mDegreeX = 0;

GLfloat mDegreeYMin = 0;
GLfloat mDegreeYMax = 0;
GLfloat mDegreeYFinalMin = 0;
GLfloat mDegreeYFinalMax = 0;
GLfloat mDegreeYDefault = 0;
GLfloat mDegreeY = 0;

GLfloat mDegreeZMin = 0;
GLfloat mDegreeZMax = 0;
GLfloat mDegreeZFinalMin = 0;
GLfloat mDegreeZFinalMax = 0;
GLfloat mDegreeZDefault = 0;
GLfloat mDegreeZ = 0;

GLfloat mFovMin = 0;
GLfloat mFovMax = 0;
GLfloat mFovFinalMin = 0;
GLfloat mFovFinalMax = 0;
GLfloat mFovDefault = 0;
GLfloat mFov = 60;

GLfloat mRightDegreeXMin = 0;
GLfloat mRightDegreeXMax = 0;
GLfloat mRightDegreeXFinalMin = 0;
GLfloat mRightDegreeXFinalMax = 0;
GLfloat mRightDegreeXDefault = 0;
GLfloat mRightDegreeX = 0;

GLfloat mRightDegreeYMin = 0;
GLfloat mRightDegreeYMax = 0;
GLfloat mRightDegreeYFinalMin = 0;
GLfloat mRightDegreeYFinalMax = 0;
GLfloat mRightDegreeYDefault = 0;
GLfloat mRightDegreeY = 0;

GLfloat mRightDegreeZMin = 0;
GLfloat mRightDegreeZMax = 0;
GLfloat mRightDegreeZFinalMin = 0;
GLfloat mRightDegreeZFinalMax = 0;
GLfloat mRightDegreeZDefault = 0;
GLfloat mRightDegreeZ = 0;

GLfloat mRightFovMin = 0;
GLfloat mRightFovMax = 0;
GLfloat mRightFovFinalMin = 0;
GLfloat mRightFovFinalMax = 0;
GLfloat mRightFovDefault = 0;
GLfloat mRightFov = 60;

GLfloat getDegreeX(GLboolean isLeft){
    if(isLeft) return mDegreeX;
    else return mRightDegreeX;
}
GLfloat getDegreeY(GLboolean isLeft){
    if(isLeft) return mDegreeY;
    else return mRightDegreeY;
}
GLfloat getDegreeZ(GLboolean isLeft){
    if(isLeft) return mDegreeZ;
    else return mRightDegreeZ;
}
GLfloat getFov(GLboolean isLeft){
    if(isLeft) return mFov;
    else return mRightFov;
}

void setDegreeX(GLfloat x, GLboolean isLeft){
    if(isLeft){
        if(mDegreeXMax > mDegreeXMin){
            if(x > mDegreeXMax) {
                x = mDegreeXMax;
            } else if(x < mDegreeXMin){
                x = mDegreeXMin;
            }
        }
        mDegreeX = x;
    } else {
        if(mRightDegreeXMax > mRightDegreeXMin){
            if(x > mRightDegreeXMax) {
                x = mRightDegreeXMax;
            } else if(x < mRightDegreeXMin){
                x = mRightDegreeXMin;
            }
        }
        mRightDegreeX = x;
    }
}
void setDegreeY(GLfloat y, GLboolean isLeft){
    if(isLeft){
        if(mDegreeYMax > mDegreeYMin){
            if(y > mDegreeYMax) {
                y = mDegreeYMax;
            } else if(y < mDegreeYMin){
                y = mDegreeYMin;
            }
        }
        mDegreeY = y;
    } else {
        if(mRightDegreeYMax > mRightDegreeYMin){
            if(y > mRightDegreeYMax) {
                y = mRightDegreeYMax;
            } else if(y < mRightDegreeYMin){
                y = mRightDegreeYMin;
            }
        }
        mRightDegreeY = y;
    }
}
void setDegreeZ(GLfloat z, GLboolean isLeft){
    if(isLeft){
        if(mDegreeZMax > mDegreeZMin){
            if(z > mDegreeZMax) {
                z = mDegreeZMax;
            } else if(z < mDegreeZMin){
                z = mDegreeZMin;
            }
        }
        mDegreeZ = z;
    } else {
        if(mRightDegreeZMax > mRightDegreeZMin){
            if(z > mRightDegreeZMax) {
                z = mRightDegreeZMax;
            } else if(z < mRightDegreeZMin){
                z = mRightDegreeZMin;
            }
        }
        mRightDegreeZ = z;
    }
}
void setFov(GLfloat fov, GLfloat ratio, GLboolean isLeft){
    if(isLeft){
        if(mFovMax > mFovMin){
            if(fov > mFovMax) {
                fov = mFovMax;
            } else if(fov < mFovMin){
                fov = mFovMin;
            }
        }
        mFov = fov;
        if(mDegreeYMax > mDegreeYMin){
            mDegreeYMax = mDegreeYFinalMax - mFov/2;
            mDegreeYMin = mDegreeYFinalMin + mFov/2;
            if(mDegreeYMax <= mDegreeYDefault){
                mDegreeYMax = mDegreeYDefault + 1;
            }
            if(mDegreeYMin >= mDegreeYDefault){
                mDegreeYMin = mDegreeYDefault - 1;
            }
        }
        if(mDegreeXMax > mDegreeXMin){
            mDegreeXMax = mDegreeXFinalMax - mFov*ratio/2;
            mDegreeXMin = mDegreeXFinalMin + mFov*ratio/2;
            if(mDegreeXMax <= mDegreeXDefault){
                mDegreeXMax = mDegreeXDefault + 1;
            }
            if(mDegreeXMin >= mDegreeXDefault){
                mDegreeXMin = mDegreeXDefault - 1;
            }
        }
    } else {
        if(mRightFovMax > mRightFovMin){
            if(fov > mRightFovMax) {
                fov = mRightFovMax;
            } else if(fov < mRightFovMin){
                fov = mRightFovMin;
            }
        }
        mRightFov = fov;
        if(mRightDegreeYMax > mRightDegreeYMin){
            mRightDegreeYMax = mRightDegreeYFinalMax - mRightFov/2;
            mRightDegreeYMin = mRightDegreeYFinalMin + mRightFov/2;
            if(mRightDegreeYMax <= mRightDegreeYDefault){
                mRightDegreeYMax = mRightDegreeYDefault + 1;
            }
            if(mRightDegreeYMin >= mRightDegreeYDefault){
                mRightDegreeYMin = mRightDegreeYDefault - 1;
            }
        }
        if(mRightDegreeXMax > mRightDegreeXMin){
            mRightDegreeXMax = mRightDegreeXFinalMax - mRightFov*ratio/2;
            mRightDegreeXMin = mRightDegreeXFinalMin + mRightFov*ratio/2;
            if(mRightDegreeXMax <= mRightDegreeXDefault){
                mRightDegreeXMax = mRightDegreeXDefault + 1;
            }
            if(mRightDegreeXMin >= mRightDegreeXDefault){
                mRightDegreeXMin = mRightDegreeXDefault - 1;
            }
        }
    }
}

void setFovRegion(GLfloat min, GLfloat max, GLfloat fov, GLboolean isLeft){
    if(isLeft){
        mFovMax = max;
        mFovMin = min;
        mFovFinalMax = max;
        mFovFinalMin = min;
        mFovDefault = fov;
    } else {
        mRightFovMax = max;
        mRightFovMin = min;
        mRightFovFinalMax = max;
        mRightFovFinalMin = min;
        mRightFovDefault = fov;
    }
    setFov(fov, 1.777778, isLeft);
    LOGD("fov=%f, min=%f, max=%f, isLeft=%d", fov, min, max, isLeft);
}
void setDegreeXRegion(GLfloat min, GLfloat max, GLfloat x, GLboolean isLeft){
    if(max > min){
        max = max - 10;
        min = min + 10;
    }
    if(isLeft){
        mDegreeXMax = max;
        mDegreeXMin = min;
        mDegreeXFinalMax = max;
        mDegreeXFinalMin = min;
        mDegreeXDefault = x;
        mDegreeX = x;
    } else {
        mRightDegreeXMax = max;
        mRightDegreeXMin = min;
        mRightDegreeXFinalMax = max;
        mRightDegreeXFinalMin = min;
        mRightDegreeXDefault = x;
        mRightDegreeX = x;
    }
    LOGD("degreeX=%f, min=%f, max=%f, isLeft=%d", x, min, max, isLeft);
}
void setDegreeYRegion(GLfloat min, GLfloat max, GLfloat y, GLboolean isLeft){
    if(max > min){
        max = max - 10;
        min = min + 10;
    }
    if(isLeft){
        mDegreeYMax = max;
        mDegreeYMin = min;
        mDegreeYFinalMax = max;
        mDegreeYFinalMin = min;
        mDegreeYDefault = y;
        mDegreeY = y;
    } else {
        mRightDegreeYMax = max;
        mRightDegreeYMin = min;
        mRightDegreeYFinalMax = max;
        mRightDegreeYFinalMin = min;
        mRightDegreeYDefault = y;
        mRightDegreeY = y;
    }
    LOGD("degreeY=%f, min=%f, max=%f, isLeft=%d", y, min, max, isLeft);
}
void setDegreeZRegion(GLfloat min, GLfloat max, GLfloat z, GLboolean isLeft){
    if(isLeft){
    mDegreeZMax = max;
        mDegreeZMin = min;
        mDegreeZFinalMax = max;
        mDegreeZFinalMin = min;
        mDegreeZDefault = z;
        mDegreeZ = z;
    } else {
        mRightDegreeZMax = max;
        mRightDegreeZMin = min;
        mRightDegreeZFinalMax = max;
        mRightDegreeZFinalMin = min;
        mRightDegreeZDefault = z;
        mRightDegreeZ = z;
    }
}

void resetDegree(){
    mDegreeX = mDegreeXDefault;
    mDegreeY = mDegreeYDefault;
    mDegreeZ = mDegreeZDefault;
    mFov = mFovDefault;

    mRightDegreeX = mRightDegreeXDefault;
    mRightDegreeY = mRightDegreeYDefault;
    mRightDegreeZ = mRightDegreeZDefault;
    mRightFov = mRightFovDefault;
}

void setDegree(GLboolean isLeft){
    switch(mMode){
    case SHOW_MODE_ORIGINAL:
        setDegreeXRegion(0, 0, 180, isLeft);
        setDegreeYRegion(0, 0, 0, isLeft);
        break;
    case SHOW_MODE_SPHERE_FRONT:
        setDegreeXRegion(0, 180, 90, isLeft);
        setDegreeYRegion(-90, 90, 0, isLeft);
        break;
    case SHOW_MODE_SPHERE_FRONT_BACK:
        if(isLeft){
            setDegreeXRegion(0, 0, 270, GL_TRUE);
            setDegreeYRegion(-135, 0, -45, GL_TRUE);
        } else{
            setDegreeXRegion(0, 0, 90, GL_FALSE);
            setDegreeYRegion(0, 135, 45, GL_FALSE);
        }
        break;
    case SHOW_MODE_SPHERE_UP:
        setDegreeXRegion(0, 0, 270, isLeft);
        setDegreeYRegion(0, 180, 90, isLeft);
        break;
    case SHOW_MODE_SPHERE_DOWN:
        setDegreeXRegion(0, 0, 270, isLeft);
        setDegreeYRegion(-180, 0, -90, isLeft);
        break;
    case SHOW_MODE_SPHERE_VR:
        setDegreeXRegion(0, 180, 90, isLeft);
        setDegreeYRegion(-90, 90, 0, isLeft);
        break;
    case SHOW_MODE_CYLINDER_UP_DOWN:
        if(isLeft){
            setDegreeXRegion(180, 360, 270, GL_TRUE);
            setDegreeYRegion(-180, 0, -90, GL_TRUE);
        } else{
            setDegreeXRegion(180, 360, 270, GL_FALSE);
            setDegreeYRegion(0, 180, 90, GL_FALSE);
        }
        break;
    case SHOW_MODE_PLANE_UP_DOWN:
        setDegreeXRegion(90, 270, 180, isLeft);
        setDegreeYRegion(-180, 180, 0, isLeft);
        break;
    default:
        setDegreeXRegion(0, 180, 90, isLeft);
        setDegreeYRegion(-90, 90, 0, isLeft);
        break;
    }
    setFovRegion(30, 90, 90, isLeft);
    setDegreeZRegion(0, 0, 0, isLeft);
}

GLboolean setShowMode(GLint mode, GLint resolution, GLboolean isLeft){
    mMode = mode;
    mResolution = resolution;
    LOGD("setShowMode mMode=%d, mResolution=%d, isLeft=%d", mMode, mResolution, isLeft);
    setDegree(isLeft);
    return GL_TRUE;
}

GLfloat * getVertex(){
    GLfloat * result;
    switch(mResolution){
    case RESOLUTION_4K:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = k4k_plane_vertex;
            break;
        case SHOW_MODE_SPHERE_FRONT:
        case SHOW_MODE_SPHERE_FRONT_BACK:
        case SHOW_MODE_SPHERE_UP:
        case SHOW_MODE_SPHERE_DOWN:
        case SHOW_MODE_SPHERE_VR:
            result = k4k_spherical_vertex;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = k4k_cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = k4k_plane_vertex;
            break;
        default:
            result = k4k_plane_vertex;
            break;
        }
        break;
    case RESOLUTION_1080P:
    case RESOLUTION_720P:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = plane_vertex;
            break;
        case SHOW_MODE_SPHERE_FRONT:
        case SHOW_MODE_SPHERE_FRONT_BACK:
        case SHOW_MODE_SPHERE_UP:
        case SHOW_MODE_SPHERE_DOWN:
        case SHOW_MODE_SPHERE_VR:
            result = spherical_vertex;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = plane_vertex;
            break;
        default:
            result = k4k_plane_vertex;
            break;
        }
        break;
    default:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = k4k_plane_vertex;
            break;
        case SHOW_MODE_SPHERE_FRONT:
        case SHOW_MODE_SPHERE_FRONT_BACK:
        case SHOW_MODE_SPHERE_UP:
        case SHOW_MODE_SPHERE_DOWN:
        case SHOW_MODE_SPHERE_VR:
            result = k4k_spherical_vertex;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = k4k_cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = k4k_plane_vertex;
            break;
        default:
            result = k4k_plane_vertex;
            break;
        }
        break;
    }
    return result;
}


GLfloat * getTexture(){
    GLfloat * result;
    switch(mResolution){
    case RESOLUTION_4K:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = k4k_plane_original_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT:
            result = k4k_spherical_front_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT_BACK:
            result = k4k_spherical_up_down_Texture;
            break;
        case SHOW_MODE_SPHERE_UP:
            result = k4k_spherical_up_Texture;
            break;
        case SHOW_MODE_SPHERE_DOWN:
            result = k4k_spherical_down_Texture;
            break;
        case SHOW_MODE_SPHERE_VR:
            result = k4k_spherical_front_Texture;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = k4k_cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = k4k_plane_up_down_Texture;
            break;
        default:
            result = k4k_plane_original_Texture;
            break;
        }
        break;
    case RESOLUTION_1080P:
    case RESOLUTION_720P:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = plane_original_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT:
            result = spherical_front_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT_BACK:
            result = spherical_up_down_Texture;
            break;
        case SHOW_MODE_SPHERE_UP:
            result = spherical_up_Texture;
            break;
        case SHOW_MODE_SPHERE_DOWN:
            result = spherical_down_Texture;
            break;
        case SHOW_MODE_SPHERE_VR:
            result = spherical_front_Texture;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = plane_up_down_Texture;
            break;
        default:
            result = plane_original_Texture;
            break;
        }
        break;
    default:
        switch(mMode){
        case SHOW_MODE_ORIGINAL:
            result = k4k_plane_original_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT:
            result = k4k_spherical_front_Texture;
            break;
        case SHOW_MODE_SPHERE_FRONT_BACK:
            result = k4k_spherical_up_down_Texture;
            break;
        case SHOW_MODE_SPHERE_UP:
            result = k4k_spherical_up_Texture;
            break;
        case SHOW_MODE_SPHERE_DOWN:
            result = k4k_spherical_down_Texture;
            break;
        case SHOW_MODE_SPHERE_VR:
            result = k4k_spherical_front_Texture;
            break;
        case SHOW_MODE_CYLINDER_UP_DOWN:
            result = k4k_cylindrical_vertex;
            break;
        case SHOW_MODE_PLANE_UP_DOWN:
            result = k4k_plane_up_down_Texture;
            break;
        default:
            result = k4k_plane_original_Texture;
            break;
        }
        break;
    }
    return result;
}

void drawForOES(GLint textureId){
    if(!isHaveLicence) return;
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);

    glVertexPointer(3, GL_FLOAT, 0, (GLvoid *)getVertex());
    glTexCoordPointer(2, GL_FLOAT, 0, (GLvoid *)getTexture());

    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_EXTERNAL_OES, textureId);
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 6480);

    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
}

void draw(GLint textureId){
    if(!isHaveLicence) return;
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_TEXTURE_COORD_ARRAY);

    glVertexPointer(3, GL_FLOAT, 0, (GLvoid *)getVertex());
    glTexCoordPointer(2, GL_FLOAT, 0, (GLvoid *)getTexture());

    glActiveTexture(GL_TEXTURE0);
    glBindTexture(GL_TEXTURE_2D, textureId);
    glDrawArrays(GL_TRIANGLE_STRIP, 0, 6480);

    glDisableClientState(GL_VERTEX_ARRAY);
    glDisableClientState(GL_TEXTURE_COORD_ARRAY);
}

GLint createTextureIDForOES() {
    glEnable(GL_TEXTURE_EXTERNAL_OES);
    GLuint texture = -1;
    glGenTextures(1, &texture);
    glBindTexture(GL_TEXTURE_EXTERNAL_OES, texture);

    glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameterf(GL_TEXTURE_EXTERNAL_OES, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    return texture;
}

GLint createTextureID() {
    glEnable(GL_TEXTURE_2D);
    GLuint texture = -1;
    glGenTextures(1, &texture);
    glBindTexture(GL_TEXTURE_2D, texture);

    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    return texture;
}

void gluPerspective(GLfloat fovy, GLfloat aspect, GLfloat zNear, GLfloat zFar)
{
    GLfloat top = zNear * (GLfloat) tan(fovy * (PI / 360.0));
    GLfloat bottom = -top;
    GLfloat left = bottom * aspect;
    GLfloat right = top * aspect;
    glFrustumf(left, right, bottom, top, zNear, zFar);
}

void gluLookAt(float eyeX, float eyeY, float eyeZ,
        float centerX, float centerY, float centerZ, float upX, float upY,
        float upZ)
{
    // See the OpenGL GLUT documentation for gluLookAt for a description
    // of the algorithm. We implement it in a straightforward way:

    float fx = centerX - eyeX;
    float fy = centerY - eyeY;
    float fz = centerZ - eyeZ;

    // Normalize f
    float rlf = 1.0f / sqrtf(fx*fx + fy*fy + fz*fz);
    fx *= rlf;
    fy *= rlf;
    fz *= rlf;

    // Normalize up
    float rlup = 1.0f / sqrtf(upX*upX + upY*upY + upZ*upZ);
    upX *= rlup;
    upY *= rlup;
    upZ *= rlup;

    // compute s = f x up (x means "cross product")

    float sx = fy * upZ - fz * upY;
    float sy = fz * upX - fx * upZ;
    float sz = fx * upY - fy * upX;

    // compute u = s x f
    float ux = sy * fz - sz * fy;
    float uy = sz * fx - sx * fz;
    float uz = sx * fy - sy * fx;

    float m[16] ;
    m[0] = sx;
    m[1] = ux;
    m[2] = -fx;
    m[3] = 0.0f;

    m[4] = sy;
    m[5] = uy;
    m[6] = -fy;
    m[7] = 0.0f;

    m[8] = sz;
    m[9] = uz;
    m[10] = -fz;
    m[11] = 0.0f;

    m[12] = 0.0f;
    m[13] = 0.0f;
    m[14] = 0.0f;
    m[15] = 1.0f;

    glMultMatrixf(m);
    glTranslatef(-eyeX, -eyeY, -eyeZ);
}
