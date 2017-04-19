/*
 * aes.h
 *
 *  Created on: 2017-1-18
 *      Author: lyz1900
 */

#ifndef AES_H_
#define AES_H_

//extern unsigned char sBox[256] ;
//extern  unsigned char invsBox[256] ;

//public:
int encode(const char* in, char* out);
int encode_key(const char* in, unsigned char* key, char* out);
int InvCipher_server( const char* lisense,const char* android_ID,const char* ciphertext_0,const char* ciphertext_1);

#endif /* AES_H_ */
