/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge_source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.*;

/**
 *
 * @author hoanglvq
 */
public class CriterionController extends IKnowledgeSource implements event.EventService{
    
    private final HashMap<String, ICriterion> lstCriterions; 
    List lstCmpListener;
    
    public CriterionController() {
        lstCmpListener = new ArrayList();
        lstCriterions = new HashMap<>();
        this.nameKS = "CriterionController";
    }
    
    public void registerCriterion(ICriterion crt){
//        this.lstCriterions.put("", crt)
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
