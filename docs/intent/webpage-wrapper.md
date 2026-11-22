# Intent: webpage wrapper

Confirmed 2026-11-04.

- **Outcome:** A minimal Android app that opens only hardcoded sites. It replaces a blocked browser.
- **User:** Me, on my own phone.
- **Why now:** Browsers are blocked on my phone, but I still need a few specific sites.
- **Screen:** The app opens straight to `https://journal.fullchee.workers.dev/`, full screen. No home screen, toolbar or URL bar. The system back gesture goes back in page history, then exits.
- **Navigation:** Links outside the allowlist are blocked, with no "open elsewhere" escape hatch. Google and Cloudflare sign-in domains are always allowed.
- **Logins:** Cookies persist, so I stay signed in.
- **Changing sites:** Edit the code and rebuild. The friction is intentional.
- **Build/install:** Kotlin + WebView, built in Android Studio, APK sideloaded onto the phone.
- **Out of scope:** URL bar, editing sites in the app, Play Store, downloads and file uploads, multiple tabs.
