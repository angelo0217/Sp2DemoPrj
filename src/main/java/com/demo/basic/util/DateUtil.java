package com.demo.basic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class DateUtil {
    public static final SimpleDateFormat YDMHS_FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getNow(){
        return YDMHS_FMT.format(new Date());
    }

}
