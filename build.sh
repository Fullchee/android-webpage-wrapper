#!/bin/zsh
set -euo pipefail
cd "${0:A:h}"

./gradlew assembleDebug

apk="$PWD/app/build/outputs/apk/debug/webpage-wrapper.apk"
# Copies the file itself (paste into Finder, Slack, etc.), not its path.
osascript -e "set the clipboard to (POSIX file \"$apk\")"
open -R "$apk"
