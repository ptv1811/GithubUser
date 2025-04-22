👤 GithubUser
=============

🎯 **A Android application for managing GitHub user data.**

📁 Project Structure
--------------------

```
📦 GithubUser/
├── 📂 app/                 # Main application module
├── 📂 core/                # Core utilities and shared logic
│      └── 📂 common/       # Common module shared between modules
│      └── 📂 data/         # Data module which provides repositories
│      └── 📂 database/     # Database module 
│      └── 📂 model/        # Model module
│      └── 📂 network/      # Network module
│      └── 📂 testing/      # Testing module
├── 📂 features/            # Features module
│      └── 📂 userlist/     # User list feature module
├── 📂 gradle/              # Gradle wrapper and build scripts
├── 📄 build.gradle.kts     # Project-level build configuration
├── 📄 settings.gradle.kts  # Gradle settings
├── 📄 gradle.properties    # Gradle properties
├── 📄 .gitignore           # Git ignore rules
└── 📄 README.md            # Project documentation

```

🧱 Modules Overview
-------------------

-   **app/**: Entry point of the application, handling initialization and configuration.

-   **core/**: Contains shared utilities, constants, and helper functions.

-   **features/userlist/**: Manages the user list feature, including UI and business logic.

🛠️ Tech Stack
--------------

-   🧑‍💻 **Language**: ![Kotlin](https://img.shields.io/badge/Kotlin-1.8.0-blue?logo=kotlin&logoColor=white)

-   🏗️ **Architecture**: MVVM (Model-View-ViewModel)

-   📦 **Libraries**:

    -   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)

    -   **Networking**: [Retrofit](https://square.github.io/retrofit/)

    -   **Database**: [Room](https://developer.android.com/jetpack/androidx/releases/room)

    -   **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

    -   **Reactive Streams**: [Flow](https://kotlinlang.org/docs/flow.html)

    -   **Testing**: JUnit, [Turbine](https://github.com/cashapp/turbine) for Flow testing

🧱 Code Structure
------------------
-   📄 **HomeActivity**: Handles the display of the GitHub user list and manages pagination and error dialogs.  

-   📄 **HomeViewModel**: Manages the state and data for HomeActivity. Fetches data from the repository and handles errors using SharedFlow.  

-   📄 **GithubUserDao**: Provides database operations for storing and retrieving GitHub user data.  

-   📄 **UserEntityMapper**: Maps between UserEntity (database model) and GithubUser (domain model).

📄 License
----------

This project is licensed under the MIT License. See the LICENSE file for details.

* * * * *