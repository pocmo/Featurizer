/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer;

import com.androidzeitgeist.featurizer.extractor.ColorExtractor;
import com.androidzeitgeist.featurizer.extractor.DescriptionExtractor;
import com.androidzeitgeist.featurizer.extractor.Extractor;
import com.androidzeitgeist.featurizer.extractor.IconExtractor;
import com.androidzeitgeist.featurizer.extractor.ImageExtractor;
import com.androidzeitgeist.featurizer.extractor.TitleExtractor;
import com.androidzeitgeist.featurizer.extractor.TypeExtractor;
import com.androidzeitgeist.featurizer.extractor.UrlExtractor;
import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Class for extracting features (like title, icon, image) from a website.
 */
public class Featurizer {
    private static final List<Extractor> DEFAULT_EXTRACTORS = Collections.unmodifiableList(Arrays.asList(
            new TitleExtractor(),
            new ImageExtractor(),
            new IconExtractor(),
            new UrlExtractor(),
            new TypeExtractor(),
            new DescriptionExtractor(),
            new ColorExtractor()
    ));

    private OkHttpClient client;
    private List<Extractor> extractors;

    public Featurizer() {
        this(new OkHttpClient());
    }

    public Featurizer(OkHttpClient client) {
        this.client = client;
        this.extractors = DEFAULT_EXTRACTORS;
    }

    /**
     * Set a custom set of extractors to use for finding website features.
     */
    public void setExtractors(List<Extractor> extractors) {
        this.extractors = extractors;
    }

    /**
     * Extract features from the website the given URL is pointing to.
     */
    public WebsiteFeatures featurize(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        return featurize(request);
    }

    /**
     * Perform the given request and extract features from the returned website.
     */
    public WebsiteFeatures featurize(Request request) throws IOException {
        Response response = client.newCall(request)
                .execute();

        ResponseBody body = response.body();

        return featurize(
                body.byteStream(),
                body.contentType().charset(StandardCharsets.UTF_8).name(),
                response.request().url().toString());
    }

    /**
     * Extract features from the given stream using the charset and (base) URL passed in.
     */
    public WebsiteFeatures featurize(InputStream stream, String charset, String url) throws IOException {
        WebsiteFeatures.Builder builder = new WebsiteFeatures.Builder();

        Document document = Jsoup.parse(stream, charset, url);

        for (Extractor extractor : extractors) {
            extractor.extract(document, builder);
        }

        return builder.build();
    }
}
