
package client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IGradeService", targetNamespace = "http://iGradeService/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IGradeService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toHundredScale", targetNamespace = "http://iGradeService/", className = "client.ToHundredScale")
    @ResponseWrapper(localName = "toHundredScaleResponse", targetNamespace = "http://iGradeService/", className = "client.ToHundredScaleResponse")
    @Action(input = "http://iGradeService/IGradeService/toHundredScaleRequest", output = "http://iGradeService/IGradeService/toHundredScaleResponse")
    public String toHundredScale(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toLetterScale", targetNamespace = "http://iGradeService/", className = "client.ToLetterScale")
    @ResponseWrapper(localName = "toLetterScaleResponse", targetNamespace = "http://iGradeService/", className = "client.ToLetterScaleResponse")
    @Action(input = "http://iGradeService/IGradeService/toLetterScaleRequest", output = "http://iGradeService/IGradeService/toLetterScaleResponse")
    public String toLetterScale(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "toSevenScale", targetNamespace = "http://iGradeService/", className = "client.ToSevenScale")
    @ResponseWrapper(localName = "toSevenScaleResponse", targetNamespace = "http://iGradeService/", className = "client.ToSevenScaleResponse")
    @Action(input = "http://iGradeService/IGradeService/toSevenScaleRequest", output = "http://iGradeService/IGradeService/toSevenScaleResponse")
    public String toSevenScale(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}