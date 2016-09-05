package cn.kxlove;

import org.beetl.core.Context;
import org.beetl.core.Function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author happyhaha
 * Created on 16/8/19
 */
public class DateFunction implements Function {
    public DateFunction() {
    }

    public Date call(Object[] paras, Context ctx) {
        if(paras.length == 0) {
            return new Date();
        } else if(paras.length == 2 && paras[0] instanceof String && paras[1] instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat((String)paras[1]);

            try {
                return sdf.parse((String)paras[0]);
            } catch (ParseException var5) {
                throw new RuntimeException("Parse date Error" + var5.getMessage());
            }
        } else {
            throw new RuntimeException("Parse date Error,Args String,Sting ");
        }
    }
}
