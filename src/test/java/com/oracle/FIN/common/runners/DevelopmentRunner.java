package com.oracle.FIN.common.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import io.*;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "@AP001",
		features = { "src/test/java/com/oracle/FIN/FIN/features/" },
		plugin = { "pretty","html:target/html/ofs/" }, 
		glue = {"com.oracle.FIN.common.steps", "com.oracle.FIN.common.runners", "com.oracle.FIN.FIN.steps"},
		dryRun=false
		)

public class DevelopmentRunner { }
