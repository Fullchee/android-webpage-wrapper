# Intent: webpage wrapper

Confirmed 2026-11-04. Updated 2026-11-22: one app per site.

- **Outcome:** A factory of minimal Android apps, one per hardcoded site. Together they replace a blocked browser.
- **User:** Me, on my own phone.
- **Why now:** Browsers are blocked on my phone, but I still need a few specific sites.
- **Sites:** Each site is a Gradle product flavor with its own name, icon, home URL and app ID. Today: Journal Site (`https://journal.fullchee.workers.dev/`).
- **Screen:** Each app opens straight to its site, full screen. No home screen, toolbar or URL bar. The system back gesture goes back in page history, then exits.
- **Navigation:** Links outside the allowlist are blocked, with no "open elsewhere" escape hatch. Google and Cloudflare sign-in domains are always allowed.
- **Logins:** Cookies persist, so I stay signed in. Each app keeps its own cookies, so a site's app ID never changes.
- **Changing sites:** Add or edit a flavor in `app/build.gradle.kts`, then rebuild. The friction is intentional.
- **Build/install:** Kotlin + WebView. `mise run build` publishes one APK per site as a GitHub release, sideloaded onto the phone.
- **Out of scope:** URL bar, editing sites in the app, Play Store, downloads and file uploads, multiple tabs, more than one site per app.
