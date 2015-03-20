/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge_source;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoanglvq
 */
public class CriterionController extends KnowledgeSource implements event.EventService{
    
    List lstCmpListener;
    
    public CriterionController() {
        lstCmpListener = new ArrayList();
        this.nameKS = "CriterionController";
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
