/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.extractor;

import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import org.jsoup.nodes.Document;

/**
 * Generic interface for a class that extracts features from a document and adds them to a builder
 * instance.
 */
public interface Extractor {
    /**
     * Extract features from <code>document</code> and add them to the <code>builder</code>.
     */
    void extract(Document document, WebsiteFeatures.Builder builder);
}
