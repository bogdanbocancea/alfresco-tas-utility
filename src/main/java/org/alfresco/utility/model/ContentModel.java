package org.alfresco.utility.model;

import java.io.File;

import org.alfresco.utility.data.DataValue;

import com.google.common.io.Files;

public class ContentModel extends TestModel
{
    private String title;
    private String name;
    
    private String description;

    private File location = new File(DataValue.UNDEFINED.name());
    private  String protocolLocation;

    public ContentModel (String name)
    {
        setName(name);
    }
    
    public ContentModel(File location)
    {
        setLocation(location);
        setTitle(Files.getNameWithoutExtension(location.getName()));
    }

    public ContentModel(File location, String title)
    {
        setLocation(location);
        setTitle(title);
    }

    public ContentModel(File location, String title, String description)
    {
        this(location, title);
        setDescription(description);
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location.getPath();
    }

    public File getLocationPath()
    {
        return location;
    }

    public void setLocation(File location)
    {
        this.location = location;
        this.name = location.getName();
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getProtocolLocation()
    {
        return protocolLocation;
    }

    public void setProtocolLocation(String protocolLocation)
    {
        this.protocolLocation = protocolLocation;
    }

}