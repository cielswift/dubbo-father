package com.ciel.provider.j8;

import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.output.ScanOutput;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class J8DATE {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalDate of = LocalDate.of(2019, 12, 12);
        System.out.println(of);
        System.out.println(localDate);

        LocalTime now = LocalTime.now();
        System.out.println(now);
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        LocalDateTime of1 = LocalDateTime.of(2019, 12, 12, 8, 30, 5);
        System.out.println(of1);

        LocalDateTime localDateTime = of1.withYear(2050); //修改日期
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = localDateTime.plusYears(2); //增加时间
        LocalDateTime localDateTime2 = localDateTime1.minusMonths(24); //减去时间


        int i = localDateTime2.compareTo(localDateTime1);
        System.out.println(i);

        boolean after = localDateTime2.isAfter(localDateTime1); //l2 是否在 l1的后面
        boolean before = localDateTime2.isBefore(localDateTime1);
        boolean equal = localDateTime2.isEqual(localDateTime1);

        System.out.println(after + "==" + before + ";;" + equal);

        Duration between = Duration.between(localDateTime2, localDateTime1); //日期比较器
        long toDays = between.toDays(); //两个日期相差天数
        System.out.println(toDays);

        Period period = Period.between(localDate, of); //日期比较器
        int years = period.getDays();
        System.out.println("qq"+years);


        DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //格式化
        String format = localDateTime2.format(df2);
        System.out.println(format);

        LocalDateTime parse = LocalDateTime.parse("2019-12-01 12:13:48",df2); //解析
        System.out.println(parse);

        Instant instant = Instant.now(); //时间戳

        System.out.println("aa::"+instant);

        //时间校正器


        LocalDateTime xz = LocalDateTime.now();
        LocalDateTime localDateTime3 = xz.plusMonths(1).withDayOfMonth(1);//调整到下一个月第一天
        System.out.println(localDateTime3);

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds(); //返回所有时区
        availableZoneIds.forEach( t -> System.err.println(t));


        ZonedDateTime zonedDateTime = ZonedDateTime.now(Clock.systemUTC()); //世界标准时间
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now();
        ZonedDateTime zonedDateTime3 = ZonedDateTime.now(ZoneId.of("America/Cuiaba"));
        zonedDateTime3.withZoneSameInstant(ZoneId.of("America/Cuiaba")); //修改时区,也改时间
        zonedDateTime3.withZoneSameLocal(ZoneId.of("America/Cuiaba"));//只改时区,不改时间
        System.out.println(zonedDateTime3);



    }
}
