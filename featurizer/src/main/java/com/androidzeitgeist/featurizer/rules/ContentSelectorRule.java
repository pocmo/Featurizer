/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.rules;

import org.jsoup.nodes.Element;

/**
 * Rule for extracting the "content" attribute of elements that match a selector CSS query.
 */
public class ContentSelectorRule extends SelectorRule<String> {
    private boolean absoluteUrl;

    /**
     * @param cssQuery A selector like CSS query.
     * @param absoluteUrl True if the content attribute contains a URL and this rule should return the absolute URL.
     */
    public ContentSelectorRule(String cssQuery, boolean absoluteUrl) {
        super(cssQuery);

        this.absoluteUrl = absoluteUrl;
    }

    @Override
    protected String process(Element element) {
        if (absoluteUrl) {
            return element.attr("abs:content");
        } else {
            return element.attr("content");
        }
    }
}
