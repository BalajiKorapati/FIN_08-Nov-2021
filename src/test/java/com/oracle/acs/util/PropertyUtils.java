// 
// Decompiled by Procyon v0.5.36
// 

package com.oracle.acs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class PropertyUtils
{
    private static Properties props;
    
    public static void loadProperties(final InputStream is, final boolean importAll) throws IOException {
        final Properties tempProps = new Properties();
        tempProps.load(is);
        loadProperties(tempProps, importAll);
    }
    
    public static void loadSystemProperties(final boolean importAll) {
        final Properties systemProps = System.getProperties();
        loadProperties(systemProps, importAll);
    }
    
    public static void loadEnvironmentVariables(final boolean importAll) {
        final Properties envProps = new Properties();
        envProps.putAll(System.getenv());
        loadProperties(envProps, importAll);
    }
    
    public static String getProperty(final String key) {
        return PropertyUtils.props.getProperty(key.toLowerCase());
    }
    
    public static void setProperty(final String key, final String value) {
        PropertyUtils.props.setProperty(key.toLowerCase(), value);
    }
    
    public static String dumpProperties() {
        final Enumeration<?> e = getSortedKeys();
        final StringBuilder sb = new StringBuilder();
        while (e.hasMoreElements()) {
            final String key = (String)e.nextElement();
            sb.append(key + " = " + PropertyUtils.props.getProperty(key) + "\n");
        }
        return sb.toString();
    }
    
    private static Enumeration<String> getSortedKeys() {
        final Enumeration<?> keysEnum = PropertyUtils.props.keys();
        final Vector<String> keyList = new Vector<String>();
        while (keysEnum.hasMoreElements()) {
            keyList.add((String)keysEnum.nextElement());
        }
        Collections.sort(keyList);
        return keyList.elements();
    }
    
    private static void loadProperties(final Properties newProps, final boolean importAll) {
        final Enumeration<?> e = newProps.keys();
        while (e.hasMoreElements()) {
            final String key = (String)e.nextElement();
            final String newKey = key.toLowerCase();
            if (importAll || PropertyUtils.props.getProperty(newKey) != null) {
                PropertyUtils.props.put(newKey, newProps.getProperty(key));
            }
        }
    }
    
    public static void clearProperties() {
        PropertyUtils.props.clear();
    }
    
    static {
        PropertyUtils.props = new Properties();
    }
}
