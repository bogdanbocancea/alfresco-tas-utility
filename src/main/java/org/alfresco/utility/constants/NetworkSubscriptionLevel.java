package org.alfresco.utility.constants;

/**
 * @author Cristina Axinte
 */
public enum NetworkSubscriptionLevel
{
    FREE("Free"), STANDARD("Standard"), ENTERPRISE("Enterprise");

    private final String networkLevel;

    private NetworkSubscriptionLevel(final String networkLevel)
    {
        this.networkLevel = networkLevel;
    }

    @Override
    public String toString()
    {
        return networkLevel;
    }
}
