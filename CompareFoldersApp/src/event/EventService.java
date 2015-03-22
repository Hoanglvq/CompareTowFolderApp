/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import knowledge_source.IKnowledgeSource;

/**
 *
 * @author hoanglvq
 */
public interface EventService {
    
    public void addComponentListener(IKnowledgeSource cmpListener);
    public void removeComponentListerner(IKnowledgeSource cmpListener);
    public void eventTrigger(IKnowledgeSource cmpListener);
    
}
