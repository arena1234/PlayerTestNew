#ifndef _H_NATIVE_LOG_H_
#define _H_NATIVE_LOG_H_

#include <stdarg.h>
#include <android/log.h>
#include <jni.h>

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, "VR_JNI", __VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, "VR_JNI", __VA_ARGS__);
extern  JavaVM* vm_global;
extern  jobject obj_global;

#endif
