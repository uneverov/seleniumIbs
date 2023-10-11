package ru.appline.core;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources"),
        @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "ru.appline.core.steps"),
        @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "summary, pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"),
})

public class RunnerTest {}
