/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge_source;

import black_board.BlackBoard;


/**
 *
 * @author hoanglvq
 */
public abstract class KnowledgeSource {
    
    BlackBoard bb;
    String nameKS;
    
    public void KnowledgeSource(BlackBoard newBB){
        this.bb = newBB;        
    }
    
    public String getNameKS(){
        return this.nameKS;
    }
    
}
