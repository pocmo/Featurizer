/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;
import com.androidzeitgeist.featurizer.rules.ContentSelectorRule;
import com.androidzeitgeist.featurizer.rules.HrefSelectorRule;
import com.androidzeitgeist.featurizer.rules.SelectorRule;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Extractor implementation for finding the website's (canonical) URL.
 */
public class UrlExtractor implements Extractor {
    private final List<SelectorRule<String>> urlRules = Arrays.asList(
            new ContentSelectorRule("meta[property='og:url']", true),
            new HrefSelectorRule("link[rel='canonical']")
    );

    @Override
    public void extract(Document document, WebsiteFeatures.Builder builder) {
        // (1) Use the CSS selector based rules to find the canonical URL

        for (final SelectorRule<String> rule : urlRules) {
            final List<String> urls = rule.apply(document);

            if (!urls.isEmpty()) {
                builder.setUrl(urls.get(0));
                return;
            }
        }

        // (2) Just use the URL (with redirects followed) we got the document from

        builder.setUrl(document.baseUri());
    }
}
