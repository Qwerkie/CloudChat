/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iGradeService;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author ctd5100
 */
@WebService
public interface IGradeService {
    @WebMethod
    public String toHundredScale(String grade, String unit);
    @WebMethod
    public String toSevenScale(String grade, String unit);
    @WebMethod
    public String toLetterScale(String grade, String unit);
}
