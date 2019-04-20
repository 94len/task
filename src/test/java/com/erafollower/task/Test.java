package com.erafollower.task;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @describe
 * @auth len
 * @createTime 2019/4/20
 */
public class Test {
    public static void main(String[] args) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Long dateL = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println("时间戳 >>> "+dateL);
        System.out.println("Date >>> "+df.format(LocalDateTime.ofEpochSecond(dateL/1000,0,ZoneOffset.ofHours(8))));//1555750166931
    }
}
