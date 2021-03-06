package org.alfresco.utility.network;

import static org.alfresco.utility.report.log.Step.STEP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.alfresco.utility.LogFactory;
import org.alfresco.utility.TasProperties;
import org.alfresco.utility.exception.ServerReachableAlfrescoIsNotRunningException;
import org.alfresco.utility.exception.ServerUnreachableException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerHealth
{
    private Logger LOG = LogFactory.getLogger();
    private static final String SERVER_VERSION_PATH = "/alfresco/service/api/server";
    private String alfrescoEdition;
    private String alfrescoVersion;

    @Autowired
    protected TasProperties properties;

    @Autowired
    private TenantConsole tenantConsole;

    public String getAlfrescoEdition()
    {
        return alfrescoEdition;
    }
    
    public String getAlfrescoVersion()
    {
        return alfrescoVersion;
    }

    public boolean isServerReachable(String server, int port)
    {        
        boolean reachable = false;
        try
        {
            try (Socket soc = new Socket())
            {
                soc.connect(new InetSocketAddress(server, port), 5000);
            }
            reachable = true;
        }
        catch (IOException ex)
        {
            LOG.info("Check Alfresco Test Server: {} is Reachable, found: {}", server, ex.getMessage());
            return false;
        }

        LOG.info("Check Alfresco Test Server: {} is Reachable, found: {}", server, reachable);
        return reachable;
    }

    public boolean isServerReachable()
    {
        STEP(String.format("Check the server %s is reachable", properties.getFullServerUrl()));
        boolean reachable = isServerReachable(properties.getServer(), properties.getPort());
        if(properties.showTenantsOnServerHealth())
        {
            try
            {
                LOG.info("Check if there are tenants on the server: {}", tenantConsole.tenantExist());
            }
            catch (IOException e)
            {
                LOG.error("Exception checking if there are tenants on the server", e);
            }
        }
        return reachable;
    }

    public boolean isAlfrescoRunning()
    {
        if (!isServerReachable())
            throw new ServerUnreachableException(properties);

        boolean isAlfrescoRunning = false;
        GetMethod get;
        String response;
        String alfrescoServerVersionPage = properties.getFullServerUrl() + SERVER_VERSION_PATH;
        LOG.info("Check Alfresco Test Server: {} is Online based on Admin System Summary Page {}.", properties.getServer(), alfrescoServerVersionPage);
        try
        {
            HttpClient client = new HttpClient();

            get = new GetMethod(alfrescoServerVersionPage);
            String unhashedString = String.format("%s:%s", properties.getAdminUser(), properties.getAdminPassword());
            get.setRequestHeader("Authorization", "Basic " + Base64.encodeBase64String(unhashedString.getBytes()));

            get.getParams().setSoTimeout(5000);
            client.executeMethod(get);
            response = IOUtils.toString(get.getResponseBodyAsStream(), CharEncoding.UTF_8);

            LOG.info(response.toString());

            get.releaseConnection();
            isAlfrescoRunning = response.contains("version");
            String responseEdition = response.split("\",")[0];
            String responseVersion = response.split("\",")[1];
            alfrescoEdition = responseEdition.substring(responseEdition.lastIndexOf("\"") + 1);
            alfrescoVersion = responseVersion.substring(responseVersion.lastIndexOf("\"") + 1);
        }
        catch (Exception ex)
        {
            LOG.error("Cannot GET {} page. Exception: {} ", alfrescoServerVersionPage, ex.getMessage());
            isAlfrescoRunning = false;
        }

        return isAlfrescoRunning;
    }

    /**
     * Throw an exception if the server is not online.
     *
     * @throws ServerReachableAlfrescoIsNotRunningException If the server is not online.
     */
    public void assertServerIsOnline()
    {
        if (!isAlfrescoRunning())
        {
            throw new ServerReachableAlfrescoIsNotRunningException(properties);
        }
    }
}
