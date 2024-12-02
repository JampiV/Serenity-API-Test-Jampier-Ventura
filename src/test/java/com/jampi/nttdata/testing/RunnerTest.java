package com.jampi.nttdata.testing;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.jampi.nttdata.testing",
        tags = "@Airlines")

public class RunnerTest
{

}
