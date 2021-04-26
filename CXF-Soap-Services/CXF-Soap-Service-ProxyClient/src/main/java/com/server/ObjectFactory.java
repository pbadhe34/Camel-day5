
package com.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReportIncident_QNAME = new QName("http://server.com/", "reportIncident");
    private final static QName _ReportIncidentResponse_QNAME = new QName("http://server.com/", "reportIncidentResponse");
    private final static QName _StatusIncident_QNAME = new QName("http://server.com/", "statusIncident");
    private final static QName _StatusIncidentResponse_QNAME = new QName("http://server.com/", "statusIncidentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReportIncident }
     * 
     */
    public ReportIncident createReportIncident() {
        return new ReportIncident();
    }

    /**
     * Create an instance of {@link ReportIncidentResponse }
     * 
     */
    public ReportIncidentResponse createReportIncidentResponse() {
        return new ReportIncidentResponse();
    }

    /**
     * Create an instance of {@link StatusIncident }
     * 
     */
    public StatusIncident createStatusIncident() {
        return new StatusIncident();
    }

    /**
     * Create an instance of {@link StatusIncidentResponse }
     * 
     */
    public StatusIncidentResponse createStatusIncidentResponse() {
        return new StatusIncidentResponse();
    }

    /**
     * Create an instance of {@link InputReportIncident }
     * 
     */
    public InputReportIncident createInputReportIncident() {
        return new InputReportIncident();
    }

    /**
     * Create an instance of {@link OutputReportIncident }
     * 
     */
    public OutputReportIncident createOutputReportIncident() {
        return new OutputReportIncident();
    }

    /**
     * Create an instance of {@link InputStatusIncident }
     * 
     */
    public InputStatusIncident createInputStatusIncident() {
        return new InputStatusIncident();
    }

    /**
     * Create an instance of {@link OutputStatusIncident }
     * 
     */
    public OutputStatusIncident createOutputStatusIncident() {
        return new OutputStatusIncident();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportIncident }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.com/", name = "reportIncident")
    public JAXBElement<ReportIncident> createReportIncident(ReportIncident value) {
        return new JAXBElement<ReportIncident>(_ReportIncident_QNAME, ReportIncident.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReportIncidentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.com/", name = "reportIncidentResponse")
    public JAXBElement<ReportIncidentResponse> createReportIncidentResponse(ReportIncidentResponse value) {
        return new JAXBElement<ReportIncidentResponse>(_ReportIncidentResponse_QNAME, ReportIncidentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusIncident }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.com/", name = "statusIncident")
    public JAXBElement<StatusIncident> createStatusIncident(StatusIncident value) {
        return new JAXBElement<StatusIncident>(_StatusIncident_QNAME, StatusIncident.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StatusIncidentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.com/", name = "statusIncidentResponse")
    public JAXBElement<StatusIncidentResponse> createStatusIncidentResponse(StatusIncidentResponse value) {
        return new JAXBElement<StatusIncidentResponse>(_StatusIncidentResponse_QNAME, StatusIncidentResponse.class, null, value);
    }

}
