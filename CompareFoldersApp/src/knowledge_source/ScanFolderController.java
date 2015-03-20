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

/**
 *
 * @author hoanglvq
 */
public class ScanFolderController extends KnowledgeSource implements event.EventService{

    String path;
    List lstCmpListener;
    IFolderController fComponent;

    public ScanFolderController() {
        lstCmpListener = new ArrayList();
        this.nameKS = "FolderController";
        fComponent = Initiator.init();
    }
    
    public boolean openFolder(){
        return fComponent.openFolder();
    }
    
    public File[] getListFile(){
        return fComponent.getListFile();
    }
    
    public String getFolderPath(){
        return fComponent.getFolderPath();
    }

    @Override
    public void addComponentListener(knowledge_source.KnowledgeSource cmpListener) {
        lstCmpListener.add(cmpListener);
    }

    @Override
    public void removeComponentListerner(knowledge_source.KnowledgeSource cmpListener) {
        lstCmpListener.remove(cmpListener);
    }

    @Override
    public void eventTrigger(knowledge_source.KnowledgeSource cmpListener) {
        
    }

}
