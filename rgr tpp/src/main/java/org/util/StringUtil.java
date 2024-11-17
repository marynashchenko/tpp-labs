package org.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static List columnsMatcher(String userInput) {
        List<String> columnNames = new ArrayList<>();
        List<String> columnValues = new ArrayList<>();
        Pattern columnPattern = Pattern.compile("([a-zA-Z_]+)=\"([^\"]+)\"");

        Matcher matcher = columnPattern.matcher(userInput);

        int index = 0;
        while (matcher.find()) {
            columnNames.add(matcher.group(1));
            columnValues.add(matcher.group(2));
            index++;
        }
        List<List> columns = new ArrayList<>();
        columns.add(columnNames);
        columns.add(columnValues);
        return columns;
    }
}
