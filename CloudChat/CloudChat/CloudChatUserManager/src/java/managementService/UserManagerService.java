/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managementService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Charlie
 */
@WebService
public interface UserManagerService {
    @WebMethod
    public boolean logInUser(String userID, String password);
    @WebMethod
    public boolean logOutUser(String userID);
    @WebMethod
    public boolean registerUser(String userID, String password);
    @WebMethod
    public String getAllLoggedUsers();
}
