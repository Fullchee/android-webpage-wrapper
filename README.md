# Web Wrapper

A minimal Android app that opens only hardcoded sites. Every other link is blocked.

## Change the sites

Edit `SITES` in `app/src/main/kotlin/com/fullchee/webwrapper/Allowlist.kt`, then rebuild.

## Build and install

1. `./gradlew assembleDebug` (or Build → Build APK in Android Studio).
2. Copy `app/build/outputs/apk/debug/app-debug.apk` to the phone.
3. Open it on the phone and allow installs from that source.

## Test

`./gradlew testDebugUnitTest`
