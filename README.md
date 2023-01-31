# PictureBook
A picture gallery application build in Android to search and display images from
Pixabay - Free Images repository.

## Features

- Search photos by type to search feature.
- Populate latest photos.
- Gallery support pagination.
- Implemented offline support with interceptor.
- More Details screen - Full scale selected image with details about user name, list of tags, number
  of like, comments and downloads for an image.


## Architecture
 - Using Modern Android Development practices.
 - Implemented MVVM-Clean Architecture using Usecase, Repository pattern for data.

## Android Jetpack Components
- Navigation Graphs for fragment navigation.
- Android Viewmodel.
- LiveData

## UI/UX
  ## Android UI Components
  - Material components - Snackbar, TextInputEditText, TextInputLayout, MaterialCardView, and MaterialAlertDialogBuilder.
  - AndroidX components - Splashscreen, Constraintlayout, and Appcompat views.

## DI Tools
- Dagger Hilt for DI implementation.

## Network Call Management
- Retrofit
- OKHTTP
- OKHTTP Interceptors

## External Libraries
- Kennyc1012 MultiStateView.
- GSON JSON parsing library.
- Intuit SDP-Android library with a new size unit - sdp (scalable dp) for supporting multiple screens.
- Glide - For lazy loading of an images with URL.

## Testing
 - Implemented unit test cases with JUnit and MockK framework.