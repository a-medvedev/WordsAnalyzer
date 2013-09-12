package com.suhorukov.analyzer;

public class WordAnalyzer {
    public static void main(String[] args){
        if (args.length < 0){
            System.out.println("Входной файл не указан!");
            return;
        }
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
