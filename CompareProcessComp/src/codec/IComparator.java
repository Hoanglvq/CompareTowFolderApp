/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codec;

import java.io.File;
import service.FileProp;

/**
 *
 * @author Kaka Hoang Huy
 */
public interface IComparator {
    FileProp[][] startCompare(File[] listFile1, File[] listFile2);
    void stopCompare();
}
