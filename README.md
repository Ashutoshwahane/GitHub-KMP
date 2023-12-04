# GitHub KMP
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-blue)](https://github.com/JetBrains/compose-multiplatform)


https://github.com/Ashutoshwahane/GitHub-KMP/assets/50887729/5cc60f38-6bd7-4945-a8d4-b536eb5a733a


## Built with 

- [Kotlin](kotlinlang.org): Programming language
- [Kotlin Multiplatform](https://kotlinlang.org/docs/multiplatform.html): For building multi-platform applications in the single codebase.
- [Compose Multiplatform]([https://kmp.jetbrains.com](https://www.jetbrains.com/lp/compose-multiplatform/)) For building UI using Jetpack compose.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines): For multithreading
- [Serialization](https://github.com/Kotlin/kotlinx.serialization): For JSON serialization/deserailization
- [Ktor Client](https://github.com/ktorio/ktor): Performing HTTP requests, Creating image loading utility for iOS module.
- [Kamel](https://github.com/Kamel-Media/Kamel): Image loading for Android

This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


