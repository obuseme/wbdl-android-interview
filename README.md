
# Marvel Viewer

Test submission by Jaime Boden

Main Libaries Used
- 100% Kotlin
- Retrofit/ OkHttp
- Coroutines
- Koin (For Dependency Injection)
- Groupie (RecyclerView)
- Glide (Images)
- Room (for DB Cashing)
- JetPack Navigation Component (for Navigation)

Known Issues
- Running on an emulator API 28 was having general performance issues
- Rotating the screen performs an API call again (activity is destroyed and redrawn)

TODOs
- Only caching initial series screen, next steps is to cache the details screen TODO
- Abstract business logic on detail screen when no characters/ comics come back, could do with being taken off the main thread
- Remove SeriesConverter class and unify both Series (Model and DB) classes
- Much more test coverage