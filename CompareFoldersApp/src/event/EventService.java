/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import knowledge_source.KnowledgeSource;

/**
 *
 * @author hoanglvq
 */
public interface EventService {
    
    public void addComponentListener(KnowledgeSource cmpListener);
    public void removeComponentListerner(KnowledgeSource cmpListener);
    public void eventTrigger(KnowledgeSource cmpListener);
    
}
