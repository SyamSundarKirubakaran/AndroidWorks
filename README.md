# AndroidWorks

![Android](https://img.shields.io/badge/Platform-Android-green.svg)   ![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)   ![In Progress](https://img.shields.io/badge/in%20progress-true-yellow.svg) <br />
Repository that showcases Android Best Practices with Intermediate Project Creation skills

## Contents
**#1 Internet Interact**<br /><br />
**#2 Picasso Pick**<br /><br />
**#3 Move Movies**

## 1.Internet Interact:
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

## 2.Picasso Pick:
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

## 3.Move Movies:
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
**Show case:**
<br />
Work on progress..
