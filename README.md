
# Marvel Viewer

Test submission by Jaime Boden

Libaries Used
- Kotlin
    - To enable the Kotlin programming language to be used
- Retrofit/OkHttp
    - To allow intergration with the marvel API to retreive data from various endpoints
- Coroutines
    - To allow asynchronous network calls to be made on background threads
- Koin
    - Lightweight Dependency Injection Library, perfect for a small project
- Groupie
    - Library for building custom recyclerview items and removes some default boilerplate code
- Glide
    - Used to download images from the API into ImageViews
- Room
    - Database library used for caching data on the device
- JetPack Navigation Component
    - Navigation library from Google to allow easy transitions between fragments
- ktlint
    - Kotlin code lint checker, used to evaluate code style and report any issues

Known Issues
- Running on an emulator API 28 was having general performance issues
- Rotating the screen performs an API call again (activity is destroyed and redrawn)

TODOs
- Only caching initial series screen, next steps is to cache the details screen
- Abstract business logic on detail screen when no characters/ comics come back, could do with being taken off the main thread
- Remove SeriesConverter class and unify both Series (Model and DB) classes
- Much more test coverage