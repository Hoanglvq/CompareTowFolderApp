/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import service.FileProp;

/**
 *
 * @author Kaka Hoang Huy
 */
interface IComparator {
    HashMap<String, List<File[]>> startCompare(String dirMasterA, String dirMasterB, List<File> fileList1, List<File> fileList2);
    void stopCompare();
}
