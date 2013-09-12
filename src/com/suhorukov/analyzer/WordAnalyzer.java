package com.suhorukov.analyzer;

public class WordAnalyzer {
    public static void main(String[] args){
        Parser p = new Parser();
        if (!p.parse(args[0])){
            System.out.println("Невозможно прочитать входной файл.");
        }
        p.sortList();
        if(!p.dumpToFile()){
            System.out.println("невозможно записать в выходной файл.");
        }
    }
}
