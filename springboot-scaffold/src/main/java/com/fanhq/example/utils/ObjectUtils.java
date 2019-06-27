package com.fanhq.example.utils;

import java.io.Serializable;

/**
 * @author fanhaiqiu
 * @date 2019/4/23
 */
public class ObjectUtils {

    /**
     * @param value
     * @return
     */
    public static boolean isNotNull(Object value) {
        if (value == null) {
            return false;
        }
        return true;
    }

    /**
     * @param value
     * @return
     */
    public static boolean isNull(Object value) {
        return value == null;
    }

    /**
     * 空对象
     */
    public static final Null NULL = new Null();

    public static class Null implements Serializable {
        /**
         * Required for serialization support. Declare serialization compatibility with Commons Lang 1.0
         *
         * @see Serializable
         */
        private static final long serialVersionUID = 7092611880189329093L;

        /**
         * Restricted constructor - singleton.
         */
        private Null() {
            super();
        }
    }
}
