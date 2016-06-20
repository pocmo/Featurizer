/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.rules;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for rules that use a selector like CSS query to find elements.
 */
public abstract class SelectorRule<T> implements Rule<T> {
    private final String cssQuery;

    public SelectorRule(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    protected abstract T process(Element element);

    @Override
    public List<T> apply(Document document) {
        final Elements elements = document.select(cssQuery);

        final List<T> results = new ArrayList<>();

        for (int i = 0; i < elements.size(); i++) {
            final T result = process(elements.get(i));

            if (result != null) {
                results.add(result);
            }
        }

        return results;
    }
}
