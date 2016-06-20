/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.ContentSelectorRule;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor implementation for the website's description.
 */
public class DescriptionExtractor implements Extractor {
    private final List<ContentSelectorRule> descriptionRules = Arrays.asList(
            new ContentSelectorRule("meta[property='og:description']", false),
            new ContentSelectorRule("meta[name='description']", false)
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        for (final ContentSelectorRule rule : descriptionRules) {
            final List<String> descriptions = rule.apply(document);

            if (!descriptions.isEmpty()) {
                builder.setDescription(descriptions.get(0));
                return;
            }
        }
    }
}
