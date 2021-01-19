package com.example.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    /*
    用 Map value 而不是获取 req 然后获取 Map req.getParameterMap() 注入，使用的耦合度低，泛用性高
     */
    public static <T> T copyParamToBean(Map value , T bean ){
        try {
            /*
            原理是 set 方法注入
            优化可以采用反射注入
             */
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
}
