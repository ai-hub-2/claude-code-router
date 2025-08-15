# Claude Code UI - Android App

A native Android application that provides a mobile interface for Claude Code CLI, built with Kotlin and following modern Android development practices.

## Features

- **Chat Interface**: Real-time chat with Claude AI assistant
- **File Management**: Browse and view project files
- **Terminal Access**: Access to terminal functionality
- **Settings Management**: Configure app preferences
- **Dark/Light Theme**: Support for both themes
- **Responsive Design**: Optimized for all screen sizes

## Architecture

- **MVVM Pattern**: Using ViewModel and LiveData
- **Hilt Dependency Injection**: For dependency management
- **Room Database**: Local data persistence
- **Retrofit**: Network communication
- **Navigation Component**: Fragment-based navigation
- **Material Design 3**: Modern UI components

## Requirements

- Android Studio Arctic Fox or later
- Android SDK 21+ (API level 21)
- Target SDK 34 (API level 34)
- Java 17
- Gradle 8.4+

## Setup

1. Clone the repository:
```bash
git clone https://github.com/your-username/claudecodeui-android.git
cd claudecodeui-android
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Build and run the application

## Building

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## Project Structure

```
app/src/main/java/com/claudecodeui/android/
├── activities/          # Activity classes
├── fragments/           # Fragment classes
├── adapters/            # RecyclerView adapters
├── viewmodels/          # ViewModel classes
├── models/              # Data models
├── network/             # Network layer (API, Repository)
├── utils/               # Utility classes
└── ui/                  # UI components
```

## Dependencies

### Core Android
- AndroidX Core KTX
- AndroidX AppCompat
- Material Design Components
- ConstraintLayout

### Architecture Components
- ViewModel
- LiveData
- Room Database
- Navigation Component

### Networking
- Retrofit2
- OkHttp3
- Gson

### Dependency Injection
- Hilt

### UI/UX
- Glide (Image loading)
- Markwon (Markdown rendering)
- Lottie (Animations)

## Configuration

The app connects to a Claude Code CLI server. Configure the server URL and API key in the settings.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Original Claude Code UI project: https://github.com/you112ef/claudecodeui
- Android development community
- Material Design team
