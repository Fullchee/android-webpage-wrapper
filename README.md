# Web Wrapper

Builds minimal Android apps that each open one website full screen. Every other link is blocked. Use them to reach chosen sites while the phone's browser is blocked.

Sites today: **Journal Site** (`https://journal.fullchee.workers.dev/`).

## Add a site

1. In `app/build.gradle.kts`, copy the `create("journal")` block under `productFlavors`. Give it a new name, `applicationId`, `app_name` and `HOME_URL`.
2. Optional: put an adaptive icon in `app/src/<site>/res/` (see `app/src/journal/res/`). Without one, the app gets a globe icon.
3. Commit, then run `mise run build`.

Never change an existing site's `applicationId`: the phone treats it as a new app and you sign in again.

## Build and install

1. Commit your changes, then run `mise run build`. It builds one APK per site and publishes them as a GitHub release.
2. On the phone, download a site's APK, e.g. [journal.apk](https://github.com/Fullchee/android-webpage-wrapper/releases/latest/download/journal.apk).
3. Open it and allow installs from that source.

## Test

`./gradlew testDebugUnitTest` runs the tests once per site.

## Commands

Needs [mise](https://mise.jdx.dev). Run `mise install` to get JDK 25 (JDK 21 or newer works).

| Command | Does |
| --- | --- |
| `mise run build` | Build every site and publish the APKs as a GitHub release |
| `./gradlew testDebugUnitTest` | Run unit tests for every site |
| `./gradlew assembleDebug` | Build every site's debug APK |
| `./gradlew installJournalDebug` | Build and install one site on a connected device |
| `./gradlew tasks` | List all tasks |
