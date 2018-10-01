package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 根据 formatter格式返回系统日期
     */
    public static String getDateTime(String formatter){
        SimpleDateFormat df=new SimpleDateFormat(formatter);
        return df.format(new Date());
    }

    public static String getDateTime(){
        return DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getDate(){
        return DateUtils.getDateTime("yyyy-MM-dd");
    }
}
