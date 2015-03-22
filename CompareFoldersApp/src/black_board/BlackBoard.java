/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_board;

import java.util.ArrayList;
import knowledge_source.IKnowledgeSource;
import knowledge_source.ScanFolderController;
import java.util.HashMap;
import java.util.List;
import model.FileType;


/**
 *
 * @author hoanglvq
 */
public class BlackBoard {
    
    private final HashMap<String, IKnowledgeSource> lstKS; 
    private String pathFolder1="";
    private String pathFolder2="";
    private List<FileType> lstFolder1;
    private List<FileType> lstFolder2;
    

    public BlackBoard() {
        this.lstKS = new HashMap<>();
        this.lstFolder1 = new ArrayList<>();
        this.lstFolder2 = new ArrayList<>();
    }
    
    
    // Register Knowledge Source to BlackBoard
    public void registerKS(IKnowledgeSource kSObject){
       kSObject.KnowledgeSource(this);
       this.lstKS.put(kSObject.getNameKS(), kSObject);
    }
    
    // choose folder 1
    public boolean chooseFolder1(){
        ScanFolderController fdC = (ScanFolderController) lstKS.get("FolderController");
        return fdC.openFolder(1);
    }
    
     // choose folder 2
    public boolean chooseFolder2(){
        ScanFolderController fdC = (ScanFolderController) lstKS.get("FolderController");
        return fdC.openFolder(2);
    }
    
    // get & set function variable
    public String getFolder1Path(){
        return pathFolder1;
    }
    
    public void setPathFolder1(String pathFolder){
        this.pathFolder1 = pathFolder;
    }
    
    public String getFolder2Path(){
        return pathFolder2;
    }
    
    public void setPathFolder2(String pathFolder){
        this.pathFolder2 = pathFolder;
    }
    
    public void setListFilesInFolder1(List<FileType> lstFiles){
        this.lstFolder1 = lstFiles;
    }
    
    public void setListFilesInFolder2(List<FileType> lstFiles){
        this.lstFolder2 = lstFiles;
    }
    
    // other functions
    public void emptyListFilesInFolder(int num){
        if(num == 1){
            this.lstFolder1 = new ArrayList<>();
        }else{
            this.lstFolder2 = new ArrayList<>();
        }
    }
    
    // demo
    public void display(){
        System.out.println("Folder 1: ");
        for (FileType file : lstFolder1) {
            System.out.println("- "+file.getName());
        }
        System.out.println("Folder 2: ");
        for (FileType file1 : lstFolder2) {
            System.out.println("- "+file1.getName());
        }
    }
    

}
