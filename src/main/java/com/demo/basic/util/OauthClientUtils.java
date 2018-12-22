package com.demo.basic.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public final class OauthClientUtils {
    public static Date dateFormat(String dateStr) {

        SimpleDateFormat mt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return mt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 參數轉換，將"1,2,3"轉換為[1,2,3]集合
     *
     * @param str
     * @param type
     * @return
     */
    public static List transformStringToList(String str, Class type) {

        List<? super Object> parameters = new ArrayList<>();

        String[] tmps = str.trim().split(",");

        String typeName = type.getSimpleName();

        switch (typeName) {
            case "Byte":
                for (String item : tmps) {
                    try {
                        Byte var1 = Byte.parseByte(item);
                        parameters.add(var1);
                    } catch (Exception e) {
                        throw e;
                    }
                }
                break;
            case "Long":
                for (String item : tmps) {
                    try {
                        Long var1 = Long.parseLong(item);
                        parameters.add(var1);
                    } catch (Exception e) {
                        throw e;
                    }
                }
            default:
                for (String item : tmps) {
                    parameters.add(item);
                }
        }

        return parameters;
    }


    public static Set transformStringToSet(String str, Class type) {

        Set<? super Object> parameters = new HashSet<>();

        String[] tmps = str.trim().split(",");

        String typeName = type.getSimpleName();

        switch (typeName) {
            case "Byte":
                for (String item : tmps) {
                    try {
                        Byte var1 = Byte.parseByte(item);
                        parameters.add(var1);
                    } catch (Exception e) {
                        throw e;
                    }
                }
                break;
            case "Long":
                for (String item : tmps) {
                    try {
                        Long var1 = Long.parseLong(item);
                        parameters.add(var1);
                    } catch (Exception e) {
                        throw e;
                    }
                }
            default:
                for (String item : tmps) {
                    parameters.add(item);
                }
        }

        return parameters;
    }

    /**
     * Map轉換為對象
     *
     * @param map
     * @param target
     * @return
     * @throws Exception
     */
    public static Object transformMapToObject(Map map, Class target) throws Exception {

        BeanInfo beanInfo = Introspector.getBeanInfo(target);

        // 獲取所有屬性
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

        Object obj = target.newInstance();

        for (PropertyDescriptor descriptor : descriptors) {

            // map中是否包含該屬性
            if (map.containsKey(descriptor.getName())) {

                String propertySimpleName = descriptor.getPropertyType().getSimpleName();

                String key = descriptor.getName();

                if (propertySimpleName.equals("Byte") || propertySimpleName.equals("byte")) {
                    Byte byteValue = Byte.parseByte(String.valueOf(map.get(key)));
                    descriptor.getWriteMethod().invoke(obj, byteValue);
                    continue;
                }

                if (propertySimpleName.equals("Long") || propertySimpleName.equals("long")) {
                    Long longValue = Long.parseLong(String.valueOf(map.get(key)));
                    descriptor.getWriteMethod().invoke(obj, longValue);
                    continue;
                }

                if (propertySimpleName.equals("Short") || propertySimpleName.equals("short")) {
                    Short shortValue = Short.parseShort(String.valueOf(map.get(key)));
                    descriptor.getWriteMethod().invoke(obj, shortValue);
                    continue;
                }

                if (propertySimpleName.equals("Double") || propertySimpleName.equals("double")) {
                    Double doubleValue = Double.parseDouble(String.valueOf(map.get(key)));
                    descriptor.getWriteMethod().invoke(obj, doubleValue);
                    continue;
                }

                if (propertySimpleName.equals("Float") || propertySimpleName.equals("float")) {
                    Float floatValue = Float.parseFloat(String.valueOf(map.get(key)));
                    descriptor.getWriteMethod().invoke(obj, floatValue);
                    continue;
                }

                // 如果不符合上述條件
                Object value = map.get(key);
                descriptor.getWriteMethod().invoke(obj, value);

            }

        }

        return obj;
    }

    /**
     * 將source中的屬性值賦給target中屬性；
     * 如果source中的屬性值不為空就進行賦值操作
     *
     * @param source
     * @param target 目標賦值類
     * @return
     * @throws Exception
     */
    public static Object copy(Object source, Object target) throws Exception {

        // 獲取物件的描述資訊
        BeanInfo sf = Introspector.getBeanInfo(source.getClass());
        BeanInfo tf = Introspector.getBeanInfo(target.getClass());

        // 獲取屬性描述資訊，其中包括 屬性名稱，類型，屬性的讀寫方法
        PropertyDescriptor[] desSf = sf.getPropertyDescriptors();
        PropertyDescriptor[] desTf = tf.getPropertyDescriptors();

        for (PropertyDescriptor var1 : desSf) {
            // 如果該屬性名是class 並且類型為Class 則跳過
            if (var1.getName().equals("class") && var1.getPropertyType().getSimpleName().equals("Class")) continue;

            for (PropertyDescriptor var2 : desTf) {

                // 如果該屬性名是class 並且類型為Class 則跳過
                if (var1.getName().equals("class") && var2.getPropertyType().getSimpleName().equals("Class")) continue;

                if (var1.getName().equals(var2.getName())) {

                    Method methodSf = var1.getReadMethod();
                    Object obj = methodSf.invoke(source);
                    // 如果該屬性的讀方法返回的值不為空則進行賦值
                    if (obj != null) {
                        Method methodTf = var2.getWriteMethod();
                        // 反射 調用target 對應屬性的寫方法
                        methodTf.invoke(target, obj);
                    }

                }
            }
        }
        return target;
    }

    /**
     * 隨機生成UUID
     */
    public static String uuid32Generator() {

        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString().replace("-", "");
        return uuidStr;
    }

}

