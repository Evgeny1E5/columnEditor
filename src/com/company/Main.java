package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static BufferedWriter bwFile;
    static BufferedReader brFile;
    static String fileToRead = "test.txt";

    public static void main(String[] args) throws Exception {
        /*String array[] = "hello".split("");
        String oneItem = array[0];
        array[0] = array[3];
        array[3] = oneItem;
        System.out.println(String.join("", array));*/
       // System.out.println("Enter file name");
        System.out.println(Runtime.getRuntime().totalMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().freeMemory());


        String lineFile = "", lineCommand = "";
        String[] arrSplited;

        for(;;){
            BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
            //file to read
            brFile = new BufferedReader(new FileReader(fileToRead));

            System.out.println("Wait for command... move or del");
            lineCommand = brConsole.readLine();
            arrSplited = lineCommand.split(" ");
            System.out.println("Reading file...");


            //file to write
            //bwFile = new BufferedWriter(new FileWriter("pk"));


            if(arrSplited[0].equals("move")){
                move(Integer.valueOf(arrSplited[1])-1, Integer.valueOf(arrSplited[2])-1);
                System.out.println("Command " + lineCommand + " ended");
            }else if(arrSplited[0].equals("del")){
                if(arrSplited.length == 2){
                    del(Integer.valueOf(arrSplited[1])-1);
                } else del(Integer.valueOf(arrSplited[1])-1, Integer.valueOf(arrSplited[2])-1);
            }else if(arrSplited[0].equals("uniq")){
                System.out.println("Execute unique");
                unique();
            }
            brFile.close();
            list.clear();
        }

    }

    static void move(int startPosition, int endPosition) throws Exception {
        String[] stringArray;
        String oneItem, lineFile;
        bwFile = new BufferedWriter(new FileWriter("out.txt"));

        while((lineFile = brFile.readLine()) != null){
            stringArray = lineFile.split("");
            oneItem = stringArray[startPosition];
            stringArray[startPosition] = stringArray[endPosition];
            stringArray[endPosition] = oneItem;
            //list.set(i, String.join("", stringArray));
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
        brFile.close();
        Files.move(Paths.get("out.txt"), Paths.get("out.txt").resolveSibling(fileToRead),REPLACE_EXISTING);
    }

    static void del(int position) throws Exception {
        String[] stringArray;
        String oneItem, lineFile;
        bwFile = new BufferedWriter(new FileWriter("out.txt"));

        while((lineFile = brFile.readLine()) != null){
            stringArray = lineFile.split("");
            //list.set(i, String.join("", stringArray));
            stringArray[position] = "";
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
        brFile.close();
        Files.move(Paths.get("out.txt"), Paths.get("out.txt").resolveSibling(fileToRead),REPLACE_EXISTING);

    }

    static void del(Integer startPosition, Integer endPosition) throws Exception {
        String[] stringArray;
        String oneItem, lineFile;
        int start, end;
        end = endPosition;
        bwFile = new BufferedWriter(new FileWriter("out.txt"));


        while((lineFile = brFile.readLine()) != null){
            stringArray = lineFile.split("");

            for(start = startPosition;start<=end; start++){
                stringArray[start] = "";
            }
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
        brFile.close();
        Files.move(Paths.get("out.txt"), Paths.get("out.txt").resolveSibling(fileToRead),REPLACE_EXISTING);

    }
    static void unique() throws Exception {
        String lineFile, lineFileOut;
        boolean firstNewLine = false;
        HashSet<String> hashSet = new HashSet<>();

        bwFile = new BufferedWriter(new FileWriter("out.txt"));

        while ((lineFile = brFile.readLine()) != null) {
            if (hashSet.size() < 34000000) {
                hashSet.add(lineFile);
                if (hashSet.size() % 1000000 == 0) {
                    System.out.println(hashSet.size());


                }
            } else {
                for (String s : hashSet) {
                    bwFile.write(s);
                    bwFile.newLine();
                }
                bwFile.flush();
                hashSet.clear();
                hashSet.add(lineFile);
                System.out.println("Writed to file");
            }
        }
        for (String s : hashSet) {
            bwFile.write(s);
            bwFile.newLine();
        }
        hashSet.clear();
        brFile.close();
        bwFile.close();


    }

}
