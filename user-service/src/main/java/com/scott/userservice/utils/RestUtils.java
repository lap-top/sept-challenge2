package com.scott.userservice.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

public class RestUtils {
    public static String[] nullProperties(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        return Arrays.stream(pds).filter(pd -> src.getPropertyValue(pd.getName()) == null).map(pd -> pd.getName()).toArray(value -> new String[value]);

    }
}
