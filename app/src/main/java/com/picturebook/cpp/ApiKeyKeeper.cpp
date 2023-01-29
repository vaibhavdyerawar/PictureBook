#include<jni.h>
#include<string>
#include<iostream>

extern "C" JNIEXPORT jstring JNICALL
Java_com_pixaland_app_service_ApiKeyProvider_getAPIKey(JNIEnv *env, jobject this){
        return env->NewStringUTF("33000158-a26e47bb8e28fff274d0e1ab3");
}
