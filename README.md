<h1 align="center">DragonDex</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
  🔥 DragonDex is a modern Android development project developed with Hilt, Coroutines, Flow, Jetpack (Room, ViewModel) based on MVVM architecture.
</p>

<p align="center">
  <img src="assets/main_screenshot.png" alt="Image of app"/>
</p>

## Download
Go to the [Releases](https://github.com/skydoves/pokedex-compose/releases) to download the latest APK.

## Tech stack & Open-source libraries
- Minimum SDK level 21.
- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
    - Jetpack Compose: Android’s modern toolkit for declarative UI development.
    - Lifecycle: Observes Android lifecycles and manages UI states upon lifecycle changes.
    - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
    - Navigation: Facilitates screen navigation, complemented by [Hilt Navigation Compose](https://developer.android.com/jetpack/compose/libraries#hilt) for dependency injection.
    - Room: Constructs a database with an SQLite abstraction layer for seamless database access.
    - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
    - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
    - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Constructs REST APIs and facilitates paging network data retrieval.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API for code generation and analysis.
- [Lottie](https://github.com/airbnb/lottie-android): Render After Effects animations natively on Android and iOS.

<p align="center">
  <img src="assets/description_gif.gif" alt="Descriptive gif of the app"/>
</p>

## Architecture
DragonDex is based on MVVM architecture:

<p align="center">
  <img src="assets/dragon_dex_architecture.png" alt="Architecture"/>
</p>

The architecture of this repository is structured in 4 main layers:
- UI: Contains UI-related classes such as Activities.
- Domain: Contains UseCases, Repositories, and Models -> Business logic.
- Data: Contains Repository implementations and data sources. -> Provides the data.
- DI: Contains dependency injection modules.

## Open API
<img src="https://web.dragonball-api.com/images-compress/logo_dragonballapi.webp" align="right" width="21%"/>

DragonDex using the [dragonball-api](https://web.dragonball-api.com/) for constructing RESTful API.<br>

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/sgaleraalq/DragonDex/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/sgaleraalq)__ on GitHub for my next creations! 🤩
