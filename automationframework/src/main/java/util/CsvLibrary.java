package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvLibrary {

    public static List<List<String>> getLocators(String locatorpage) {
        String line = "";
        String splitBy = ",";
        List<List<String>> csvData = new ArrayList<List<String>>();

        try {
            int i = 0;
            BufferedReader br = new BufferedReader(new FileReader(locatorpage));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {

                List<String> el = Arrays.asList(line.split(splitBy));
                String[] ele = line.split(splitBy);    // use comma as separator
                csvData.add(el);
            }
            for (List<String> data : csvData) {
                System.out.println(data.get(0) + "\t" + data.get(1) + "\t" + data.get(2));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvData;
    }
}
