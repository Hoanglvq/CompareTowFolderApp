/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black_board;

import knowledge_source.KnowledgeSource;
import knowledge_source.ScanFolderController;
import java.util.HashMap;
import java.io.File;


/**
 *
 * @author hoanglvq
 */
public class BlackBoard {
    
    private final HashMap<String, KnowledgeSource> lstKS; 
    private String pathFolder1="";
    private String pathFolder2="";
    private File[] lstFolder1;
    private File[] lstFolder2;
    

    public BlackBoard() {
       
        this.lstKS = new HashMap<>();
        this.lstFolder1 = new File[0];
        this.lstFolder2 = new File[0];
        
    }
    
    
    // Register Knowledge Source to BlackBoard
    public void registerKS(KnowledgeSource kSObject){
       this.lstKS.put(kSObject.getNameKS(), kSObject);
    }
    
    // choose folder 1
    public boolean chooseFolder1(){
        ScanFolderController fdC = (ScanFolderController) lstKS.get("FolderController");
        boolean rst = fdC.openFolder();
        lstFolder1 = fdC.getListFile();
        pathFolder1 = fdC.getFolderPath();
        return rst;
    }
    
     // choose folder 2
    public boolean chooseFolder2(){
        ScanFolderController fdC = (ScanFolderController) lstKS.get("FolderController");
        boolean rst = fdC.openFolder();
        lstFolder2 = fdC.getListFile();
        pathFolder2 = fdC.getFolderPath();
        return rst;
    }
    
    public String getFolder1Path(){
        return pathFolder1;
    }
    
    public String getFolder2Path(){
        return pathFolder2;
    }
    
    public void emptyListFilesInFolder(int num){
        if(num == 1){
            this.lstFolder1 = new File[0];
        }else{
            this.lstFolder2 = new File[0];
        }
    }
    
    // demo
    public void display(){
        for (File file : lstFolder1) {
            System.out.println("- "+file.getName());
        }
    }
    

}
