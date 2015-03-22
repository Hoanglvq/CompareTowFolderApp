/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import codec.IComparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import service.FileProp;

/**
 *
 * @author Kaka Hoang Huy
 */
public class Comparator implements IComparator {

    //This can be any folder locations which you want to compare
    static File dir1 = new File("E:\\Compare\\s1");
    static File dir2 = new File("E:\\Compare\\s2");

    public static void main(String... args) {
        Comparator compare = new Comparator();
        List<File> ar1 = Arrays.asList(dir1.listFiles());
        List<File> ar2 = Arrays.asList(dir2.listFiles());
        FileProp[][] result = compare.compareListFiles("E:\\Compare\\s2", "E:\\Compare\\s1", ar1, ar2);
        System.out.println("rs: " + result.length + "------------------------------------------------");
        for (int i = 0; i < result.length; i++) {
            FileProp[] item = result[i];
            if (item[0] != null) {
                System.out.println("Name: " + item[0].getPath() + " ---> Status: " + item[0].getStatus());
            }
            if (item[1] != null) {
                System.out.println("Name: " + item[1].getPath() + " ---> Status: " + item[1].getStatus());
            }
        }
    }

    public Path getRelativePath(String children, String master) {
        Path pathAbsolute = Paths.get(children);
        Path pathBase = Paths.get(master);
        try {
            Path pathRelative = pathBase.relativize(pathAbsolute);
//        System.out.println(pathRelative);
            return pathRelative;
        } catch (Exception e) {
//        System.out.println("not same root");
        }
        return null;
    }

//    private boolean checkCriterion(String dirMasterA, String dirMasterB, List<File> fileList1, List<File> fileList2, int criterionNumber) {
//        long sizeList1 = fileList1.size();
//        long sizeList2 = fileList2.size();
//        long sizeCompare = 0;
//        long sameFileNum = 0;
//        double percent = 0.0;
//        HashMap<String, File> map1;
//        if (sizeList1 < sizeList2) {
//            map1 = new HashMap<String, File>();
//            for (int i = 0; i < fileList1.size(); i++) {
//                String relativePath = getRelativePath(dirMasterA, fileList1.get(i).getAbsolutePath()).toString();
//                if (relativePath != null && relativePath != "") {
//                    map1.put(relativePath, fileList1.get(i));
//                }
////                System.out.println("path: " + fileList1.get(i).getAbsoluteFile().length());
//            }
//            sizeCompare = sizeList1;
//            sameFileNum = compareFiles(dirMasterB, fileList2, map1);
//        } else {
//            map1 = new HashMap<String, File>();
//            for (int i = 0; i < fileList2.size(); i++) {
//                String relativePath = getRelativePath(dirMasterA, fileList2.get(i).getAbsolutePath()).toString();
//                if (relativePath != null && relativePath != "") {
//                    map1.put(relativePath, fileList2.get(i));
//                }
//            }
//            sizeCompare = sizeList2;
//            sameFileNum = compareFiles(dirMasterA, fileList1, map1);
//        }
//        if (sizeCompare > 0) {
//            percent = sameFileNum / sizeCompare;
//            System.out.println("Percent of same name files: " + percent);
//            if (percent >= criterionNumber) {
//                return true;
//            }
//        }
//        return false;
//    }
    private FileProp[][] compareListFiles(String dirMasterA, String dirMasterB, List<File> fileList1, List<File> fileList2) {
        int sizeList1 = fileList1.size();
        int sizeList2 = fileList2.size();
        FileProp[][] result = new FileProp[(sizeList1 + sizeList2)][2];
        HashMap<String, File> map1;
        if (sizeList1 < sizeList2) {
            map1 = new HashMap<String, File>();
            for (int i = 0; i < fileList1.size(); i++) {
                String relativePath = getRelativePath(dirMasterA, fileList1.get(i).getAbsolutePath()).toString();
                if (relativePath != null && relativePath != "") {
                    map1.put(relativePath, fileList1.get(i));
                }
            }
            result = compareFiles(dirMasterB, fileList2, map1);

        } else {
            map1 = new HashMap<String, File>();
            for (int i = 0; i < fileList2.size(); i++) {
                String relativePath = getRelativePath(dirMasterA, fileList2.get(i).getAbsolutePath()).toString();
                if (relativePath != null && relativePath != "") {
                    map1.put(relativePath, fileList2.get(i));
                }
            }
            result = compareFiles(dirMasterA, fileList1, map1);
        }
        return result;
    }

