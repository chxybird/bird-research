package com.bird.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @Author lipu
 * @Date 2021/1/23 17:58
 * @Description JDK新日期API
 */
public class Client {

    /**
     * @Author lipu
     * @Date 2021/1/23 18:27
     * @Description 日期创建
     */
    public static void initDate(){
        //日期LocalDate 表示年月日
        LocalDate localDate=LocalDate.now();
        LocalDate date=LocalDate.of(2021,1,20);
        System.out.println(localDate);
        System.out.println(date);
        //日期LocalTime 表示时分秒
        LocalTime localTime = LocalTime.now();
        LocalTime time = LocalTime.of(21, 23, 36);
        System.out.println(localTime);
        System.out.println(time);
        //日期LocalDateTime 表示年月日时分秒
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 18:36
     * @Description 日期修改
     */
    public static void update(){
        LocalDate date=LocalDate.now();
        //向后加3天
        LocalDate newDate = date.plusDays(3);
        //向前减2个月
        LocalDate finalDate = newDate.minusMonths(2);
        System.out.println(finalDate);
    }

    /**
     * @Author lipu
     * @Date 2021/1/23 18:39
     * @Description 日期字符串格式化
     */
    public static void format(){
        LocalDateTime dateTime=LocalDateTime.now();
        //使用JDK默认提供的格式
        DateTimeFormatter formatter=DateTimeFormatter.ISO_DATE;
        String newDate = formatter.format(dateTime);
        System.out.println(newDate);
        //自定义格式
        DateTimeFormatter fmt=DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH小时mm分ss秒");
        String customerDate = fmt.format(dateTime);
        System.out.println(customerDate);

        //字符串解析为日期
        LocalDateTime time = LocalDateTime.parse("2021年01月23日 18小时45分05秒",fmt);
        System.out.println(time);
    }

    public static void main(String[] args) {

    }
}
