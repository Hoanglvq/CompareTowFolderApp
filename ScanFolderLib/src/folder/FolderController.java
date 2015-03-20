package folder;


import java.io.File;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoanglvq
 */
public class FolderController implements IFolderController{
    
    private final JFileChooser chooser;
    private File[] lstFile;
    private File currentFolder;
    
    public FolderController() {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }
    
    
    @Override
    public boolean openFolder(){
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            currentFolder = chooser.getSelectedFile();
            lstFile = currentFolder.listFiles();
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public File[] getListFile(){
        return lstFile;
    }
    
    @Override
    public boolean isTheSameAsFolder(String pathFolder){
        return currentFolder.getAbsolutePath().equals(pathFolder);
    }
    
    @Override
    public String getFolderPath(){
        return currentFolder.getAbsolutePath();
    }
    
    
}
