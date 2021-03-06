package org.alfresco.utility.testrail.core;

import java.util.ArrayList;
import java.util.List;

import org.alfresco.utility.Utility;
import org.alfresco.utility.testrail.model.Section;
import org.alfresco.utility.testrail.model.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

/**
 * Is the author that will perform actions using {@link TestRailAPI}
 */
public class TestRailExecutor
{
    public static Logger LOG = LoggerFactory.getLogger("testrail");

    private boolean isEnabled = Utility.isPropertyEnabled("testManagement.enabled");
    private boolean includeOnlyTestCasesExecuted = Utility.isPropertyEnabled("testManagement.includeOnlyTestCasesExecuted");

    private static TestRailAPI testRailAPI = new TestRailAPI();

    /*
     * all section from current project
     */
    private static List<Section> allServerSections = new ArrayList<Section>();
    private static List<TestCase> allServerTestCases = new ArrayList<TestCase>();

    public static List<Section> getAllSection()
    {
        return allServerSections;
    }

    public static boolean existsInAllSection(Section section)
    {
        boolean exist = false;

        for (Section oldS : getAllSection())
        {
            if (section.getId() == oldS.getId())
            {
                exist = true;
                break;
            }

        }
        return exist;
    }

    public static void addSections(List<Section> sections)
    {
        for (Section s : sections)
        {
            if (!existsInAllSection(s))
                getAllSection().add(s);
        }
    }

    public static List<TestCase> getAllTestCases()
    {
        return allServerTestCases;
    }

    /**
     * @return boolean value if TestRail integration is enabled or not (i.e. testManagement.enabled=true)
     */
    public boolean isEnabled()
    {
        return isEnabled;
    }

    public TestCaseDetail uploadTestCase(ITestResult currentTest)
    {
        TestCase newTestCase = null;
        /*
         * each test case is saved under particular sections like:
         * -- section-root
         * -----section-child1
         * -------section-child2
         * missing section(s) will be created prior of uploading the test
         * if that test case exist, we will return the object that will refer it
         */
        TestCaseDetail currentTestCase = new TestCaseDetail(currentTest);

        if (!currentTestCase.hasSectionCreatedIn(getAllSection()) && currentTestCase.isMarkForUpload())
        {
            testRailAPI.createNewSection(currentTestCase, getAllSection());
        }

        // check from already queried test cases

        for (TestCase tmpTC : getAllTestCases())
        {
            if(tmpTC.getCustom_auto_ref() != null)
            {
                if (tmpTC.getCustom_auto_ref().equals(currentTestCase.getId())
                        && tmpTC.getSection_id() == currentTestCase.getTestCaseDestination().getDestination().getId())
                {
                    newTestCase = tmpTC;
                    break;
                }
            }
        }

        /* if is not created already */
        if (newTestCase == null)
        {
            /*
             * call the Rate Limit wait loop method;
             */
            if (currentTestCase.getElapsed() < testRailAPI.getWateRateLimit())
                testRailAPI.waitForRateLimit();

            newTestCase = testRailAPI.uploadTestCase(currentTestCase);
        }

        // correlation between ITestResult object and TestRail object
        currentTestCase.setTestRailObject(newTestCase);
        currentTestCase.setResult(currentTest);
        return currentTestCase;
    }

    /**
     * read all sections created
     */
    public void prepareCurrentSuiteRun()
    {
        TestRailExecutor.addSections(testRailAPI.getSectionsOfCurrentProject());
        TestRailExecutor.allServerTestCases = testRailAPI.getAllTestCasesFromCurrentProject();
        testRailAPI.getRunOfCurrentProject();
    }

    public Object addResultsForCases(List<TestCaseDetail> currentTestCases) throws Exception
    {
        if (includeOnlyTestCasesExecuted)
        {
            // then we update the current test run and add ONLY the tests that were executed in this run.
            testRailAPI.updateTestRunWithSelectedTestCases(currentTestCases);
        }
        return testRailAPI.addResultsForCases(currentTestCases);
    }
}
