#ifndef LICENCE_H
#define LICENCE_H

#include <jni.h>
#include <GLES/gl.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/system_properties.h>

#include "aes/aes.h"
#include "native_log.h"

void getEncodeA(char *out);

void encode(const char *in, char *out);

void encode(const unsigned char *key, const char *in, char *out);

GLboolean isAllow(const char *licence, const char *androidid, const char *r1, const char *r2);

char *getPhoneIMEI();

int getKey(unsigned char *key);

#endif //LICENCE_H