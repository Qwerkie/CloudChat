
package clientSOAP;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the clientSOAP package. 
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

    private final static QName _LogOutUserResponse_QNAME = new QName("http://managementService/", "logOutUserResponse");
    private final static QName _GetAllLoggedUsersResponse_QNAME = new QName("http://managementService/", "getAllLoggedUsersResponse");
    private final static QName _LogOutUser_QNAME = new QName("http://managementService/", "logOutUser");
    private final static QName _RegisterUser_QNAME = new QName("http://managementService/", "registerUser");
    private final static QName _LogInUser_QNAME = new QName("http://managementService/", "logInUser");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://managementService/", "registerUserResponse");
    private final static QName _GetAllLoggedUsers_QNAME = new QName("http://managementService/", "getAllLoggedUsers");
    private final static QName _LogInUserResponse_QNAME = new QName("http://managementService/", "logInUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: clientSOAP
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LogOutUserResponse }
     * 
     */
    public LogOutUserResponse createLogOutUserResponse() {
        return new LogOutUserResponse();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link LogOutUser }
     * 
     */
    public LogOutUser createLogOutUser() {
        return new LogOutUser();
    }

    /**
     * Create an instance of {@link GetAllLoggedUsersResponse }
     * 
     */
    public GetAllLoggedUsersResponse createGetAllLoggedUsersResponse() {
        return new GetAllLoggedUsersResponse();
    }

    /**
     * Create an instance of {@link GetAllLoggedUsers }
     * 
     */
    public GetAllLoggedUsers createGetAllLoggedUsers() {
        return new GetAllLoggedUsers();
    }

    /**
     * Create an instance of {@link LogInUserResponse }
     * 
     */
    public LogInUserResponse createLogInUserResponse() {
        return new LogInUserResponse();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link LogInUser }
     * 
     */
    public LogInUser createLogInUser() {
        return new LogInUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOutUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "logOutUserResponse")
    public JAXBElement<LogOutUserResponse> createLogOutUserResponse(LogOutUserResponse value) {
        return new JAXBElement<LogOutUserResponse>(_LogOutUserResponse_QNAME, LogOutUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLoggedUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "getAllLoggedUsersResponse")
    public JAXBElement<GetAllLoggedUsersResponse> createGetAllLoggedUsersResponse(GetAllLoggedUsersResponse value) {
        return new JAXBElement<GetAllLoggedUsersResponse>(_GetAllLoggedUsersResponse_QNAME, GetAllLoggedUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOutUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "logOutUser")
    public JAXBElement<LogOutUser> createLogOutUser(LogOutUser value) {
        return new JAXBElement<LogOutUser>(_LogOutUser_QNAME, LogOutUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogInUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "logInUser")
    public JAXBElement<LogInUser> createLogInUser(LogInUser value) {
        return new JAXBElement<LogInUser>(_LogInUser_QNAME, LogInUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLoggedUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "getAllLoggedUsers")
    public JAXBElement<GetAllLoggedUsers> createGetAllLoggedUsers(GetAllLoggedUsers value) {
        return new JAXBElement<GetAllLoggedUsers>(_GetAllLoggedUsers_QNAME, GetAllLoggedUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogInUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://managementService/", name = "logInUserResponse")
    public JAXBElement<LogInUserResponse> createLogInUserResponse(LogInUserResponse value) {
        return new JAXBElement<LogInUserResponse>(_LogInUserResponse_QNAME, LogInUserResponse.class, null, value);
    }

}
