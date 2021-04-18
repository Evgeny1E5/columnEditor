package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static BufferedWriter bwFile;


    public static void main(String[] args) throws Exception {
        /*String array[] = "hello".split("");
        String oneItem = array[0];
        array[0] = array[3];
        array[3] = oneItem;
        System.out.println(String.join("", array));*/
       // System.out.println("Enter file name");


        String lineFile = "", lineCommand = "";
        String[] arrSplited;

        for(;;){
            BufferedReader brConsole = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader brFile = new BufferedReader(new FileReader("pk"));
            System.out.println("Wait for command... move or del");
            lineCommand = brConsole.readLine();
            arrSplited = lineCommand.split(" ");
            System.out.println("Reading file...");

            while((lineFile = brFile.readLine()) != null){
                list.add(lineFile);
            }
            System.out.println("File read end. Lines = " + list.size());
            brFile.close();

            bwFile = new BufferedWriter(new FileWriter("pk"));


            if(arrSplited[0].equals("move")){
                move(Integer.valueOf(arrSplited[1])-1, Integer.valueOf(arrSplited[2])-1);
                System.out.println("Command " + lineCommand + " ended");
            }else if(arrSplited[0].equals("del")){
                if(arrSplited.length == 2){
                    del(Integer.valueOf(arrSplited[1])-1);
                } else del(Integer.valueOf(arrSplited[1])-1, Integer.valueOf(arrSplited[2])-1);
            }
            brFile.close();
            list.clear();
        }

    }

    static void move(int startPosition, int endPosition) throws Exception {
        String[] stringArray;
        String oneItem;

        for(int i = 0;i<list.size();i++){
            stringArray = list.get(i).split("");
            oneItem = stringArray[startPosition];
            stringArray[startPosition] = stringArray[endPosition];
            stringArray[endPosition] = oneItem;
            //list.set(i, String.join("", stringArray));
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
    }

    static void del(int position) throws Exception {
        String[] stringArray;
        String oneItem;

        for(int i = 0;i<list.size();i++){
            stringArray = list.get(i).split("");
            //list.set(i, String.join("", stringArray));
            stringArray[position] = "";
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
    }

    static void del(Integer startPosition, Integer endPosition) throws Exception {
        String[] stringArray;
        String oneItem;
        int start, end;
        end = endPosition;

        for(int i = 0;i<list.size();i++){
            stringArray = list.get(i).split("");

            for(start = startPosition;start<=end; start++){
                stringArray[start] = "";
            }
            bwFile.write(String.join("", stringArray));
            bwFile.write("\n");
        }
        bwFile.flush();
        bwFile.close();
    }

}
