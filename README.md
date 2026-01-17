🗞️ News App (Android)

A simple, modern Android News App built with a strong focus on clean code, scalability, and real-world best practices. This project reflects how a production-ready Android app is structured, tested, and automated using today’s recommended tools.

📱 App Overview

This app allows users to browse the latest news from a remote API and bookmark articles they want to read later. It is designed to be easy to maintain and extend, making it a good example of how Clean Architecture works in a real Android project.

The main goals of this project are:

Clear separation of responsibilities

High test coverage

Secure handling of API keys

Reliable CI automation

🏗️ Architecture

The app follows Clean Architecture, which helps keep the codebase easy to understand, test, and scale over time. Each layer has a clear responsibility and does not depend directly on framework details.

Hilt is used for dependency injection to manage object creation and wiring in a clean and test-friendly way.

Layers

Presentation Layer

Jetpack Compose UI

ViewModels that expose UI state

Handles user interactions and screen logic

Domain Layer

UseCases that contain business logic

Repository interfaces

Completely framework-independent

Data Layer

Repository implementations

Remote API integration using Retrofit

Local storage using Room for bookmarked news

🧩 Tech Stack

Language: Kotlin

UI: Jetpack Compose

Architecture: Clean Architecture + MVVM

Dependency Injection: Hilt

Networking: Retrofit

Local Storage: Room Database (for bookmarked news)

Asynchronous: Kotlin Coroutines

Testing:

JUnit

MockK

Compose UI Testing

CI/CD: GitHub Actions

Language: Kotlin

UI: Jetpack Compose

Architecture: Clean Architecture + MVVM

Dependency Injection: Hilt (Dagger)

Used across Data, Domain, and Presentation layers

ViewModels injected using @HiltViewModel

Application and Composables annotated with @HiltAndroidApp / @AndroidEntryPoint

Networking: Retrofit

Local Storage: Room Database (Bookmarks)

Asynchronous: Kotlin Coroutines

Testing:

JUnit

MockK

Compose UI Testing

CI/CD: GitHub Actions

🧪 Testing Strategy

Testing is treated as a first-class citizen in this project. The goal is to ensure confidence while refactoring and adding new features.

Unit Tests

Repository tests with mocked API responses using MockK

UseCase tests to verify business logic

ViewModel tests to validate UI state changes

Room-related tests for bookmarked news using in-memory databases

UI Tests

Jetpack Compose UI tests for major screens

Validation of user interactions and visible UI states

All tests are executed automatically as part of the CI pipeline.

🔐 API Key Security

The News API Key is not hardcoded

Stored securely using GitHub Secrets

Injected into the build process during CI execution

🚀 CI/CD Pipeline (GitHub Actions)

A GitHub Actions CI pipeline is set up to make sure every change is safe and buildable.

The pipeline runs on:

Push to master

Pull requests targeting master

What the pipeline does

Checks out the source code

Sets up JDK 17

Installs the Android SDK and SDK Platform 35

Caches Gradle dependencies to speed up builds

Builds the Debug APK

Runs unit and UI tests

Uploads the generated APK as a GitHub Actions artifact

The News API key is stored securely in GitHub Secrets and injected during the build, keeping sensitive data out of the codebase.

📦 Build Output

Debug APK is generated during CI

APK is uploaded as a GitHub Actions Artifact for easy download and testing
