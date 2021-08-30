package com.yxx.ticketing.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 *
 *
 * date类型操作工具类
 */
public class DataUtils {

    /**
     * 获取当前时间的下一天的时间
     * @param today
     * @return
     */
    public static Date getTomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    /**
     * 获取当天的0点时间
     * @return
     */
    public static Date initDateByDay(Date today){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


}
