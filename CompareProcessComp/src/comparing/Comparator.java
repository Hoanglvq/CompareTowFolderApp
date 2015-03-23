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
import java.util.ArrayList;
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
    
    public static final String onlyA = "1";
    public static final String onlyB = "2";
    public static final String newerA = "3";
    public static final String newerB = "4";
    public static final String identify = "5";
    public static final String newest = "6";
    static File dir1 = new File("E:\\Compare\\s1");
    static File dir2 = new File("E:\\Compare\\s2");

    public static void main(String... args) {
        Comparator compare = new Comparator();
        List<File> ar1 = Arrays.asList(dir1.listFiles());
        List<File> ar2 = Arrays.asList(dir2.listFiles());
        HashMap<String, List<File[]>> result = compare.compareListFiles("E:\\Compare\\s2", "E:\\Compare\\s1", ar1, ar2);
        System.out.println("rs: " + result.get("1").size() + "------------------------------------------------");
        for (int i = 0; i < result.get("1").size(); i++) {
//            FileProp[] item = result[i];
//            if (item[0] != null) {
//                System.out.println("Name: " + item[0].getPath() + " ---> Status: " + item[0].getStatus());
//            }
//            if (item[1] != null) {
//                System.out.println("Name: " + item[1].getPath() + " ---> Status: " + item[1].getStatus());
//            }
        }
    }

    public Path getRelativePath(String children, String master) {
        Path pathAbsolute = Paths.get(children);
        Path pathBase = Paths.get(master);
        try {
            Path pathRelative = pathBase.relativize(pathAbsolute);
            return pathRelative;
        } catch (Exception e) {
        }
        return null;
    }

    private HashMap<String, List<File[]>> compareListFiles(String dirMasterA, String dirMasterB, List<File> fileList1, List<File> fileList2) {
        int sizeList1 = fileList1.size();
        int sizeList2 = fileList2.size();
        HashMap<String, List<File[]>> result = new HashMap<String, List<File[]>>();
//        FileProp[][] result = new FileProp[(sizeList1 + sizeList2)][2];
        HashMap<String, File> map1;
        if (sizeList1 <= sizeList2) {
            map1 = new HashMap<String, File>();
            for (int i = 0; i < fileList1.size(); i++) {
                System.out.println("parent: " + dirMasterB);
                System.out.println("child: " + fileList1.get(i).getAbsolutePath());
                String relativePath = getRelativePath(fileList1.get(i).getAbsolutePath(), dirMasterB).toString();
                System.out.println("relative: " + relativePath);
                if (relativePath != null && relativePath != "") {
                    map1.put(relativePath, fileList1.get(i));
                }
            }
            result = compareFiles(dirMasterA, fileList2, map1);

        } else {
            map1 = new HashMap<String, File>();
            for (int i = 0; i < fileList2.size(); i++) {
                String relativePath = getRelativePath(fileList2.get(i).getAbsolutePath(), dirMasterA).toString();
                if (relativePath != null && relativePath != "") {
                    map1.put(relativePath, fileList2.get(i));
                }
            }
            result = compareFiles(dirMasterB, fileList1, map1);
        }
        return result;
    }

    private HashMap<String, List<File[]>> compareFiles(String dirMaster, List<File> listFile, HashMap<String, File> map) {
        HashMap<String, List<File[]>> result = new HashMap<String, List<File[]>>();
        File newestDiff = null;
        long newestTime = 0;
        List<File[]> onlyAList = new ArrayList<File[]>();
        List<File[]> onlyBList = new ArrayList<File[]>();
        List<File[]> newerAList = new ArrayList<File[]>();
        List<File[]> newerBList = new ArrayList<File[]>();
        List<File[]> identifyList = new ArrayList<File[]>();
        List<File[]> newestList = new ArrayList<File[]>();
//        result = new FileProp[listFile.size() + map.size()][2];
        System.out.println("Size1: " + listFile.size() + " Size2: " + map.size());
        for (int i = 0; i < listFile.size(); i++) {
            File listItem = listFile.get(i);
            String fName = listItem.getName();
            String fPath = listItem.getAbsolutePath();
            System.out.println("fPath: " + fPath);
            System.out.println("dir: " + dirMaster);
            String relativePath = getRelativePath(fPath, dirMaster).toString();
            System.out.println("rela: " + relativePath);
            File fComp = map.get(relativePath);
            map.remove(relativePath);
            if (fComp != null) {
                long mapItemMod = fComp.getAbsoluteFile().lastModified();
                long listItemMod = listFile.get(i).lastModified();
//                String statusListItem = "";
//                String statusMapItem = "";
                File[] coupleOfFiles = {fComp, listFile.get(i).getAbsoluteFile()};
                if (mapItemMod > listItemMod) {
                    
                    newerBList.add(coupleOfFiles);
//                    statusListItem = "older";
//                    statusMapItem = "newer";
                } else if (mapItemMod < listItemMod) {
//                    statusListItem = "newer";
//                    statusMapItem = "older";
                    newerAList.add(coupleOfFiles);
                } else {
//                    statusListItem = "identify";
//                    statusMapItem = "identify";
                    identifyList.add(coupleOfFiles);
                }
//                FileProp file1 = new FileProp();
//                file1.setName(fName);
//                file1.setLastModified(listItem.lastModified());
//                file1.setPath(listItem.getAbsolutePath());
//                file1.setStatus(statusListItem);
//                result[i][0] = file1;
//                FileProp file2 = new FileProp();
//                file2.setName(fComp.getName());
//                file2.setLastModified(fComp.lastModified());
//                file2.setPath(fComp.getAbsolutePath());
//                file2.setStatus(statusMapItem);
//                result[i][1] = file2;
            } else {
                if(listFile.get(i).lastModified()>newestTime){
                    newestTime = listFile.get(i).lastModified();
                    newestDiff = listFile.get(i).getAbsoluteFile();
                }
                File[] coupleOfFiles = {null, listFile.get(i).getAbsoluteFile()};
//                FileProp file1 = new FileProp();
//                file1.setName(fName);
//                file1.setLastModified(listItem.lastModified());
//                file1.setPath(listItem.getAbsolutePath());
//                file1.setStatus("only");
//                result[i][0] = file1;
                onlyBList.add(coupleOfFiles);
            }
        }
        int remain = 0;
        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            System.out.println("remain: "+remain);
            String n = it.next();
            System.out.println("n: "+n);
            remain++;
            File fileFrmMap = map.get(n);
            if(fileFrmMap.lastModified()>newestTime){
                    newestTime = fileFrmMap.lastModified();
                    newestDiff = fileFrmMap;
                }
            map.remove(n);
            {
//                FileProp file2 = new FileProp();
//                file2.setName(fileFrmMap.getName());
//                file2.setLastModified(fileFrmMap.lastModified());
//                file2.setPath(fileFrmMap.getAbsolutePath());
//                file2.setStatus("only");
//                result[remain + listFile.size() - 1][1] = file2;
                File[] coupleOfFiles = {fileFrmMap, null};
                onlyAList.add(coupleOfFiles);
            }
        }
        File[] newestOfDiff = {newestDiff,null};
        newestList.add(newestOfDiff);
        result.put("1", onlyAList);
        result.put("2", onlyBList);
        result.put("3", newerAList);
        result.put("4", newerBList);
        result.put("5", identifyList);
        result.put("6", newestList);
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
