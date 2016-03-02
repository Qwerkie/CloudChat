/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iGradeService;

/**
 *
 * @author ctd5100
 */
import javax.xml.ws.Endpoint;
public class IGradePublisher {
    public static void main(String[] args){
        final String url = "http://localhost:8282/iGrade";
        System.out.println("Publishing IGradeService at endpoint " + url);
        Endpoint.publish(url, new IGradeImpl());
    }
}
