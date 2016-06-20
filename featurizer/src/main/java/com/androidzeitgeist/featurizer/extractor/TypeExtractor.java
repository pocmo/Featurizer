/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.ContentSelectorRule;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Extractor implementation for finding the website's type.
 */
public class TypeExtractor implements Extractor {
    private final ContentSelectorRule typeRule = new ContentSelectorRule("meta[property='og:type']", false);

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        final List<String> types = typeRule.apply(document);

        if (!types.isEmpty()) {
            builder.setType(types.get(0));
        }
    }
}
