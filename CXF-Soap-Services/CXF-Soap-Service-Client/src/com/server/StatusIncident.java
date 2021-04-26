
package com.server;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusIncident complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="statusIncident"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="input" type="{http://server.com/}inputStatusIncident" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusIncident", propOrder = {
    "input"
})
public class StatusIncident {

    @XmlElement(namespace = "http://server.com/")
    protected InputStatusIncident input;

    /**
     * Gets the value of the input property.
     * 
     * @return
     *     possible object is
     *     {@link InputStatusIncident }
     *     
     */
    public InputStatusIncident getInput() {
        return input;
    }

    /**
     * Sets the value of the input property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputStatusIncident }
     *     
     */
    public void setInput(InputStatusIncident value) {
        this.input = value;
    }

}
