package ru.appline.core;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = {"ru/appline/core/steps"},
        tags = "@firstTest",
        strict = true
)

public class RunnerTest {}
