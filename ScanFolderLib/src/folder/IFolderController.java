package folder;

import java.io.File;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoanglvq
 */
public interface IFolderController {
    public boolean openFolder();
    public List<File> getListFile();
    public boolean isTheSameAsFolder(String pathFolder);
    public String getFolderPath();
}
