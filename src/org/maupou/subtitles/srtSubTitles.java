/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.maupou.subtitles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Patrice
 */
public class srtSubTitles implements ArraySubTitles {

    private enum status {

        range, times, text, empty
    };

    @Override
    public Object[][] ArrayEntries(List<String> lines) throws IOException {
        Object[][] tabEntries;
        ArrayList<Object[]> subtitleItems = new ArrayList();
        String time = "\\d\\d:\\d\\d:\\d\\d,\\d\\d\\d";
        Pattern timeseq = Pattern.compile("(" + time + ")\\s-->\\s(" + time + ")");
        String txt = "", timeStart = "", timeEnd = "";
        Integer rg = 0;
        status s = status.range;
        for (String line : lines) {
            switch (s) {
                case range:
                    Integer.parseInt(line.trim());
                    s = status.times;
                    break;
                case times:
                    Matcher m = timeseq.matcher(line.trim());
                    if (m.matches()) {
                        timeStart = m.group(1);
                        timeEnd = m.group(2);
                    }
                    s = status.text;
                    break;
                case text:
                    if (line.isEmpty()) {
                        txt = txt.substring(0, txt.length() - 2);
                        subtitleItems.add(new Object[]{rg, timeStart, timeEnd, txt});
                        rg++;
                        s = status.range;                        
                        txt = "";
                    } else {
                        txt += line + "\\n";
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
        tabEntries = new Object[subtitleItems.size()][4];
        subtitleItems.toArray(tabEntries);
        return tabEntries;
    }
}