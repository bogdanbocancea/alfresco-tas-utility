package org.alfresco.utility.dsl;

import static org.alfresco.utility.report.log.Step.STEP;

import org.alfresco.utility.exception.TestConfigurationException;
import org.testng.Assert;

public class DSLAssertion<Protocol>
{
    protected DSLProtocol<?> dslProtocol;

    public DSLAssertion(DSLProtocol<?> dslProtocol)
    {
        this.dslProtocol = dslProtocol;
    }

    @SuppressWarnings("unchecked")
    public Protocol getProtocol()
    {
        return (Protocol) dslProtocol;
    }

    @SuppressWarnings("unchecked")
    public Protocol existsInRepo() throws TestConfigurationException
    {
        STEP(String.format("CMIS: Assert that content '%s' exists in repository", dslProtocol.getLastResourceWithoutPrefix()));
        dslProtocol.dataContent.usingUser(dslProtocol.getTestUser()).setLastResource(dslProtocol.getLastResourceWithoutPrefix()).assertContentExist();
        return (Protocol) dslProtocol;
    }

    @SuppressWarnings("unchecked")
    public Protocol doesNotExistInRepo() throws TestConfigurationException
    {
        STEP(String.format("CMIS: Assert that content '%s' doesn't exist in repository", dslProtocol.getLastResourceWithoutPrefix()));
        dslProtocol.dataContent.usingUser(dslProtocol.getTestUser()).setLastResource(dslProtocol.getLastResourceWithoutPrefix()).assertContentDoesNotExist();
        return (Protocol) dslProtocol;
    }
    
    /**
     * Just verify using JMX calls if the protocol is enabled on server or not
     */
    public Protocol protocolIsEnabled() throws Exception
    {
        Assert.assertTrue(dslProtocol.isProtocolEnabled(), String.format("%s protocol is enabled", dslProtocol.getProtocolName()));
        return getProtocol();
    }
    
    /**
     * Just verify using JMX calls if the protocol is enabled on server or not
     */
    public Protocol protocolIsDisabled() throws Exception
    {
        Assert.assertFalse(dslProtocol.isProtocolEnabled(), String.format("%s protocol is disabled", dslProtocol.getProtocolName()));
        return getProtocol();
    }

}
