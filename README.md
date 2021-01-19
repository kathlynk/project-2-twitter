# Project 2 - *Twitterly* : Part 2

**Twitterly** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **4** hours spent in total

## Gif Walkthrough

Here's a walkthrough of implemented user stories:

<img src="/twitterly-part2.gif" width=422><br>

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

---

# Project 2 - *Twitterly* : Part 1

**Twitterly** is an android app that allows a user to view his Twitter timeline. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **15** hours spent in total

## User Stories

The following **required** functionality is completed:

- [x] User can **sign in to Twitter** using OAuth login
- [x]	User can **view tweets from their home timeline**
  - [x] User is displayed the username, name, and body for each tweet
  - [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
- [x] User can refresh tweets timeline by pulling down to refresh

The following **optional** features are implemented:

- [x] User can view more tweets as they scroll with infinite pagination
- [x] User can tap a tweet to display a "detailed" view of that tweet
- [ ] User can open the twitter app offline and see last loaded tweets
- [x] On the Twitter timeline, leverage the CoordinatorLayout to apply scrolling behavior that hides / shows the toolbar.
- [x] User can see embedded image media within the tweet detail view
- [x] User can watch embedded video within the tweet.
- [x] User can tap a tweet to display a "detailed" view of that tweet.
- [x] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
- [x] User is using **"Twitter branded" colors and styles**


## Gif Walkthrough

Here's a walkthrough of implemented user stories:

<img src="/twitterly.gif" width=422><br>

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

I am not familiar with JSON and retrieving nested arrays of JSON object proved to be very confusing for me. I also had a lot of issues getting video to display properly.

## Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
- [Parceler](https://github.com/johncarl81/parceler) - An Android library to serialize Java Objects between Contexts
- [SimpleVideoView](https://github.com/klinker24/Android-SimpleVideoView) - Wrapper of Android's MediaPlayer for correct aspect-ratio of videos

## License

    Copyright [2020] [Patricia Booth]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