    private FileProp[][] compareFiles(String dirMaster, List<File> listFile, HashMap<String, File> map) {
        FileProp[][] result;
        result = new FileProp[listFile.size() + map.size()][2];
        System.out.println("Size1: " + listFile.size() + " Size2: " + map.size());
//        long samePathSum = 0;
//        boolean isMapNewer = true;
//        boolean lock = false;
//        boolean isComparalbe = true;
        for (int i = 0; i < listFile.size(); i++) {
            File listItem = listFile.get(i);
            String fName = listItem.getName();
            String fPath = listItem.getAbsolutePath();
            System.out.println("fName: " + fName);
            System.out.println("dir: " + dirMaster);
            String relativePath = getRelativePath(fPath, dirMaster).toString();
            File fComp = map.get(relativePath);
            map.remove(relativePath);
            if (fComp != null) {
                long mapItemMod = fComp.getAbsoluteFile().lastModified();
                long listItemMod = listFile.get(i).lastModified();
                String statusListItem = "";
                String statusMapItem = "";
                if (mapItemMod > listItemMod) {
                    statusListItem = "older";
                    statusMapItem = "newer";
                } else if (mapItemMod < listItemMod) {
                    statusListItem = "newer";
                    statusMapItem = "older";
                } else {
                    statusListItem = "identify";
                    statusMapItem = "identify";
                }
                FileProp file1 = new FileProp();
                file1.setName(fName);
                file1.setLastModified(listItem.lastModified());
//                file1.setDateCreate(0);
//                file1.setAuthor();
                file1.setPath(relativePath);
                file1.setStatus(statusListItem);
                result[i][0] = file1;
                FileProp file2 = new FileProp();
                file2.setName(fComp.getName());
                file2.setLastModified(fComp.lastModified());
//                file2.setDateCreate(dirMaster);
//                file2.setAuthor();
                file2.setPath(relativePath);
                file2.setStatus(statusMapItem);
                result[i][1] = file2;

                //                samePathSum++;
                //                long mapItem = fComp.getAbsoluteFile().lastModified();
                //                long listItem = listFile.get(i).lastModified();
                //                if(!lock)   isMapNewer = (mapItem>listItem);
                //                if((mapItem>listItem) != isMapNewer)    isComparalbe = false;
                {

                }
            } else {
                FileProp file1 = new FileProp();
                file1.setName(fName);
                file1.setLastModified(listItem.lastModified());
//                file1.setDateCreate(0);
//                file1.setAuthor();
                file1.setPath(relativePath);
                file1.setStatus("only");
                result[i][0] = file1;

            }
        }
        int remain = 0;
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String n = it.next();
            remain++;
            File fileFrmMap = map.get(n);
            map.remove(n);
            {
                FileProp file2 = new FileProp();
                file2.setName(fileFrmMap.getName());
                file2.setLastModified(fileFrmMap.lastModified());
//                file2.setDateCreate(dirMaster);
//                file2.setAuthor();
                file2.setPath(n);
                file2.setStatus("only");
                result[remain + listFile.size() - 1][1] = file2;
            }
        }
//        return samePathSum;
        return result;
    }

    @Override
    public FileProp[][] startCompare(File[] listFile1, File[] listFile2) {
        System.err.println("Not sp yet");
        return null;
    }

    @Override
    public void stopCompare() {
        System.err.println("Not sp yet");
    }
}
