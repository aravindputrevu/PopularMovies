---
name: testing-android-app
description: Test the PopularMovies Android app end-to-end on an emulator. Use when verifying UI, API integration, or navigation changes.
---

# Testing PopularMovies Android App

## Prerequisites

- Android SDK installed at `$ANDROID_HOME` (typically `/home/ubuntu/android-sdk`)
- Platform tools, build-tools, and emulator installed
- AVD named `test_avd` created with `system-images;android-35;google_apis;x86_64`
- KVM permissions: ensure `/dev/kvm` is accessible (`sudo chmod 666 /dev/kvm`)

## Devin Secrets Needed

- `TMDB_API_KEY` — set in `local.properties` as `TMDB_API_KEY=<key>` for the TMDB API

## Build

```bash
export ANDROID_HOME=/home/ubuntu/android-sdk
export PATH=$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH

# Ensure local.properties has SDK path and API key
echo "sdk.dir=$ANDROID_HOME" > local.properties
echo "TMDB_API_KEY=<your_key>" >> local.properties

./gradlew assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`

## Launch Emulator

```bash
# Fix KVM permissions if needed
sudo chmod 666 /dev/kvm 2>/dev/null || true

# Launch headless emulator in background
nohup emulator -avd test_avd -no-audio -gpu swiftshader_indirect -no-boot-anim -no-snapshot -wipe-data -no-window > /tmp/emulator.log 2>&1 &

# Wait for boot (poll until sys.boot_completed=1)
adb wait-for-device
while [ "$(adb shell getprop sys.boot_completed 2>/dev/null)" != "1" ]; do sleep 2; done
```

Boot typically takes 30-60 seconds with KVM enabled.

## Install & Launch App

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.aravind.popularmovies/.MainActivity
```

## Testing via adb

Since the emulator runs headless (`-no-window`), use `adb` for all interactions:

```bash
# Take screenshot
adb exec-out screencap -p > screenshot.png

# Tap at coordinates (x, y)
adb shell input tap 160 400

# Swipe/scroll (startX startY endX endY duration_ms)
adb shell input swipe 160 500 160 100 500

# Force stop app
adb shell am force-stop com.aravind.popularmovies
```

## Key Test Flows

1. **Home screen load** — cold launch, wait 5-8s for API data, screenshot. Verify movie posters render (not placeholders).
2. **Tab switching** — tap "Top Rated" tab (~x=240, y=111). Verify different movies from "Most Popular".
3. **Movie details** — tap a movie card, verify backdrop image, title, release date (YYYY-MM-DD), rating (X/10), votes, overview text.
4. **Back navigation** — tap back arrow (~x=35, y=55), verify return to home with tab state preserved.
5. **Scroll** — swipe up multiple times, verify new movie cards load with images.

## Gotchas

- The emulator may fail to start without KVM permissions. Always run `sudo chmod 666 /dev/kvm` first.
- First API call after cold launch can take 5-10s on the emulator. Wait before screenshotting.
- Tap coordinates depend on screen density. The default AVD uses 1080x1920 at 420dpi. Adjust coordinates if the AVD config changes.
- If the TMDB API key is missing or expired, the app will show an error state with a "Retry" button instead of movie data.
