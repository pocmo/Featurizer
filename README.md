[![Build Status](https://travis-ci.org/pocmo/Featurizer.svg?branch=master)](https://travis-ci.org/pocmo/Featurizer)

# Featurizer

An Android library for extracting features (title, icon, image, ...) from a website / URL.

Download
--------
Grab via Maven:
```xml
<dependency>
  <groupId>com.androidzeitgeist.featurizer</groupId>
  <artifactId>featurizer</artifactId>
  <version>1.1.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.androidzeitgeist.featurizer:featurizer:1.1.0'
```

Usage
-----
```java
final Featurizer featurizer = new Featurizer();
final WebsiteFeatures features = featurizer.featurize("https://www.youtube.com/watch?v=tRlmP6xWdw0");

Log.d("Featurizer", "Title: " + features.getTitle());
// Welcome to Firefox - YouTube

Log.d("Featurizer", "Description: " + features.getDescription());
// The things you do online matter. When you use Firefox, you have the power to keep those
// things personal: http://mzl.la/1Q1WZM1. Welcome to personal freedom o...

Log.d("Featurizer", "URL: " + features.getUrl());
// https://www.youtube.com/watch?v=tRlmP6xWdw0

Log.d("Featurizer", "Icon: " + features.getIconUrl());
// https://s.ytimg.com/yts/img/favicon_32-vfl8NGn4k.png

Log.d("Featurizer", "Image: " + features.getImageUrl());
// https://i.ytimg.com/vi/tRlmP6xWdw0/maxresdefault.jpg

Log.d("Featurizer", "Type: " + features.getType());
// video

Log.d("Featurizer", "Color: " + features.getColor());
// -1695465 (#FFE62117)
```

License
-------

    This Source Code Form is subject to the terms of the Mozilla Public
    License, v. 2.0. If a copy of the MPL was not distributed with this
    file, You can obtain one at http://mozilla.org/MPL/2.0/.
 
