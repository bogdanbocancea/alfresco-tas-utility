//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.09.08 at 01:38:05 PM GMT+02:00 
//


package org.alfresco.utility.extension;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="TestCases"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="testcase" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *                             &lt;element name="actualValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="expectedValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                             &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "description",
    "testCases"
})
@XmlRootElement(name = "ExtensionPointTestSuit")
public class ExtensionPointTestSuit
    implements Equals, HashCode, ToString
{

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(name = "TestCases", required = true)
    protected ExtensionPointTestSuit.TestCases testCases;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the testCases property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionPointTestSuit.TestCases }
     *     
     */
    public ExtensionPointTestSuit.TestCases getTestCases() {
        return testCases;
    }

    /**
     * Sets the value of the testCases property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionPointTestSuit.TestCases }
     *     
     */
    public void setTestCases(ExtensionPointTestSuit.TestCases value) {
        this.testCases = value;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ExtensionPointTestSuit)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ExtensionPointTestSuit that = ((ExtensionPointTestSuit) object);
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            ExtensionPointTestSuit.TestCases lhsTestCases;
            lhsTestCases = this.getTestCases();
            ExtensionPointTestSuit.TestCases rhsTestCases;
            rhsTestCases = that.getTestCases();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "testCases", lhsTestCases), LocatorUtils.property(thatLocator, "testCases", rhsTestCases), lhsTestCases, rhsTestCases)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            String theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            ExtensionPointTestSuit.TestCases theTestCases;
            theTestCases = this.getTestCases();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "testCases", theTestCases), currentHashCode, theTestCases);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            ExtensionPointTestSuit.TestCases theTestCases;
            theTestCases = this.getTestCases();
            strategy.appendField(locator, this, "testCases", buffer, theTestCases);
        }
        return buffer;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="testcase" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
     *                   &lt;element name="actualValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="expectedValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                   &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "testcase"
    })
    public static class TestCases
        implements Equals, HashCode, ToString
    {

        protected List<ExtensionPointTestSuit.TestCases.Testcase> testcase;

        /**
         * Gets the value of the testcase property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the testcase property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTestcase().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ExtensionPointTestSuit.TestCases.Testcase }
         * 
         * 
         */
        public List<ExtensionPointTestSuit.TestCases.Testcase> getTestcase() {
            if (testcase == null) {
                testcase = new ArrayList<ExtensionPointTestSuit.TestCases.Testcase>();
            }
            return this.testcase;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof ExtensionPointTestSuit.TestCases)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final ExtensionPointTestSuit.TestCases that = ((ExtensionPointTestSuit.TestCases) object);
            {
                List<ExtensionPointTestSuit.TestCases.Testcase> lhsTestcase;
                lhsTestcase = (((this.testcase!= null)&&(!this.testcase.isEmpty()))?this.getTestcase():null);
                List<ExtensionPointTestSuit.TestCases.Testcase> rhsTestcase;
                rhsTestcase = (((that.testcase!= null)&&(!that.testcase.isEmpty()))?that.getTestcase():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "testcase", lhsTestcase), LocatorUtils.property(thatLocator, "testcase", rhsTestcase), lhsTestcase, rhsTestcase)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

        public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
            int currentHashCode = 1;
            {
                List<ExtensionPointTestSuit.TestCases.Testcase> theTestcase;
                theTestcase = (((this.testcase!= null)&&(!this.testcase.isEmpty()))?this.getTestcase():null);
                currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "testcase", theTestcase), currentHashCode, theTestcase);
            }
            return currentHashCode;
        }

        public int hashCode() {
            final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
            return this.hashCode(null, strategy);
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                List<ExtensionPointTestSuit.TestCases.Testcase> theTestcase;
                theTestcase = (((this.testcase!= null)&&(!this.testcase.isEmpty()))?this.getTestcase():null);
                strategy.appendField(locator, this, "testcase", buffer, theTestcase);
            }
            return buffer;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
         *         &lt;element name="actualValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="expectedValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *         &lt;element name="stackTrace" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "description",
            "duration",
            "actualValue",
            "expectedValue",
            "stackTrace"
        })
        public static class Testcase
            implements Equals, HashCode, ToString
        {

            @XmlElement(required = true)
            protected String description;
            protected int duration;
            @XmlElement(required = true)
            protected String actualValue;
            @XmlElement(required = true)
            protected String expectedValue;
            @XmlElement(required = true)
            protected String stackTrace;
            @XmlAttribute(name = "name")
            protected String name;

            /**
             * Gets the value of the description property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDescription() {
                return description;
            }

            /**
             * Sets the value of the description property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDescription(String value) {
                this.description = value;
            }

            /**
             * Gets the value of the duration property.
             * 
             */
            public int getDuration() {
                return duration;
            }

            /**
             * Sets the value of the duration property.
             * 
             */
            public void setDuration(int value) {
                this.duration = value;
            }

            /**
             * Gets the value of the actualValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getActualValue() {
                return actualValue;
            }

            /**
             * Sets the value of the actualValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setActualValue(String value) {
                this.actualValue = value;
            }

            /**
             * Gets the value of the expectedValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getExpectedValue() {
                return expectedValue;
            }

            /**
             * Sets the value of the expectedValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setExpectedValue(String value) {
                this.expectedValue = value;
            }

            /**
             * Gets the value of the stackTrace property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getStackTrace() {
                return stackTrace;
            }

            /**
             * Sets the value of the stackTrace property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setStackTrace(String value) {
                this.stackTrace = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof ExtensionPointTestSuit.TestCases.Testcase)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final ExtensionPointTestSuit.TestCases.Testcase that = ((ExtensionPointTestSuit.TestCases.Testcase) object);
                {
                    String lhsDescription;
                    lhsDescription = this.getDescription();
                    String rhsDescription;
                    rhsDescription = that.getDescription();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                        return false;
                    }
                }
                {
                    int lhsDuration;
                    lhsDuration = this.getDuration();
                    int rhsDuration;
                    rhsDuration = that.getDuration();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "duration", lhsDuration), LocatorUtils.property(thatLocator, "duration", rhsDuration), lhsDuration, rhsDuration)) {
                        return false;
                    }
                }
                {
                    String lhsActualValue;
                    lhsActualValue = this.getActualValue();
                    String rhsActualValue;
                    rhsActualValue = that.getActualValue();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "actualValue", lhsActualValue), LocatorUtils.property(thatLocator, "actualValue", rhsActualValue), lhsActualValue, rhsActualValue)) {
                        return false;
                    }
                }
                {
                    String lhsExpectedValue;
                    lhsExpectedValue = this.getExpectedValue();
                    String rhsExpectedValue;
                    rhsExpectedValue = that.getExpectedValue();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "expectedValue", lhsExpectedValue), LocatorUtils.property(thatLocator, "expectedValue", rhsExpectedValue), lhsExpectedValue, rhsExpectedValue)) {
                        return false;
                    }
                }
                {
                    String lhsStackTrace;
                    lhsStackTrace = this.getStackTrace();
                    String rhsStackTrace;
                    rhsStackTrace = that.getStackTrace();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "stackTrace", lhsStackTrace), LocatorUtils.property(thatLocator, "stackTrace", rhsStackTrace), lhsStackTrace, rhsStackTrace)) {
                        return false;
                    }
                }
                {
                    String lhsName;
                    lhsName = this.getName();
                    String rhsName;
                    rhsName = that.getName();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                        return false;
                    }
                }
                return true;
            }

            public boolean equals(Object object) {
                final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
                return equals(null, null, object, strategy);
            }

            public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
                int currentHashCode = 1;
                {
                    String theDescription;
                    theDescription = this.getDescription();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
                }
                {
                    int theDuration;
                    theDuration = this.getDuration();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "duration", theDuration), currentHashCode, theDuration);
                }
                {
                    String theActualValue;
                    theActualValue = this.getActualValue();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "actualValue", theActualValue), currentHashCode, theActualValue);
                }
                {
                    String theExpectedValue;
                    theExpectedValue = this.getExpectedValue();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "expectedValue", theExpectedValue), currentHashCode, theExpectedValue);
                }
                {
                    String theStackTrace;
                    theStackTrace = this.getStackTrace();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stackTrace", theStackTrace), currentHashCode, theStackTrace);
                }
                {
                    String theName;
                    theName = this.getName();
                    currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
                }
                return currentHashCode;
            }

            public int hashCode() {
                final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
                return this.hashCode(null, strategy);
            }

            public String toString() {
                final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
                final StringBuilder buffer = new StringBuilder();
                append(null, buffer, strategy);
                return buffer.toString();
            }

            public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                strategy.appendStart(locator, this, buffer);
                appendFields(locator, buffer, strategy);
                strategy.appendEnd(locator, this, buffer);
                return buffer;
            }

            public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                {
                    String theDescription;
                    theDescription = this.getDescription();
                    strategy.appendField(locator, this, "description", buffer, theDescription);
                }
                {
                    int theDuration;
                    theDuration = this.getDuration();
                    strategy.appendField(locator, this, "duration", buffer, theDuration);
                }
                {
                    String theActualValue;
                    theActualValue = this.getActualValue();
                    strategy.appendField(locator, this, "actualValue", buffer, theActualValue);
                }
                {
                    String theExpectedValue;
                    theExpectedValue = this.getExpectedValue();
                    strategy.appendField(locator, this, "expectedValue", buffer, theExpectedValue);
                }
                {
                    String theStackTrace;
                    theStackTrace = this.getStackTrace();
                    strategy.appendField(locator, this, "stackTrace", buffer, theStackTrace);
                }
                {
                    String theName;
                    theName = this.getName();
                    strategy.appendField(locator, this, "name", buffer, theName);
                }
                return buffer;
            }

        }

    }

}
