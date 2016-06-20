/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import android.graphics.Color;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.ContentSelectorRule;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor for finding a color for the website. The color is defined by website.
 */
public class ColorExtractor implements Extractor {
    private final List<ContentSelectorRule> colorRules = Arrays.asList(
            new ContentSelectorRule("meta[name='theme-color']", false),
            new ContentSelectorRule("meta[name='msapplication-navbutton-color']", false),
            new ContentSelectorRule("meta[name='apple-mobile-web-app-status-bar-style']", false)
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        for (final ContentSelectorRule rule : colorRules) {
            final List<String> colors = rule.apply(document);

            for (final String color : colors) {
                try {
                    builder.setColor(Color.parseColor(color));
                } catch (IllegalArgumentException e) {
                    // Parsing the color value failed. Try the next one.
                }
            }
        }

        // TODO: If all rules fail we could try to extract the color from an icon or image.
    }
}
