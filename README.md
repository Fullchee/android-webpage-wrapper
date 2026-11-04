# Web Wrapper

A minimal Android app that opens only hardcoded sites. Every other link is blocked.

## Change the sites

Edit `SITES` in `app/src/main/kotlin/com/fullchee/webwrapper/Allowlist.kt`, then rebuild.

## Build and install

1. `mise run build` builds, reveals the APK in Finder and opens the Drive folder (or `./gradlew assembleDebug`).
2. Copy `app/build/outputs/apk/debug/webpage-wrapper.apk` to the phone.
3. Open it on the phone and allow installs from that source.

## Test

`./gradlew testDebugUnitTest`

## Commands

Needs [mise](https://mise.jdx.dev). Run `mise install` to get JDK 25 (JDK 21 or newer works).

| Command | Does |
| --- | --- |
| `mise run build` | Build, reveal APK in Finder, open Drive folder |
| `./gradlew testDebugUnitTest` | Run unit tests |
| `./gradlew assembleDebug` | Build the debug APK |
| `./gradlew installDebug` | Build and install on a connected device |
| `./gradlew tasks` | List all tasks |
