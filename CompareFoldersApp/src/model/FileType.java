/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author hoanglvq
 */
public class FileType {
    
    String name;
    Date modified;
//    String author;
//    String dateCreate;
    String path;
    String status;

    public FileType(String name, Date modified, String path, String status) {
        this.name = name;
        this.modified = modified;
//        this.author = author;
        //this.dateCreate = dateCreate;
        this.path = path;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
