/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.topsites;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Facebook.com
 */
public class FacebookTest extends BaseTest {
    @Test
    public void testStartPage() throws Exception {
        final WebsiteFeatures features = loadAndFeaturize("facebook.com.html", "http://www.facebook.com");

        Assert.assertEquals("Facebook - Log In or Sign Up", features.getTitle());

        Assert.assertEquals(
                "https://www.facebook.com/rsrc.php/yl/r/H3nktOa7ZMg.ico",
                features.getIconUrl());

        Assert.assertEquals(
                "https://www.facebook.com/images/fb_icon_325x325.png",
                features.getImageUrl());

        Assert.assertEquals(
                "Create an account or log into Facebook. Connect with friends, family and other people you know. Share photos and videos, send messages and get updates.",
                features.getDescription());

        Assert.assertNull(features.getType());

        Assert.assertEquals(
                "https://www.facebook.com/",
                features.getUrl());
    }

    @Test
    public void testBrandPage() throws Exception {
        final WebsiteFeatures features = loadAndFeaturize("facebook.com_page.html", "https://www.facebook.com/Firefox");

        Assert.assertEquals("Firefox", features.getTitle());

        Assert.assertEquals(
                "https://www.facebook.com/rsrc.php/yl/r/H3nktOa7ZMg.ico",
                features.getIconUrl());

        Assert.assertEquals(
                "https://external.ftxl1-1.fna.fbcdn.net/safe_image.php?d=AQCkd0P3S1fwEeH3&w=470&h=313&url=https%3A%2F%2Fwww.facebook.com%2Fads%2Fimage%2F%3Fd%3DAQJ_B3ZuVE8k1YkxvZHt7Djzyl1aTQ4IsHELBpJZhNmwB1uHiHKZrbh99Cw4BFsHcEEf5jbueZfs6aV5uyNS6Q2YzrDGl9b3DCGlj20VaehcRLKHQ6w_WCK5rjVJLrO98j0hAEIfqTonBs47dqT78_of",
                features.getImageUrl());

        Assert.assertEquals(
                "Firefox. 19,138,435 likes · 16,800 talking about this. Meet the world’s best browser, made just the way you like it! Get Firefox: http://mzl.la/1KSzUVg.",
                features.getDescription());

        Assert.assertNull(features.getType());

        Assert.assertEquals(
                "https://www.facebook.com/Firefox",
                features.getUrl());

        Assert.assertEquals(0, features.getColor());
    }

    @Test
    public void testMobileBrandPage() throws Exception {
        final WebsiteFeatures features = loadAndFeaturize("facebook.com_mobile.html", "https://m.facebook.com/Firefox");

        Assert.assertEquals("Firefox", features.getTitle());

        Assert.assertEquals(
                "https://m.facebook.com/favicon.ico",
                features.getIconUrl());

        Assert.assertEquals(
                "https://external.ftxl1-1.fna.fbcdn.net/safe_image.php?d=AQAlAn5EXC1dikhy&w=112&h=112&url=https%3A%2F%2Fwww.facebook.com%2Fads%2Fimage%2F%3Fd%3DAQJ_B3ZuVE8k1YkxvZHt7Djzyl1aTQ4IsHELBpJZhNmwB1uHiHKZrbh99Cw4BFsHcEEf5jbueZfs6aV5uyNS6Q2YzrDGl9b3DCGlj20VaehcRLKHQ6w_WCK5rjVJLrO98j0hAEIfqTonBs47dqT78_of&cfs=1&jq=75&ext=jpg",
                features.getImageUrl());

        Assert.assertEquals(
                "Firefox. 19,138,437 likes · 16,800 talking about this. Meet the world’s best browser, made just the way you like it! Get Firefox: http://mzl.la/1KSzUVg.",
                features.getDescription());

        Assert.assertNull(features.getType());

        Assert.assertEquals(
                "https://m.facebook.com/Firefox",
                features.getUrl());

        Assert.assertEquals(0, features.getColor());
    }
}
