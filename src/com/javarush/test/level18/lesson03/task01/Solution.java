package com.javarush.test.level18.lesson03.task01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* Максимальный байт
Ввести с консоли имя файла
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileInputStream readFile = new FileInputStream(fileName);

        int max = 0;
        int tmp;

        while(readFile.available() > 0){
            tmp = readFile.read();
            if(tmp > max){
                max = tmp;
            }
        }

        System.out.println(max);
        reader.close();
        readFile.close();
    }
}
