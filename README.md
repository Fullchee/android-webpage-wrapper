# Web Wrapper

A minimal Android app that opens only hardcoded sites. Every other link is blocked.

## Change the sites

Edit `SITES` in `app/src/main/kotlin/com/fullchee/webwrapper/Allowlist.kt`, then rebuild.

## Build and install
1. `./build.sh` builds, reveals the APK in Finder and opens the Drive folder (or `./gradlew assembleDebug`).
1. `./gradlew assembleDebug` (or Build → Build APK in Android Studio).
2. Copy `app/build/outputs/apk/debug/webpage-wrapper.apk` to the phone.
3. Open it on the phone and allow installs from that source.

## Test

`./gradlew testDebugUnitTest`

## Commands

Needs JDK 21 or newer. Install with `brew install --cask temurin@25`.

| Command | Does |
| --- | --- |
| `./gradlew testDebugUnitTest` | Run unit tests |
| `./gradlew assembleDebug` | Build the debug APK |
| `./gradlew installDebug` | Build and install on a connected device |
| `./gradlew tasks` | List all tasks |
