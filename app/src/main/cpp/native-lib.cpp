#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_sven_ou_module_launch_activities_AppStartActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "load from C++";
    return env->NewStringUTF(hello.c_str());
}
