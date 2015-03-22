/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author hoanglvq
 */
public abstract class ICriterion {
    
    String nameCriterion;
  
    public String getNameCriterion(){
        return this.nameCriterion;
    }
    
    public abstract boolean checkCriterion();
    
}
