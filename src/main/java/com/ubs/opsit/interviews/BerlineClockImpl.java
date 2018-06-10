package com.ubs.opsit.interviews;

import java.util.stream.Stream;

/**
 * Created by Rahul on 6/10/2018.
 */
public class BerlineClockImpl implements TimeConverter {

    public static final String NEW_LINE = "\n";

    public String convertTime(String aTime) {
        int[] parts = Stream.of(aTime.split(":")).mapToInt(Integer::parseInt).toArray();

        StringBuffer buffer = new StringBuffer();
        buffer
            .append(getSeconds(parts[2])).append(NEW_LINE)
            .append(getTopHours(parts[0])).append(NEW_LINE)
            .append(getBottomHours(parts[0])).append(NEW_LINE)
            .append(getTopMinutes(parts[1])).append(NEW_LINE)
            .append(getBottomMinutes(parts[1]));

        return buffer.toString();
    }

    protected String getSeconds(int number) {
        if (number % 2 == 0) return "Y";
        else return "O";
    }

    protected String getTopHours(int number) {
        return getOnOff(4, getTopNumberOfOnSigns(number));
    }

    protected String getBottomHours(int number) {
        return getOnOff(4, number % 5);
    }

    protected String getTopMinutes(int number) {
        return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
    }

    protected String getBottomMinutes(int number) {
        return getOnOff(4, number % 5, "Y");
    }

    private String getOnOff(int lamps, int onSigns) {
        return getOnOff(lamps, onSigns, "R");
    }

    private String getOnOff(int lamps, int onLamps, String onLamp) {
        String out = "";
        for (int i = 0; i < onLamps; i++) {
            out += onLamp;
        }
        for (int i = 0; i < (lamps - onLamps); i++) {
            out += "O";
        }
        return out;
    }

    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }
}

