package com.pos.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AutoGenerate {
    public static String barcode(){
        String barcode ="";
        return barcode;
    }

    public static Long num_id(){
        TimeZone jakartaTimeZone = TimeZone.getTimeZone("Asia/Jakarta");
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyySSSS");
        dateFormat.setTimeZone(jakartaTimeZone);
        Long generateId = Long.valueOf(dateFormat.format(new Date()));
        return generateId;
    }
}
