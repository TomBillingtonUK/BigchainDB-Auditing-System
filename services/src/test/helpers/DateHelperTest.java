package test.helpers;

import code.helpers.DateHelper;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class DateHelperTest
{
    @Test
    public void localDateTimeToStringTest()
    {
        String formattedDate = DateHelper.dateToString(LocalDateTime.of(2018, 1, 1, 0, 0, 0));
        Assert.assertEquals(formattedDate, "2018-01-01T00:00:00");

    }

    @Test
    public void stringToLocalDateTimeTest()
    {
        LocalDateTime formattedDate = DateHelper.stringToDate("2018-01-01T00:00:00");
        Assert.assertTrue(formattedDate.getDayOfMonth() == 1);
        Assert.assertTrue(formattedDate.getMonth() == Month.JANUARY);
        Assert.assertTrue(formattedDate.getYear() == 2018);
        Assert.assertTrue(formattedDate.getHour() == 0);
        Assert.assertTrue(formattedDate.getMinute() == 0);
        Assert.assertTrue(formattedDate.getSecond() == 0);
    }


}
