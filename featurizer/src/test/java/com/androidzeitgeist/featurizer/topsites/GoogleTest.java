/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.topsites;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Google.com
 */
public class GoogleTest extends BaseTest {
    @Test
    public void testStartPage() throws Exception {
        final WebsiteFeatures features = loadAndFeaturize("google.com.html", "http://www.google.com");

        Assert.assertEquals("Google", features.getTitle());

        Assert.assertEquals(
                "http://www.google.com/logos/doodles/2016/first-day-of-summer-2016-northern-hemisphere-5669295896920064-thp.png",
                features.getImageUrl());

        Assert.assertEquals(
                "http://www.google.com/images/branding/product/ico/googleg_lodp.ico",
                features.getIconUrl()
        );

        Assert.assertEquals(
                "http://www.google.com/?ei=3-VoV7jzCaTe8gf1vbqgAg",
                features.getUrl()
        );

        Assert.assertNull(features.getType());

        Assert.assertEquals(0, features.getColor());
    }
}
