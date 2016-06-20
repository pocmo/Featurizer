/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.rules;

import org.jsoup.nodes.Element;

/**
 * Rule for extracting text from elements matching a selector like CSS query.
 */
public class TextSelectorRule extends SelectorRule<String> {
    public TextSelectorRule(String cssQuery) {
        super(cssQuery);
    }

    @Override
    protected String process(Element element) {
        return element.text();
    }
}
