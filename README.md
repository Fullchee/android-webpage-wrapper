# Web Wrapper

A minimal Android app that opens `https://journal.fullchee.workers.dev/` full screen. Every other link is blocked.

## Change the site

Edit `HOME_URL` in `app/src/main/kotlin/com/fullchee/webwrapper/Allowlist.kt`, then rebuild.

## Build and install

1. Commit your changes, then run `mise run build`. It builds the APK and publishes it as a GitHub release.
2. On the phone, download [the latest APK](https://github.com/Fullchee/android-webpage-wrapper/releases/latest/download/webpage-wrapper.apk).
3. Open it and allow installs from that source.

## Test

`./gradlew testDebugUnitTest`

## Commands

Needs [mise](https://mise.jdx.dev). Run `mise install` to get JDK 25 (JDK 21 or newer works).

| Command | Does |
| --- | --- |
| `mise run build` | Build and publish the APK as a GitHub release |
| `./gradlew testDebugUnitTest` | Run unit tests |
| `./gradlew assembleDebug` | Build the debug APK |
| `./gradlew installDebug` | Build and install on a connected device |
| `./gradlew tasks` | List all tasks |
