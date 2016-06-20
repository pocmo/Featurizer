/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.TextSelectorRule;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor implementation for finding the website's title.
 */
public class TitleExtractor implements Extractor {
    private final List<TextSelectorRule> titleRules = Arrays.asList(
            new TextSelectorRule("meta[property='og:title'"),
            new TextSelectorRule("meta[property='twitter:title']"),
            new TextSelectorRule("meta[name='hdl']"),
            new TextSelectorRule("head title")
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        for (final TextSelectorRule rule : titleRules) {
            final List<String> titles = rule.apply(document);

            if (!titles.isEmpty()) {
                final String title = titles.get(0);

                if (title == null || "".equals(title)) {
                    continue;
                }

                builder.setTitle(title);
                return;
            }
        }
    }
}
