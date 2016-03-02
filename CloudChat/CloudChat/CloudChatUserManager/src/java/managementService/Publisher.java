/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementService;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Charlie
 */
public class Publisher {
    public static void main(String[] args){
        final String url = "http://127.0.0.1:8282/CloudChatUserManager";
        System.out.println("Publishing IGradeService at endpoint " + url);
        Endpoint.publish(url, new UserManagerServiceImpl());
    }
}
