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
public class subSubTitles implements ArraySubTitles {

    @Override
    public Object[][] ArrayEntries(List<String> lines) throws IOException {
        Object[][] tabEntries;
        ArrayList<Object[]> subtitleItems = new ArrayList();
        String txt = "", timeStart = "", timeEnd = "";
        Integer rg = 0;
        Pattern p = Pattern.compile("\\{(\\d+)\\}\\{(\\d+)\\}(.*)");
        for (String line : lines) {
            Matcher m = p.matcher(line.trim());
            if (m.matches()) {
                try {
                    int i0 = Integer.parseInt(m.group(1));
                    int i1 = Integer.parseInt(m.group(2));
                    timeStart = srtVisualElement.getTime(i0);
                    timeEnd = srtVisualElement.getTime(i1);
                    txt = m.group(3).replace("|", "\\n");
                    subtitleItems.add(new Object[]{rg, timeStart, timeEnd, txt});
                    rg++;
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
        tabEntries = new Object[subtitleItems.size()][4];
        subtitleItems.toArray(tabEntries);
        return tabEntries;
    }
}
