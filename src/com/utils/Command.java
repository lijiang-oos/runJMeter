package com.utils;


import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class Command {

    // 命令行工具用于使用启动JMeter
    public static void exeCmd(String commandStr) throws IOException {


        System.out.println("JMeter开始执行脚本：");


        System.out.println(commandStr);

        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




    public static void main(String[] args) throws IOException, InterruptedException {

        File f1 = new File("./jtl");
        if (f1.exists()) {
            FIleNumber.deleteFolder(f1);
            f1.mkdirs();

        } else {
            f1.mkdirs();
        }


        File file = new File("./report");

        if (file.exists()) {
            FIleNumber.deleteFolder(file);
            file.mkdirs();

        } else {

            file.mkdirs();

        }

        long startTime = System.currentTimeMillis();
        List list = FIleNumber.fileNumber();
        String jmx = null;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("--------------------------------------------------------");
        System.out.println("JMeter开始运行时间是：" + simpleDateFormat.format(date));


        System.out.println("                                                ");
        System.out.println("JMeter本次一共需要执行" + list.size() + "个脚本：");
        for (int i = 0; i < list.size(); i++) {
            jmx = list.get(i).toString();
            for (int j = 1; j < 5; j++) {

            }
            //启动JMeter脚本
//            jmeter -JthreadCount=2 -Jcycle=200  jmeter执行的线程数 ，jmeter执行的循环数
            String commandStr = "jmeter.bat -n -t ./scprit/" +jmx+".jmx"
                    + " -l ./jtl/" +jmx+ ".jtl"
                    + " -e -o ./report/" + jmx;
            System.out.println("第" + (i + 1) + "次开始运行：");
            Command.exeCmd(commandStr);

            long wait = 120000;
            System.out.println("休息" + (wait / 1000) + "秒！");
            Thread.sleep(wait);

        }


        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("JMeter运行结束时间是：" + simpleDateFormat1.format(date1));
        long endTime = System.currentTimeMillis();

        System.out.println("一共运行了" + (endTime - startTime) / 1000 + "s");


    }
}
