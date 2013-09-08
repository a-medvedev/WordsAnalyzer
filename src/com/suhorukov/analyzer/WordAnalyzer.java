package com.suhorukov.analyzer;

public class WordAnalyzer {
    public static void main(String[] args){
        Parser p = new Parser("D:\\mm.txt");
        p.parse();
        p.sortList();
        p.dumpToFile();
    }
}
