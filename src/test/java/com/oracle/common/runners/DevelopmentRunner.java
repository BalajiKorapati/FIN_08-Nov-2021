package com.oracle.common.runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = { "@AP00" }, 
		features = { "src/test/java/com/oracle/FIN/features" }, 
		plugin = { "pretty","html:target/html/ofs/" }, 
		glue = {"com.oracle.se", "com.oracle.common.steps", "com.oracle.common.runners", "com.oracle.FIN.steps"},
		dryRun=false
		)

public class DevelopmentRunner { }
