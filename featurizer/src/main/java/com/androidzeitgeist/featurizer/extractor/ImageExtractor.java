/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.ContentSelectorRule;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor implementation for finding an image that represents the website best.
 */
public class ImageExtractor implements Extractor {
    private final List<ContentSelectorRule> imageRules = Arrays.asList(
            new ContentSelectorRule("meta[property='og:image']", true),
            new ContentSelectorRule("meta[property='twitter:image']", true),
            new ContentSelectorRule("meta[name='thumbnail']", true)
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        // (1) Find image using css selector based rules

        for (ContentSelectorRule rule : imageRules) {
            final List<String> urls = rule.apply(document);

            if (!urls.isEmpty()) {
                builder.setImageUrl(urls.get(0));
                return;
            }
        }

        // (2) If we didn't find an "official" image, then let's try to find the best in the document

        final String url = findBestImage(document);
        if (url != null) {
            builder.setImageUrl(url);
        }
    }

    /**
     * Find the best image representing the web page.
     *
     * This implementation is heavily inspired by this blog posting:
     * https://tech.shareaholic.com/2012/11/02/how-to-find-the-image-that-best-respresents-a-web-page/
     */
    private String findBestImage(Document document) {
        int bestImageSize = 0;
        String bestImageUrl = null;

        // TODO: We could/should limit this search to content divs, if we find any.
        final Elements images = document.select("img");

        for (final Element image : images) {
            if (!image.hasAttr("width") || !image.hasAttr("height")) {
                // Just skip if there's no width and height defined in the document.
                // TODO: We could download some bits to determine the size, see: https://github.com/sdsykes/fastimage
                continue;
            }

            final int width;
            final int height;

            try {
                width = Integer.parseInt(image.attr("width"));
                height = Integer.parseInt(image.attr("height"));
            } catch (NumberFormatException e) {
                // It looks like values of width/height are not numeric. Move on.
                continue;
            }

            final double ratio = (double) width / (double) height;
            if (ratio > 3.0) {
                // We are not interested in banners or skyscrapers.
                continue;
            }

            final int size = width * height;
            if (size > bestImageSize) {
                bestImageSize = size;
                bestImageUrl = image.attr("abs:src");
            }
        }

        if (bestImageSize >= 5000) {
            // The image size should be at least 5000 (~ 70x70)
            return bestImageUrl;
        }

        return null;
    }
}
