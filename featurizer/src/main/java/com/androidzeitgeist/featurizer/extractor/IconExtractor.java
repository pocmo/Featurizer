/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import android.net.Uri;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.HrefSelectorRule;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor implementation for the website's icon.
 */
public class IconExtractor implements Extractor {
    private final List<HrefSelectorRule> iconRules = Arrays.asList(
            new HrefSelectorRule("link[rel='apple-touch-icon']"),
            new HrefSelectorRule("link[rel='apple-touch-icon-precomposed']"),
            new HrefSelectorRule("link[rel='icon']"),
            new HrefSelectorRule("link[rel='fluid-icon']"),
            new HrefSelectorRule("link[rel='shortcut icon']"),
            new HrefSelectorRule("link[rel='Shortcut Icon']"),
            new HrefSelectorRule("link[rel='mask-icon']")
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        // TODO: We could (and should) evaluate an existing app manifest too.

        // (1) First try to find an icon by using CSS selectors.

        for (final HrefSelectorRule rule : iconRules) {
            final List<String> results = rule.apply(document);

            if (!results.isEmpty()) {
                // TODO: We should introduce some ranking (or add all icons) instead of just using the first one.
                builder.setIconUrl(results.get(0));
                return;
            }
        }

        // (2) Guess the default favicon URL.

        final String defaultFaviconUrl = Uri.parse(document.baseUri())
                .buildUpon()
                .path("favicon.ico")
                .toString();

        builder.setIconUrl(defaultFaviconUrl);
    }
}
