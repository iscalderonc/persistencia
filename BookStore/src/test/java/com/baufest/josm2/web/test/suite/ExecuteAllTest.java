package com.baufest.josm2.web.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.baufest.josm2.web.test.data.Library_Data_Test;
import com.baufest.josm2.web.test.practice.Practice_Test;
import com.baufest.josm2.web.test.stubbing.Library_Stubbing_Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		Library_Data_Test.class,
		Library_Stubbing_Test.class,
		Practice_Test.class
	})

public class ExecuteAllTest {}
