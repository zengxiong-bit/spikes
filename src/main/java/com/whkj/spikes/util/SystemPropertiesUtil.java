package com.whkj.spikes.util;


import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public final class SystemPropertiesUtil {

    /**
     * 读取Boolean类型的系统变量，为空时返回null，代表未设置，而不是Boolean.getBoolean()的false.
     */
    public static Boolean getBoolean(String name) {
        String stringResult = System.getProperty(name);
        return BooleanUtil.toBooleanObject(stringResult);
    }

    /**
     * 读取Boolean类型的系统变量，为空时返回默认值, 而不是Boolean.getBoolean()的false.
     */
    public static Boolean getBoolean(String name, Boolean defaultValue) {
        String stringResult = System.getProperty(name);
        return BooleanUtil.toBooleanObject(stringResult, defaultValue);
    }

    /**
     * 读取String类型的系统变量，为空时返回null.
     */
    public static String getString(String name) {
        return System.getProperty(name);
    }

    /**
     * 读取String类型的系统变量，为空时返回默认值
     */
    public static String getString(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    /**
     * 读取Integer类型的系统变量，为空时返回null.
     */
    public static Integer getInteger(String name) {
        return Integer.getInteger(name);
    }

    /**
     * 读取Integer类型的系统变量，为空时返回默认值
     */
    public static Integer getInteger(String name, Integer defaultValue) {
        return Integer.getInteger(name, defaultValue);
    }

    /**
     * 读取Long类型的系统变量，为空时返回null.
     */
    public static Long getLong(String name) {
        return Long.getLong(name);
    }

    /**
     * 读取Integer类型的系统变量，为空时返回默认值
     */
    public static Long getLong(String name, Long defaultValue) {
        return Long.getLong(name, defaultValue);
    }




    /////////// 简单的合并系统变量(-D)，环境变量 和默认值，以系统变量优先.///////////////

    /**
     * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
     */
    public static String getString(String propertyName, String envName, String defaultValue) {
        checkEnvName(envName);
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            propertyValue = System.getenv(envName);
            return propertyValue != null ? propertyValue : defaultValue;
        }
    }




    /**
     * 合并系统变量(-D)，环境变量 和默认值，以系统变量优先
     */
    public static Boolean getBoolean(String propertyName, String envName, Boolean defaultValue) {
        checkEnvName(envName);
        Boolean propertyValue = BooleanUtil.toBooleanObject(System.getProperty(propertyName), null);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            propertyValue = BooleanUtil.toBooleanObject(System.getenv(envName), null);
            return propertyValue != null ? propertyValue : defaultValue;
        }
    }

    /////////// ListenableProperties /////////////

    /**
     * Properties 本质上是一个HashTable，每次读写都会加锁，所以不支持频繁的System.getProperty(name)来检查系统内容变化 因此扩展了一个ListenableProperties,
     * 在其所关心的属性变化时进行通知.
     *
     * @see ListenableProperties
     */
    public static synchronized void registerSystemPropertiesListener(PropertiesListener listener) {
        Properties currentProperties = System.getProperties();

        if (!(currentProperties instanceof ListenableProperties)) {
            ListenableProperties newProperties = new ListenableProperties(currentProperties);
            System.setProperties(newProperties);
            currentProperties = newProperties;
        }

        ((ListenableProperties) currentProperties).register(listener);
    }

    /**
     * 检查环境变量名不能有'.'，在linux下不支持
     */
    private static void checkEnvName(String envName) {
        if (envName == null || envName.indexOf('.') != -1) {
            throw new IllegalArgumentException("envName " + envName + " has dot which is not valid");
        }
    }

    /**
     * Properties 本质上是一个HashTable，每次读写都会加锁，所以不支持频繁的System.getProperty(name)来检查系统内容变化 因此扩展了一个ListenableProperties,
     * 在其所关心的属性变化时进行通知.
     *
     * @see PropertiesListener
     */
    public static class ListenableProperties extends Properties {

        private static final long serialVersionUID = -8282465702074684324L;

        protected List<PropertiesListener> listeners = new CopyOnWriteArrayList<PropertiesListener>();

        public ListenableProperties(Properties properties) {
            super(properties);
        }

        public void register(PropertiesListener listener) {
            listeners.add(listener);
        }

        @Override
        public synchronized Object setProperty(String key, String value) {
            Object result = put(key, value);
            for (PropertiesListener listener : listeners) {
                if (listener.propertyName.equals(key)) {
                    listener.onChange(key, value);
                }
            }
            return result;
        }
    }

    /**
     * 获取所关心的Properties变更的Listener基类.
     */
    public abstract static class PropertiesListener {

        protected String propertyName;

        public PropertiesListener(String propertyName) {
            this.propertyName = propertyName;
        }

        public abstract void onChange(String propertyName, String value);
    }

    private SystemPropertiesUtil() {
    }
}
