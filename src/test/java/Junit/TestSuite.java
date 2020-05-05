package Junit;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("src.test.java.Junit")
@SelectClasses( { RegisterDeveloper.class, RegisterProject.class } )
public class TestSuite {

}
