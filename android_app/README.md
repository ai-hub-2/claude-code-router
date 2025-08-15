# My Android App

تطبيق Android بسيط مبني باستخدام Kotlin و Android SDK.

## المميزات

- تطبيق Android حقيقي وقابل للتثبيت
- واجهة مستخدم بسيطة وجميلة
- رسالة ترحيب باللغة العربية
- يدعم Android API 24+ (Android 7.0+)

## البناء

```bash
# بناء APK للتطوير
./gradlew assembleDebug

# بناء APK للإنتاج
./gradlew assembleRelease
```

## التثبيت

1. قم بتحميل ملف APK من GitHub Actions
2. فعّل "تثبيت من مصادر غير معروفة" في إعدادات Android
3. قم بتثبيت التطبيق

## التقنيات المستخدمة

- Kotlin
- Android SDK
- Gradle
- Material Design Components