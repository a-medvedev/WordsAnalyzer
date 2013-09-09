package com.suhorukov.analyzer;

public class WordAnalyzer {
    public static void main(String[] args){
        Parser p = new Parser();
        p.parse("D:\\mm.txt");
        p.sortList();
        p.dumpToFile();

    }
}
