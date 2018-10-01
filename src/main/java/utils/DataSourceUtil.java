package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

public class DataSourceUtil {

    private static Logger logger=Logger.getLogger(DataSourceUtil.class);
    private static DataSource dataSource;

    static{
        dataSource=new ComboPooledDataSource("orderMonitor");
    }

    public static synchronized DataSource getDataSource(){
        if(dataSource==null){
            dataSource=new ComboPooledDataSource("orderMonitor");
        }
        return dataSource;
    }
}
