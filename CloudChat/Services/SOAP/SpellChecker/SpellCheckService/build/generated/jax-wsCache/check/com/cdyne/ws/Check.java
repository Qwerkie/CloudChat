
package com.cdyne.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This engine makes spelling suggestions for text.<br><br><b>See it working at</b>: <a href="http://www.cdyne.com/SpellChecker" target="_blank">http://www.cdyne.com/SpellChecker</a>
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "check", targetNamespace = "http://ws.cdyne.com/", wsdlLocation = "http://wsf.cdyne.com/SpellChecker/check.asmx?wsdl")
public class Check
    extends Service
{

    private final static URL CHECK_WSDL_LOCATION;
    private final static WebServiceException CHECK_EXCEPTION;
    private final static QName CHECK_QNAME = new QName("http://ws.cdyne.com/", "check");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://wsf.cdyne.com/SpellChecker/check.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CHECK_WSDL_LOCATION = url;
        CHECK_EXCEPTION = e;
    }

    public Check() {
        super(__getWsdlLocation(), CHECK_QNAME);
    }

    public Check(WebServiceFeature... features) {
        super(__getWsdlLocation(), CHECK_QNAME, features);
    }

    public Check(URL wsdlLocation) {
        super(wsdlLocation, CHECK_QNAME);
    }

    public Check(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CHECK_QNAME, features);
    }

    public Check(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Check(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CheckSoap
     */
    @WebEndpoint(name = "checkSoap")
    public CheckSoap getCheckSoap() {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkSoap"), CheckSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CheckSoap
     */
    @WebEndpoint(name = "checkSoap")
    public CheckSoap getCheckSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkSoap"), CheckSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns CheckSoap
     */
    @WebEndpoint(name = "checkSoap12")
    public CheckSoap getCheckSoap12() {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkSoap12"), CheckSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CheckSoap
     */
    @WebEndpoint(name = "checkSoap12")
    public CheckSoap getCheckSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkSoap12"), CheckSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns CheckHttpGet
     */
    @WebEndpoint(name = "checkHttpGet")
    public CheckHttpGet getCheckHttpGet() {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkHttpGet"), CheckHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CheckHttpGet
     */
    @WebEndpoint(name = "checkHttpGet")
    public CheckHttpGet getCheckHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkHttpGet"), CheckHttpGet.class, features);
    }

    /**
     * 
     * @return
     *     returns CheckHttpPost
     */
    @WebEndpoint(name = "checkHttpPost")
    public CheckHttpPost getCheckHttpPost() {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkHttpPost"), CheckHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CheckHttpPost
     */
    @WebEndpoint(name = "checkHttpPost")
    public CheckHttpPost getCheckHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cdyne.com/", "checkHttpPost"), CheckHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CHECK_EXCEPTION!= null) {
            throw CHECK_EXCEPTION;
        }
        return CHECK_WSDL_LOCATION;
    }

}