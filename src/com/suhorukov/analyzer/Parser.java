package com.suhorukov.analyzer;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Parser {
    private FileReader fReader;
    private StringBuilder word = new StringBuilder();
    private Map<String, Integer> popularity = new HashMap<String, Integer>();
    private List<String> words = new LinkedList<String>();

    public Parser (String fileName){
        try {
            fReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Завершение программы.");
            System.exit(1);
        }
    }

    public void parse(){
        int c = 0;
        //Читаем посимвольно
        while (c != -1){
            try {
                c = fReader.read();
            } catch (IOException e) {
                System.out.println("Произошла ошибка чтения файла.");
            }

            if (Character.isLetterOrDigit(c)){
                word.append((char)c);
            } else {    //Если символ - не буква, скидываем получившееся слово в мэп
                if (popularity.containsKey(word.toString().toLowerCase()) && !(word.toString().isEmpty())){
                    int val = popularity.get(word.toString().toLowerCase());
                    val++;
                    popularity.put(word.toString().toLowerCase(), val);
                } else {
                    popularity.put(word.toString().toLowerCase(), 1);
                }
                word = new StringBuilder();
            }
        }

        //Скидываем слова в массив
        for(String key : popularity.keySet()){
            words.add(key);
        }
    }

    public void printList(){
        for (String w : words){
            System.out.println(w + " -> " + popularity.get(w));
        }
    }

    public void sortList(){
        if (words.size() < 2){
            System.out.println("В списке слов меньше двух элементов, сортировать нечего.");
            return;
        }

        Comparator cmp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int result;
                Integer i1 = popularity.get(s1);
                Integer i2 = popularity.get(s2);
                result = i2.compareTo(i1);
                if (result == 0) {
                    result = s1.compareTo(s2);
                }
                return result;
            }
        };

        Collections.sort(words, cmp);
    }

    public void dumpToFile(){
        FileWriter fWriter = null;
        try {
            fWriter = new FileWriter("out.csv");
        } catch (IOException e) {
            System.out.println("Невозможно открыть выходной файл. Программа остановлена.");
            System.exit(2);
        }
        //Общее кол-во слов
        int wordCount = 0;
        for (Integer value : popularity.values()){
            wordCount += value.intValue();
        }

        for (String w : words){
            double percent = popularity.get(w) / (double)wordCount * 100;
            String str = w + "," + popularity.get(w).toString() + "," + percent + "\n";
            try {
                fWriter.write(str);
            } catch (IOException e) {
                System.out.println("Ошибка записи в выходной файл. Программа остановлена.");
                System.exit(3);
            }
        }
    }
}



