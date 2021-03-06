package org.alfresco.utility.model;

import org.alfresco.utility.data.RandomData;

public class GroupModel extends TestModel
{
    private String groupIdentifier;
    private String displayName;


    public GroupModel()
    {
    }

    public GroupModel(String groupName)
    {
        setGroupIdentifier(groupName);
        setDisplayName(groupName);
    }

    public String getGroupIdentifier()
    {
        return groupIdentifier;
    }

    public void setGroupIdentifier(String groupIdentifier)
    {
        this.groupIdentifier = groupIdentifier;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public static GroupModel getEmailContributorsGroup()
    {
        return new GroupModel("EMAIL_CONTRIBUTORS");
    }

    public static GroupModel getRandomGroupModel()
    {
        String randomName = RandomData.getRandomName("group");
        return new GroupModel(randomName);
    }
}
