package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Main {
    private static final String filePath = "C:\\STUDY\\JAVA\\HW_JAVA\\HW_JAVA2\\jsonTestFile.json";
    public static void main(String[] args) {
        createRequest();
    //    sortedBubble();
    //    parseFile();
     }

    /**
     * 1/ Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса, используя StringBuilder. Данные для фильтрации приведены ниже в виде json строки.
     * Если значение null, то параметр не должен попадать в запрос.
     * Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
     */

    public static void createRequest() {

        Map<String, String> params = new HashMap<String,String>();
        params.put("name","Ivanov");
        params.put("country","Russia");
        params.put("city","Moscow");
        params.put("age",null);

        StringBuilder s = new StringBuilder();
        for (Map.Entry<String,String> pair : params.entrySet())
        {
            if (pair.getValue() != null)
            {
                s.append(pair.getKey() +" = '" + pair.getValue()+"'' , ");
            }
        }

        System.out.println(s.toString());
    }

    /**
     * * 2 Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
     */
    public static String sortedBubble() {
        Logger logger = Logger.getLogger(Main.class.getName());
        FileHandler logFile = null;
        try {
            logFile = new FileHandler("logfile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(logFile);
        SimpleFormatter sFormat = new SimpleFormatter();
        logFile.setFormatter(sFormat);
        int [] numbers = {3, 10, 8, 12, 16, 9};;

        boolean isSorted = false;
        int value;
        while(!isSorted)
        {
            isSorted = true;
            for (int i = 0; i < numbers.length-1; i++)
            {
                if(numbers[i] > numbers[i+1])
                {
                    isSorted = false;
                    value = numbers[i];
                    numbers[i] = numbers[i+1];
                    numbers[i+1] = value;
                }
                logger.info(Arrays.toString(numbers));
            }
        }
        return Arrays.toString(numbers);
    }

    /**
     * 3 Дана json строка (можно сохранить в файл и читать из файла)
     * [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
     * {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
     * Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида:
     * Студент [фамилия] получил [оценка] по предмету [предмет].
     */

    public static void parseFile() {

        try {
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            JSONArray lang= (JSONArray) jsonObject.get("студенты");

            Iterator i = lang.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                System.out.println("Студент "+ innerObj.get("фамилия") +
                        " получил " + innerObj.get("оценка") + " по предмету " + innerObj.get("предмет"));
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }
}

