set shell := ["zsh", "-euo", "pipefail", "-c"]

# Build the APK, reveal it in Finder, open the Drive folder
build:
    ./gradlew assembleDebug
    open -R app/build/outputs/apk/debug/webpage-wrapper.apk
    open "https://drive.google.com/drive/u/0/folders/1uIDzHTp9rQKAxHKFArRUaK1xQXI7RNLO"
