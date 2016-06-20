/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.rules;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Generic interface for a rule that is applied to a document and returns a generic list of matches.
 */
public interface Rule<T> {
    List<T> apply(Document document);
}
