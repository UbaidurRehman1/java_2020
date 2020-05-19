package com.ubaid.io;

import java.io.*;

public class IODemo {

    public static void main(String[] args) {
//        copyFileWithoutBuffer();
        copyFileWithBufferAndArray();
    }

    public static void copyFileWithBufferAndArray() {
        System.out.println("Copying With Buffer");
        long startTime, timeElapsed;
        String path = "/home/ubaid/dev/java_2020/M1/src/io.jpg";
        String out = "/home/ubaid/dev/java_2020/M1/src/new2.jpg";
        File file = new File(path);
        File outFile = new File(out);
        System.out.println("Size of File is: " + ((double)file.length() / (1024 * 1024)) + " MB");
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file)); BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outFile))) {
            startTime = System.nanoTime();
            byte[] buffer = new byte[16000 * 2 * 2 * 2];
            int byteRead;
            while((byteRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, byteRead);
            }
            timeElapsed = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (timeElapsed / 1000000.0) + " milli seconds");

        } catch(IOException exp) {
            exp.printStackTrace();
        }

    }

    public static void copyFileWithoutBuffer() {
        System.out.println("Copying Without Buffer");
        long startTime, timeElapsed;
        String path = "/home/ubaid/dev/java_2020/M1/src/io.jpg";
        String out = "/home/ubaid/dev/java_2020/M1/src/new.jpg";
        File file = new File(path);
        File outFile = new File(out);
        System.out.println("Size of File is: " + ((double)file.length() / (1024 * 1024)) + " MB");
        try(FileInputStream inputStream = new FileInputStream(file); FileOutputStream outputStream = new FileOutputStream(outFile)) {
            startTime = System.nanoTime();
            int byteRead;
            while((byteRead = inputStream.read()) != -1) {
                outputStream.write(byteRead);
            }
            timeElapsed = System.nanoTime() - startTime;
            System.out.println("Elapsed Time is " + (timeElapsed / 1000000.0) + " milli seconds");

        } catch(IOException exp) {
            exp.printStackTrace();
        }
    }
}
