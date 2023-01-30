#include<jni.h>
#include<string>
#include<iostream>

extern "C" JNIEXPORT JNICALL jstring
Java_com_picturebook_service_ApiKeyProvider_getAPIKey(JNIEnv *env, jobject jObj) {
    return env->NewStringUTF("33181627-dfe938147d37944eaf644206e");
}
