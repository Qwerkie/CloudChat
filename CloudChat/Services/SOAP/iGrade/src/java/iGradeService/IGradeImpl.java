/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iGradeService;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 *
 * @author ctd5100
 */
@WebService(endpointInterface = "iGradeService.IGradeService")
@HandlerChain(file = "serviceHandler.xml")
public class IGradeImpl implements IGradeService {

    @Override
    public String toHundredScale(String grade, String unit) {
        String retVal;
        if (unit.equalsIgnoreCase("7-scale") || unit.equalsIgnoreCase("seven-scale")) {
            switch (grade) {
                case "7":
                    retVal = "93-100";
                    break;
                case "6.5":
                    retVal = "89-92";
                    break;
                case "6":
                    retVal = "85-88";
                    break;
                case "5.8":
                    retVal = "88-84";
                    break;
                case "5.5":
                    retVal = "75-79";
                    break;
                case "5":
                    retVal = "70-74";
                    break;
                case "4.8":
                    retVal = "65-69";
                    break;
                case "4.5":
                    retVal = "60-64";
                    break;
                case "4":
                    retVal = "0-59";
                    break;
                default:
                    retVal = "ERROR";
                    break;
            }
        } else if (unit.equalsIgnoreCase("letter-scale")) {
            switch (grade) {
                case "A":
                    retVal = "93-100";
                    break;
                case "A-":
                    retVal = "89-92";
                    break;
                case "B+":
                    retVal = "85-88";
                    break;
                case "B":
                    retVal = "88-84";
                    break;
                case "B-":
                    retVal = "75-79";
                    break;
                case "C+":
                    retVal = "70-74";
                    break;
                case "C":
                    retVal = "65-69";
                    break;
                case "D":
                    retVal = "60-64";
                    break;
                case "F":
                    retVal = "0-59";
                    break;
                case "a":
                    retVal = "93-100";
                    break;
                case "a-":
                    retVal = "89-92";
                    break;
                case "b+":
                    retVal = "85-88";
                    break;
                case "b":
                    retVal = "88-84";
                    break;
                case "b-":
                    retVal = "75-79";
                    break;
                case "c+":
                    retVal = "70-74";
                    break;
                case "c":
                    retVal = "65-69";
                    break;
                case "d":
                    retVal = "60-64";
                    break;
                case "f":
                    retVal = "0-59";
                    break;
                default:
                    retVal = "ERROR";
                    break;
            }
        }
        else{
            retVal = grade;
        }
        return retVal;
    }

    @Override
    public String toSevenScale(String grade, String unit) {
        String retVal;
        if (unit.equalsIgnoreCase("letter-scale")) {
            switch (grade) {
                case "A":
                    retVal = "7";
                    break;
                case "A-":
                    retVal = "6.5";
                    break;
                case "B+":
                    retVal = "6";
                    break;
                case "B":
                    retVal = "5.8";
                    break;
                case "B-":
                    retVal = "5.5";
                    break;
                case "C+":
                    retVal = "5";
                    break;
                case "C":
                    retVal = "4.8";
                    break;
                case "D":
                    retVal = "4.5";
                    break;
                case "F":
                    retVal = "4";
                    break;
                case "a":
                    retVal = "7";
                    break;
                case "a-":
                    retVal = "6.5";
                    break;
                case "b+":
                    retVal = "6";
                    break;
                case "b":
                    retVal = "5.8";
                    break;
                case "b-":
                    retVal = "5.5";
                    break;
                case "c+":
                    retVal = "5";
                    break;
                case "c":
                    retVal = "4.8";
                    break;
                case "d":
                    retVal = "4.5";
                    break;
                case "f":
                    retVal = "4";
                    break;
                default:
                    retVal = "ERROR";
                    break;
            }
        } else if (unit.equalsIgnoreCase("hundred-scale") || unit.equalsIgnoreCase("100=scale")) {
            int gradeNum = Integer.parseInt(grade);
            if(gradeNum > 92 && gradeNum < 101){
                retVal = "7";
            }
            else if(gradeNum > 88 && gradeNum < 93){
                retVal = "6.5";
            }
            else if(gradeNum > 84 && gradeNum < 89){
                retVal = "6";
            }
            else if(gradeNum > 79 && gradeNum < 85){
                retVal = "5.8";
            }
            else if(gradeNum > 74  && gradeNum < 80){
                retVal = "5.5";
            }
            else if(gradeNum > 69 && gradeNum < 75){
                retVal = "5";
            }
            else if(gradeNum > 64 && gradeNum < 70){
                retVal = "4.8";
            }
            else if(gradeNum > 59 && gradeNum < 65){
                retVal = "4.5";
            }
            else if(gradeNum > -1 && gradeNum < 60){
                retVal = "4";
            }
            else{
                retVal = "ERROR";
            }
        }
        else{
            retVal = grade;
        }
        return retVal;
    }

    @Override
    public String toLetterScale(String grade, String unit) {
        String retVal;
        if (unit.equalsIgnoreCase("seven-scale") || unit.equalsIgnoreCase("7-scale")) {
            switch (grade) {
                case "7":
                    retVal = "A";
                    break;
                case "6.5":
                    retVal = "A-";
                    break;
                case "6":
                    retVal = "B+";
                    break;
                case "5.8":
                    retVal = "B";
                    break;
                case "5.5":
                    retVal = "B-";
                    break;
                case "5":
                    retVal = "C+";
                    break;
                case "4.8":
                    retVal = "C";
                    break;
                case "4.5":
                    retVal = "D";
                    break;
                case "4":
                    retVal = "F";
                    break;
                default:
                    retVal = "ERROR";
                    break;
            }
        } else if (unit.equalsIgnoreCase("hundred-scale") || unit.equalsIgnoreCase("100=scale")) {
            int gradeNum = Integer.parseInt(grade);
            if(gradeNum > 92 && gradeNum < 101){
                retVal = "A";
            }
            else if(gradeNum > 88 && gradeNum < 93){
                retVal = "A-";
            }
            else if(gradeNum > 84 && gradeNum < 89){
                retVal = "B+";
            }
            else if(gradeNum > 79 && gradeNum < 85){
                retVal = "B";
            }
            else if(gradeNum > 74  && gradeNum < 80){
                retVal = "B-";
            }
            else if(gradeNum > 69 && gradeNum < 75){
                retVal = "C+";
            }
            else if(gradeNum > 64 && gradeNum < 70){
                retVal = "C";
            }
            else if(gradeNum > 59 && gradeNum < 65){
                retVal = "D";
            }
            else if(gradeNum > -1 && gradeNum < 60){
                retVal = "F";
            }
            else{
                retVal = "ERROR";
            }
        }
        else{
            retVal = grade;
        }
        return retVal;
    }

}
