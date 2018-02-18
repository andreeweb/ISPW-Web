package it.uniroma2.dicii.ispw.junit;

import junit.framework.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        IssueManagementControllerTest.class,
        LoginControllerTest.class
})


public class JunitTestSuite {

    //	Tests will be automatically retrieved from
    //  the classes specified in @SuiteClasses
}
