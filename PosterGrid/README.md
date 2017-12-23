# Popular-Movies-Stage-II

![Android](https://img.shields.io/badge/Platform-Android-green.svg)   ![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)
<br />
Submission for Android NanoDegree Project

## Contents
**#1 [Movie Feast - II](https://github.com/SyamSundarKirubakaran/Popular-Movies-Stage-II)** 
- Nano Degree Submission - Popular Movies Stage II

## Preliminary works
- Check out my [AndroidWorks](https://github.com/SyamSundarKirubakaran/AndroidWorks) Repository to find out how I learnt step by step to accomplish this result.

## Movie Feast - II:
An app that uses the [Movies.db API](https://www.themoviedb.org/?language=en) to find the popular movies,Top rated,Now playing and Up coming movies in realtime and inflate the activities with the data of those movies accordingly. <br />

**Things I've Learnt:**
* Working with [Movies API](https://www.themoviedb.org/?language=en)
* [Networking in Android](https://developer.android.com/training/basics/network-ops/connecting.html)
* JSON parsing
* Scrolling view
* Picasso
* Bottom Navigation Bar
* Grid View
* Play videos in YouTube using implicit intents
* Favorite movies tracking
* Content Providers
* SQLite Databases
* Working with URIs
* Using share Intent
* Handle Requests in absence of network
* Offline Storage of favorite movies
* Bug fix<br />

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
<img src="assert/popular.png" height=480 width =270 />
<img src="assert/top_rated.png" height=480 width =270 />
<img src="assert/now_playing.png" height=480 width =270 />
<img src="assert/up_coming.png" height=480 width =270 />
<img src="assert/overview.png" height=480 width =270 />
<img src="assert/pre_video.png" height=480 width =270 />
<img src="assert/youtube.png" height=480 width =270 />
<img src="assert/share.png" height=480 width =270 />
<img src="assert/empty_fav.png" height=480 width =270 />
<img src="assert/add_fav.png" height=480 width =270 />
<img src="assert/fav.png" height=480 width =270 />
<img src="assert/rem_fav.png" height=480 width =270 /><br />
**In absence of Network:**<br />
<img src="assert/no_network.png" height=480 width =270 />
<img src="assert/fav_full.png" height=480 width =270 />


