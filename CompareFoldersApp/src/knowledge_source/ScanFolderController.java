/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge_source;

import java.util.ArrayList;
import java.util.List;
import folder.*;
import java.io.File;
import java.util.Date;
import model.FileType;

/**
 *
 * @author hoanglvq
 */
public class ScanFolderController extends IKnowledgeSource implements event.EventService {

    String path;
    List lstCmpListener;
    IFolderController fComponent;

    public ScanFolderController() {
        lstCmpListener = new ArrayList();
        this.nameKS = "FolderController";
        fComponent = Initiator.init();
    }

    public boolean openFolder(int num) {
        boolean rst = fComponent.openFolder();
        if (num == 1) {
            this.bb.setPathFolder1(this.getFolderPath());
            this.bb.setListFilesInFolder1(this.convertToListFileType(this.getListFile()));
        }else{
            this.bb.setPathFolder2(this.getFolderPath());
            this.bb.setListFilesInFolder2(this.convertToListFileType(this.getListFile()));
        }
        return rst;
    }
    
    private List<FileType> convertToListFileType(List<File> lstF){
        List<FileType> lstFileType = new ArrayList<>();
        lstF.stream().map((f) -> new FileType(f.getName(),new Date(f.lastModified()),f.getAbsolutePath(),"")).forEach((ft) -> {
            lstFileType.add(ft);
        });
        return lstFileType;
    }
    
   
    public List<File> getListFile() {
        return fComponent.getListFile();
    }

    public String getFolderPath() {
        return fComponent.getFolderPath();
    }

    @Override
    public void addComponentListener(knowledge_source.IKnowledgeSource cmpListener) {
        lstCmpListener.add(cmpListener);
    }

    @Override
    public void removeComponentListerner(knowledge_source.IKnowledgeSource cmpListener) {
        lstCmpListener.remove(cmpListener);
    }

    @Override
    public void eventTrigger(knowledge_source.IKnowledgeSource cmpListener) {

    }

}
