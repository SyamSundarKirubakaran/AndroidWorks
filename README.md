# AndroidWorks

![Android](https://img.shields.io/badge/Platform-Android-green.svg)   ![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)   ![In Progress](https://img.shields.io/badge/in%20progress-true-yellow.svg) <br />
Repository that showcases Android Best Practices with Intermediate Project Creation skills

## Contents
**#1 [Internet Interact](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/InternetInteract)**
<br /><br />
**#2 [Pick Picasso](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/pickpicasso)**<br /><br />
**#3 [Move Movies](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/MoveMovie)**
<br /><br />
**#4 [Movie Feast](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/PosterGrid) @Milestone_Project #1** 
-Nano Degree Submission - Popular Movies Stage I (sub-repo name: [PosterGrid](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/PosterGrid)) 
<br /><br />
**#5 [SQLite Light](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/SQLiteLight)**
<br /><br />
**#6 [Lifecycle Lemma](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/LifecycleLemma)**
<br /><br />
**#7 [Preferences Preserved](https://github.com/SyamSundarKirubakaran/AndroidWorks/tree/master/PreferencePreserved)**

## 1. Internet Interact:
An app used to find the followers in Github by supplying his/her accounts user name.(**NOTE:** The app can only show 30 followers at a time since the **GitHub API** JSON Objects are restricted to 30 for an URL). <br />

**You'll Learn:**
* [Networking in Android](https://developer.android.com/training/basics/network-ops/connecting.html)
* JSON parsing
* Simple List view
* Array List and Adapters<br />

**Github URL for followers Retival:** <br />
<br />
`https://api.github.com/users/ <<User name>> /followers` <br />
<br />
**Show case:**
<br />
<p align="center">
  <img src="asserts/gifs/internet_interact.gif">
</p>

## 2. Pick Picasso:
A simple app that download the image from the given URL and displays it in an image view using **Picasso** library to fetch the image<br />

**You'll Learn:**
* [Picasso](http://square.github.io/picasso/)
* On click handling <br />

**URL used for image retival** <br />
<br />
`https://image.tmdb.org/t/p/w500//oSLd5GYGsiGgzDPKTwQh7wamO8t.jpg` <br />
<br />
Replace the above URL with our own.<br />
<br />
**Show case:**
<br />
<p align="center">
  <img src="asserts/gifs/picasso_pick.gif">
</p>

## 3. Move Movies:
An app that uses the [Movies.db API](https://www.themoviedb.org/?language=en) to find the popular movies and inflate the activities with the data of those movies accordingly. <br />

**You'll Learn:**
* Working with [Movies API](https://www.themoviedb.org/?language=en)
* [Networking in Android](https://developer.android.com/training/basics/network-ops/connecting.html)
* JSON parsing
* Simple List view
* Array List and Adapters<br />

**Popular movies in the form of JSON:** <br />
<br />
`https://api.themoviedb.org/3/movie/popular?api_key= <<YOUR_API_KEY>> ` <br />
<br />
**Note:**<br />
Get your API key and place it in the strings.xml file in the resource directory.<br />
``` xml
<string name="API_Key">Your API Key</string>
```
**Show case:**<br />
<p align="center">
  <img src="asserts/gifs/movie.gif">
</p>

## 4. Movie Feast:
An app that uses the [Movies.db API](https://www.themoviedb.org/?language=en) to find the popular movies and Top rated movies in realtime and inflate the activities with the data of those movies accordingly. <br />

**You'll Learn:**
* Working with [Movies API](https://www.themoviedb.org/?language=en)
* [Networking in Android](https://developer.android.com/training/basics/network-ops/connecting.html)
* JSON parsing
* Scrolling view
* Picasso
* Bottom Tabbed Activity<br />

**Popular movies in the form of JSON:** <br />
<br />
`https://api.themoviedb.org/3/movie/popular?api_key= <<YOUR_API_KEY>> ` <br />
<br />
`https://api.themoviedb.org/3/movie/top_rated?api_key= <<YOUR_API_KEY>> ` <br />
<br />
**Note:**<br />
Get your API key and place it in the strings.xml file in the resource directory.(NOTE: Marked as TODO in strings.xml file)<br />
``` xml
<string name="API_Key">Your API Key</string>
```
**Show case:**<br />
<p align="center">
  <img src="asserts/gifs/movie_feast.gif">
</p>

## 5. SQLite Light:
An app that uses the [SQLite](https://www.sqlite.org/) database to store data premanently and retrieve it when ever necessary.<br />

**You'll Learn:**
* Working with [SQLite Databases](https://www.sqlite.org/)
* [SQL Query](https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html)
* Data Presistence
* List View Updation after specific operation on the Database<br />

**Base Understanding for:** <br />
* Content Providers
* Working with URIs to query out from Database

**Note:**<br />
Mainly focuses on Data Presistence.

**Show case:**<br />
<p align="center">
  <img src="asserts/gifs/sqlite_light.gif">
</p>

## 6. Lifecycle Lemma:
An app that illustrates the working of android lifecycle and which preserves data between screen orientation changes using onSavedInstanceStates and callbacks.<br />

**You'll Learn:**
* Short term Data presistence
* Lifecycle callbacks
* onSavedInstanceState method to preserve data between orientation shift that leads to data teardowns.<br />

**Show case:**<br />
<p align="center">
  <img src="asserts/gifs/Lifecycle_lemma.gif">
</p>

## 7. Preferences Preserved:
An app that illustrates how to manage users Preferences for you app and also helps to store users preference so that those preferences remainds default even when the app is relaunched.<br />

**You'll Learn:**
* Short term Data presistence
* Setting up Settings Activity
* Check box Preferences, List Preferences and Edit text Preferences.
* Handling and Managing user's preferences so that UI can be changed accordingly.<br />

**Show case:**<br />
<p align="center">
  <img src="asserts/gifs/prefs.gif">
</p>

