package vn.teca.scopio.base.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Base64Utils;
import vn.teca.scopio.base.model.HinhAnh;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataConvertUtil {

    private static final Logger logger = LogManager.getLogger(DataConvertUtil.class);

    public static String safeToString(Object obj1) {
        return safeToString(obj1, "");
    }
//    public static Base64Utils safeToBase64(Object obj1){
//        if (obj1 == null) {
//            return null;
//        }
//        return (Base64Utils) obj1;
//    }
//    public static  Object safeToOject(){
//////        List<Object> obj = null;
//        Object obj = new Object;
////                  = new Object();
//            if(obj == null){
//                return null;
//            }
//            return (Object) obj;
//    }

    public static Date safeToDate(Object obj1) {
        if (obj1 == null) {
            return null;
        }
        return (Date) obj1;
    }
    public static LocalDateTime safetoLocalDate(Object obj1){
        if (obj1 == null){
            return null;
        }
        return (LocalDateTime) obj1;
    }
    public static LocalDateTime safeToLocalDateTime(Object obj1) {
        if (obj1 instanceof LocalDateTime) {
            return (LocalDateTime) obj1;
        }
        return null; // Trả về null nếu obj1 không phải là LocalDateTime
    }
    public static Timestamp safeToTimestamp(Object obj) {
        if (obj instanceof LocalDateTime) {
            LocalDateTime localDateTime = (LocalDateTime) obj;
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Timestamp.from(instant);
        }
        return null; // Trả về null nếu không thể chuyển đổi thành LocalDateTime
    }
//    public static LocalDateTime timestampToLocalDateTime(Object timestamp) {
//        if (timestamp instanceof Long || timestamp instanceof Integer) {
//            long timestampLong = ((Number) timestamp).longValue(); // Ép kiểu thành long
//            Instant instant = Instant.ofEpochMilli(timestampLong);
//            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//        }
//        return null; // Trả về null nếu timestamp là null
//    }
//    public static Long safeToTimestamp(Object obj) {
//        if (obj instanceof LocalDateTime) {
//            LocalDateTime localDateTime = (LocalDateTime) obj;
//            return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
//        }
//        return null; // Trả về null nếu không thể chuyển đổi thành LocalDateTime
//    }

    public static Long safeToLong(Object obj1, Long defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        if (obj1 instanceof BigDecimal) {
            return ((BigDecimal) obj1).longValue();
        }
        if (obj1 instanceof BigInteger) {
            return ((BigInteger) obj1).longValue();
        }

        try {
            return Long.parseLong(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static Long safeToLong(Object obj1) {
        return safeToLong(obj1, 0L);
    }

    public static Double safeToDouble(Object obj1, Double defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
    public static BigDecimal safeToBigDecimal(Object obj1, BigDecimal defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return BigDecimal.valueOf(Double.parseDouble(obj1.toString()));
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
    public static BigDecimal safeToBigDecimal(Object obj1) {
        return safeToBigDecimal(obj1, BigDecimal.valueOf(0.00));
    }
    public static boolean isNullOrEmpty(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
    public static boolean isNullOrEmptyObject(final Object object) {
        return object == null ;
    }

    public static void setParams(Query query, Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, Object>> set = params.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> obj = iterator.next();
                query.setParameter(obj.getKey(), obj.getValue());
            }
        }
    }

    public static int safeToInt(Object obj1, Integer defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(obj1.toString());
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static int safeToInt(Object obj1) {
        return safeToInt(obj1, 0);
    }



}
