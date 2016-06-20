/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.androidzeitgeist.featurizer.features;

/**
 * An object of this class groups multiple features of a website into a single instance.
 */
public class WebsiteFeatures {
    private String title;
    private String imageUrl;
    private String iconUrl;
    private String url;
    private String type;
    private String description;
    private int color;

    private WebsiteFeatures() {}

    /**
     * Get the title of the website.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the URL of an image that represents this website. This can be an "official" image defined
     * by the website or the best guess looking at all the images on the website.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Get the URL of an icon for the website. This can be a favicon, an "apple touch icon" or one
     * of the other types of icons a website can define.
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * Get the canonical URL of the website. If the website does not define an URL then this will
     * return the URL we parsed the document from (after following all redirects).
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get the (open graph) type of the website.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the description of the website. This method will only return descriptions defined by the
     * website and not try to generate a description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the color for this website. The color is defined by the website. The current implementation
     * will not try to generate a color. Returns 0 if no color is defined.
     */
    public int getColor() {
        return color;
    }

    public static class Builder {
        private WebsiteFeatures features;

        public Builder() {
            features = new WebsiteFeatures();
        }

        public Builder setTitle(String title) {
            features.title = title;
            return this;
        }

        public Builder setImageUrl(String url) {
            features.imageUrl = url;
            return this;
        }

        public Builder setIconUrl(String url) {
            features.iconUrl = url;
            return this;
        }

        public Builder setUrl(String url) {
            features.url = url;
            return this;
        }

        public Builder setType(String type) {
            features.type = type;
            return this;
        }

        public Builder setDescription(String type) {
            features.description = type;
            return this;
        }

        public Builder setColor(int color) {
            features.color = color;
            return this;
        }

        public WebsiteFeatures build() {
            return features;
        }
    }
}
