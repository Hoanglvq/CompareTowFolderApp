package folder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
public class FolderController implements IFolderController {

    private final JFileChooser chooser;
    private List<File> lstFiles;
    private File currentFolder;

    public FolderController() {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
    }

    @Override
    public boolean openFolder() {
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFolder = chooser.getSelectedFile();
            this.lstFiles = new ArrayList<>();
            this.getAllFilesInFolderToList(currentFolder);
            return true;
        } else {
            return false;
        }
    }

    private void getAllFilesInFolderToList(File folder) {        
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                this.getAllFilesInFolderToList(file);
            } else {
                this.lstFiles.add(file);
            }
        }
    }

    @Override
    public List<File> getListFile() {
        return lstFiles;
    }

    @Override
    public boolean isTheSameAsFolder(String pathFolder) {
        return currentFolder.getAbsolutePath().equals(pathFolder);
    }

    @Override
    public String getFolderPath() {
        return currentFolder.getAbsolutePath();
    }

}
