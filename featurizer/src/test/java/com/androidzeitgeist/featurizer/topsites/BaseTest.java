package com.androidzeitgeist.featurizer.topsites;

import com.androidzeitgeist.featurizer.BuildConfig;
import com.androidzeitgeist.featurizer.Featurizer;
import com.androidzeitgeist.featurizer.features.WebsiteFeatures;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public abstract class BaseTest {
    public WebsiteFeatures loadAndFeaturize(String fileName, String baseUrl) throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(fileName);

        Featurizer featurizer = new Featurizer();
        return featurizer.featurize(stream, "UTF-8", baseUrl);
    }
}
