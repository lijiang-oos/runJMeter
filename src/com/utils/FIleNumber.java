package com.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FIleNumber {
    public static List fileNumber() {
        //创建文件目录
        File file = new File("./scprit");
        List list = new ArrayList();
        //调用方法打印所有的Java文件名
        printFileName(file, list);
        return list;
    }

    //定义方法打印给定目录下的所有的Java文件,参数为文件的目录,返回值为空
    public static void printFileName(File file, List list) {
        if (file.isDirectory() && file.exists()) {//参数file是目录并且存在
            //生成File数组
            File[] fileList = file.listFiles();


            //遍历集合
            for (File f : fileList) {
                if (f.isDirectory()) {//次元素是目录
                    printFileName(f, list);//递归调用
                } else {
                    if (f.getName().endsWith(".jmx")) {//如果是以.jmx结尾
//                            System.out.println(f.getName());//打印该文件的名字
                        StringBuffer a = new StringBuffer(f.getName());
                        a.delete(a.length() - 4, a.length());
                        list.add(a);


                    }
                }
            }
        } else {
            System.out.println("未找到scprit文件夹");
        }
    }

    //    删除文件夹，和文件夹中的所有文件
    public static void deleteFolder(File folder) throws IOException, InterruptedException {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }


}
