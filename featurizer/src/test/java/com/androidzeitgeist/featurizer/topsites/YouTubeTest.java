/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.topsites;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import junit.framework.Assert;

import org.junit.Test;

/**
 * YouTube.com
 */
public class YouTubeTest extends BaseTest {
    @Test
    public void testStartPage() throws Exception {
        final WebsiteFeatures features = loadAndFeaturize("youtube.com.html", "http://www.youtube.com");

        Assert.assertEquals("YouTube", features.getTitle());

        Assert.assertEquals(
                "http://s.ytimg.com/yts/img/yt_1200-vfl4C3T0K.png",
                features.getImageUrl());

        Assert.assertEquals(
                "http://s.ytimg.com/yts/img/favicon_32-vfl8NGn4k.png",
                features.getIconUrl());

        Assert.assertEquals(
                "https://www.youtube.com/",
                features.getUrl());

        Assert.assertEquals(
                "Enjoy the videos and music you love, upload original content, and share it all with friends, family, and the world on YouTube.",
                features.getDescription()
        );

        Assert.assertNull(features.getType());

        Assert.assertEquals(0xFFE62117, features.getColor());
    }

    @Test
    public void testVideoPage() throws Exception {
        WebsiteFeatures features = loadAndFeaturize("youtube.com_video.html", "https://www.youtube.com/watch?v=tRlmP6xWdw0");

        Assert.assertEquals("Welcome to Firefox - YouTube", features.getTitle());

        Assert.assertEquals(
                "https://i.ytimg.com/vi/tRlmP6xWdw0/maxresdefault.jpg",
                features.getImageUrl());

        Assert.assertEquals(
                "https://s.ytimg.com/yts/img/favicon_32-vfl8NGn4k.png",
                features.getIconUrl());

        Assert.assertEquals(
                "https://www.youtube.com/watch?v=tRlmP6xWdw0",
                features.getUrl());

        Assert.assertEquals(
                "The things you do online matter. When you use Firefox, you have the power to keep those things personal: http://mzl.la/1Q1WZM1. Welcome to personal freedom o...",
                features.getDescription()
        );

        Assert.assertEquals("video", features.getType());

        Assert.assertEquals(0xFFE62117, features.getColor());
    }
}
