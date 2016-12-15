# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in H:\Android\program\AndroidStudioSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 保留序列化
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable { *; }

# 保留model，一般model最好避免混淆（model无关紧要，不混淆也没多大关系）
-keep * extends com.activeandroid.Model {*;}

# 保留适配器
-keep public class * extends android.widget.BaseAdapter {*;}
# 如果你使用了CusorAdapter,添加下面这行
-keep public class * extends android.widget.CusorAdapter{*;}
# 同样如果你觉得麻烦，就直接将BaseAdpater换成Adapter

# 保留资源类变量不被混淆，否则，你的反射是获取不到资源id的
# 这里的com.oschina.test是自己项目的包名，不要照搬。
-keep public class com.oschina.test.R$*{
    public static final int *;
}

# 保留四大组件和自定义控件及组件。
# 如果我们自定了ListView,ScrollView,Gallery这些组件的时候，
# 我们就不能混淆这些自定义的类了，因为在layout里面我们已经引用这个了类，而且已经写死了。
# 第二句保留在activity中的方法参数是view的方法，这样的话，我们在xml里面编写onClick就不会被影响了
-keep public class * extends android.app.Activity
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View {*;}
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class com.android.vending.licensing.ILicensingService
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留点击和回调函数
-keepclasseswithmembers class * {
    void onClick*(...);
}
-keepclasseswithmembers class * {
    *** *Callback(...);
}

##-----------------第三方jar包配置（选择性添加）-------------------------

-dontwarn dagger.**
-keep class dagger.** { *;}

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *;}

-dontwarn okio.**
-keep class okio.** { *;}

-dontwarn retrofit2.**
-keep class retrofit2.** { *;}

-dontwarn rx.**
-keep class rx.** { *;}

