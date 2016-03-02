
package clientSOAP;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "UserManagerServiceImplService", targetNamespace = "http://managementService/", wsdlLocation = "http://127.0.0.1:8282/CloudChatUserManager?wsdl")
public class UserManagerServiceImplService
    extends Service
{

    private final static URL USERMANAGERSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException USERMANAGERSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName USERMANAGERSERVICEIMPLSERVICE_QNAME = new QName("http://managementService/", "UserManagerServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8282/CloudChatUserManager?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        USERMANAGERSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        USERMANAGERSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public UserManagerServiceImplService() {
        super(__getWsdlLocation(), USERMANAGERSERVICEIMPLSERVICE_QNAME);
    }

    public UserManagerServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), USERMANAGERSERVICEIMPLSERVICE_QNAME, features);
    }

    public UserManagerServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, USERMANAGERSERVICEIMPLSERVICE_QNAME);
    }

    public UserManagerServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, USERMANAGERSERVICEIMPLSERVICE_QNAME, features);
    }

    public UserManagerServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserManagerServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns UserManagerService
     */
    @WebEndpoint(name = "UserManagerServiceImplPort")
    public UserManagerService getUserManagerServiceImplPort() {
        return super.getPort(new QName("http://managementService/", "UserManagerServiceImplPort"), UserManagerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserManagerService
     */
    @WebEndpoint(name = "UserManagerServiceImplPort")
    public UserManagerService getUserManagerServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://managementService/", "UserManagerServiceImplPort"), UserManagerService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (USERMANAGERSERVICEIMPLSERVICE_EXCEPTION!= null) {
            throw USERMANAGERSERVICEIMPLSERVICE_EXCEPTION;
        }
        return USERMANAGERSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}