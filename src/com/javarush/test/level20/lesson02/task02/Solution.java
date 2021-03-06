package com.javarush.test.level20.lesson02.task02;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* Читаем и пишем в файл: JavaRush
Реализуйте логику записи в файл и чтения из файла для класса JavaRush
В файле your_file_name.tmp может быть несколько объектов JavaRush
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {

    public static void main(String[] args)
    {
        try {
            File your_file_name = File.createTempFile("save", ".txt", new File("C:\\tmp\\"));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

            JavaRush javaRush = new JavaRush();

            User user1 = new User();
            user1.setBirthDate(dateFormat.parse("21.01.2000"));
            user1.setCountry(User.Country.RUSSIA);
            user1.setLastName("Golubkov");
            user1.setMale(true);

            User user2 = new User();
            user2.setBirthDate(dateFormat.parse("14.03.1995"));
            user2.setCountry(User.Country.OTHER);
            user2.setFirstName("Masha");
            user2.setLastName(null);
            user2.setMale(false);

            javaRush.users.add(user1);
            javaRush.users.add(user2);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        public void save(OutputStream outputStream) throws Exception
        {
            PrintWriter printWriter = new PrintWriter(outputStream);
            for (User user:users){
                printWriter.println("@");
                String firstName = user.getFirstName(); if (firstName == null){firstName  = "noName";}
                printWriter.println(firstName);
                String lastName = user.getLastName(); if (lastName==null){lastName = "noLastname";}
                printWriter.println(lastName);
                printWriter.println(dateFormat.format(user.getBirthDate()));
                printWriter.println(String.valueOf(user.isMale()));
                printWriter.println(user.getCountry());
            }
            printWriter.close();
        }
        public void load(InputStream inputStream) throws IOException, ParseException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while(reader.ready()){
                String newUser = reader.readLine();

                if (newUser.equals("@")){
                    User user = new User();
                    String name = reader.readLine();
                    if (name.equals("noName")){
                        name = null;
                    }
                    user.setFirstName(name);

                    String lastName = reader.readLine();
                    if (lastName.equals("noLastname")){
                        lastName = null;
                    }
                    user.setLastName(lastName);
                    user.setBirthDate(dateFormat.parse(reader.readLine()));
                    if (reader.readLine().equals("true")){
                        user.setMale(true);
                    } else user.setMale(false);
                    user.setCountry(User.Country.valueOf(reader.readLine()));
                    users.add(user);
                }
            }
        }
    }
}