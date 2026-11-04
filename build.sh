#!/bin/zsh
set -euo pipefail
cd "${0:A:h}"

./gradlew assembleDebug

open -R "$PWD/app/build/outputs/apk/debug/webpage-wrapper.apk"
open "https://drive.google.com/drive/u/0/folders/1uIDzHTp9rQKAxHKFArRUaK1xQXI7RNLO"
