package com.ubs.opsit.interviews;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rahul on 6/10/2018.
 */
public class BerlineClockImplTest {

    BerlineClockImpl clock = new BerlineClockImpl();

    // Time Tests

    // When the time is 00:00:00
    @Test
    public void testWhenTimeIs000000() {
        String s = "Y\n" +
                "OOOO\n" +
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO";

        Assert.assertEquals(s, clock.convertTime("00:00:00"));
    }

    // When the time is 13:17:01
    @Test
    public void testWhenTimeIs131701() {
        String s = "O\n" +
                "RROO\n" +
                "RRRO\n" +
                "YYROOOOOOOO\n" +
                "YYOO";

        Assert.assertEquals(s, clock.convertTime("13:17:01"));
    }

    // When the time is 23:59:59
    @Test
    public void testWhenTimeIs235959() {
        String s = "O\n" +
                "RRRR\n" +
                "RRRO\n" +
                "YYRYYRYYRYY\n" +
                "YYYY";

        Assert.assertEquals(s, clock.convertTime("23:59:59"));
    }

    // When the time is 24:00:00
    @Test
    public void testWhenTimeIs240000() {
        String s = "Y\n" +
                "RRRR\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO";

        Assert.assertEquals(s, clock.convertTime("24:00:00"));
    }

    // Core Clock Tests

    // Yellow lamp should blink on/off every two seconds
    @Test
    public void testYellowLampShouldBlinkOnOffEveryTwoSeconds() {
        Assert.assertEquals("Y", clock.getSeconds(0));
        Assert.assertEquals("O", clock.getSeconds(1));
        Assert.assertEquals("O", clock.getSeconds(59));
    }

    // Top hours should have 4 lamps
    @Test
    public void testTopHoursShouldHave4Lamps() {
        Assert.assertEquals(4, clock.getTopHours(7).length());
    }

    // Top hours should light a red lamp for every 5 hours
    @Test
    public void testTopHoursShouldLightRedLampForEvery5Hours() {
        Assert.assertEquals("OOOO", clock.getTopHours(0));
        Assert.assertEquals("RROO", clock.getTopHours(13));
        Assert.assertEquals("RRRR", clock.getTopHours(23));
        Assert.assertEquals("RRRR", clock.getTopHours(24));
    }

    // Bottom hours should have 4 lamps
    @Test
    public void testBottomHoursShouldHave4Lamps() {
        Assert.assertEquals(4, clock.getBottomHours(5).length());
    }

    // Bottom hours should light a red lamp for every hour left from top hours
    @Test
    public void testBottomHoursShouldLightRedLampForEveryHourLeftFromTopHours() {
        Assert.assertEquals("OOOO", clock.getBottomHours(0));
        Assert.assertEquals("RRRO", clock.getBottomHours(13));
        Assert.assertEquals("RRRO", clock.getBottomHours(23));
        Assert.assertEquals("RRRR", clock.getBottomHours(24));
    }

    // Top minutes should have 11 lamps
    @Test
    public void testTopMinutesShouldHave11Lamps() {
        Assert.assertEquals(11, clock.getTopMinutes(34).length());
    }

    // Top minutes should have 3rd, 6th and 9th lamps in red to indicate first quarter, half and last quarter
    @Test
    public void testTopMinutesShouldHave3rd6thAnd9thLampsInRedToIndicateFirstQuarterHalfAndLastQuarter() {
        String minutes32 = clock.getTopMinutes(32);
        Assert.assertEquals("R", minutes32.substring(2, 3));
        Assert.assertEquals("R", minutes32.substring(5, 6));
        Assert.assertEquals("O", minutes32.substring(8, 9));
    }

    // Top minutes should light a yellow lamp for every 5 minutes unless it's first quarter, half or last quarter
    @Test
    public void testTopMinutesShouldLightYellowLampForEvery5MinutesUnlessItIsFirstQuarterHalfOrLastQuarter() {
        Assert.assertEquals("OOOOOOOOOOO", clock.getTopMinutes(0));
        Assert.assertEquals("YYROOOOOOOO", clock.getTopMinutes(17));
        Assert.assertEquals("YYRYYRYYRYY", clock.getTopMinutes(59));
    }

    // Bottom minutes should have 4 lamps
    @Test
    public void testBottomMinutesShouldHave4Lamps() {
        Assert.assertEquals(4, clock.getBottomMinutes(0).length());
    }

    // Bottom minutes should light a yellow lamp for every minute left from top minutes
    @Test
    public void testBottomMinutesShouldLightYellowLampForEveryMinuteLeftFromTopMinutes() {
        Assert.assertEquals("OOOO", clock.getBottomMinutes(0));
        Assert.assertEquals("YYOO", clock.getBottomMinutes(17));
        Assert.assertEquals("YYYY", clock.getBottomMinutes(59));
    }



}