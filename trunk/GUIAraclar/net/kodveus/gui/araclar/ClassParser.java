/*
 * Emre tarafindan Jun 10, 2005 tarihinde yaratilmistir.
 */
package net.kodveus.gui.araclar;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

public class ClassParser {
    private static boolean SHOWLOG = false;

    public ClassParser() {
    }

    public static String getClassName(Class parseClass) {
        return parseClass.getName();
    }

    public Object getValue(Object obj, String propertyname) {
        String name = "get" + ilkKarakterBuyut(propertyname);

        try {
            if (obj.getClass().getMethod(name, (Class[]) null) != null) {
                Method method = obj.getClass().getMethod(name, (Class[]) null);
                Object rtn = method.invoke(obj, (Object[]) null);

                return rtn;
            }
        } catch (Exception ex) {
            if (SHOWLOG) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public static Method getMethod(Class parseClass, String name) {
        Method[] methods = parseClass.getMethods();

        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(name)) {
                return methods[i];
            }
        }

        return null;
    }

    public static void setValue(Object owner, String propertyname, Object value) {
        String name = "set" + ilkKarakterBuyut(propertyname);

        try {
            Object[] props = new Object[] { value };
            Class[] types = new Class[] { value.getClass() };
            Method method = owner.getClass().getMethod(name, types);

            if (method != null) {
                method.invoke(owner, props);

                return;
            }
        } catch (Exception ex) {
            if (SHOWLOG) {
                ex.printStackTrace();
            }
        }

        return;
    }

    public static Object prepareValue(Method method, String value) {
        Class parameterClass = method.getParameterTypes()[0];

        if (parameterClass.getName().equals(String.class.getName())) {
            return value;
        }

        if (parameterClass.getName().equals(int.class.getName())) {
            return Integer.valueOf(value);
        }

        if (parameterClass.getName().equals(Date.class.getName())) {
            return TipCevirici.cevirDate(value);
        }

        if (parameterClass.getName().equals(BigDecimal.class.getName())) {
            try {
                return new BigDecimal(value);
            } catch (Exception ex) {
            }
        }

        return null;
    }

    public static Object createObject(Class parseClass)
        throws Exception {
        Constructor[] conss = parseClass.getConstructors();

        for (int i = 0; i < conss.length; i++) {
            Constructor cons = conss[i];

            if (cons.getParameterTypes().length == 0) {
                return cons.newInstance((Object[]) null);
            }
        }

        return null;
    }

    public static Object createInstance(Class cls) {
        try {
            Constructor cons = cls.getConstructor((Class[]) null);

            return cons.newInstance((Object[]) null);
        } catch (Exception ex) {
            if (SHOWLOG) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public static Object getProperty(Object obj, String propertyname) {
        String name = "get" + ilkKarakterBuyut(propertyname);

        try {
            Method method = getMethod(obj.getClass(), name);

            if (method != null) {
                return method.invoke(obj, (Object[]) null);
            }
        } catch (Exception ex) {
            System.out.println("getProperty(object,string)" + ex.toString());
        }

        return null;
    }

    public static Object getPropertyWithFastGetMethod(Object obj,
        String propertyname) {
        String name = "get" + ilkKarakterBuyut(propertyname);

        try {
            //Method method = obj.getClass().getMethod(name,null);
            Method method = findMethod(obj.getClass(), name);

            if (method != null) {
                return method.invoke(obj, (Object[]) null);
            }
        } catch (Exception ex) {
            System.out.println("getProperty(object,string)" + ex.toString());
        }

        return null;
    }

    public static Method findMethod(Class cls, String methodName) {
        try {
            Method method = cls.getMethod(methodName, (Class[]) null);

            if (method != null) {
                return method;
            }

            if (cls.getSuperclass() != null) {
                return findMethod(cls.getSuperclass(), methodName);
            }
        } catch (Exception ex) {
            if (cls.getSuperclass() != null) {
                return findMethod(cls.getSuperclass(), methodName);
            }
        }

        return null;
    }

    public Class getPropertyType(Object obj, String propertyname) {
        String name = "get" + ilkKarakterBuyut(propertyname);

        try {
            Method method = getMethod(obj.getClass(), name);

            if (method != null) {
                return method.getReturnType();
            }
        } catch (Exception ex) {
            System.out.println("getPropertyType(object,string)" +
                ex.toString());
        }

        return null;
    }

    public static Object setProperty(Object obj, String propertyname,
        String value) {
        String name = "set" + ilkKarakterBuyut(propertyname);

        try {
            Method method = getMethod(obj.getClass(), name);
            Object[] props = new Object[] { value };

            if (method != null) {
                method.invoke(obj, props);

                return obj;
            }
        } catch (Exception ex) {
            System.out.println("setProperty(object,string,string):" +
                ex.toString());
        }

        return null;
    }

    public static Object setProperty(Object obj, String propertyname,
        Object value) {
        String name = "set" + ilkKarakterBuyut(propertyname);

        try {
            Method method = getMethod(obj.getClass(), name);
            Object[] props = new Object[] { value };

            if (method != null) {
                method.invoke(obj, props);

                return obj;
            }
        } catch (Exception ex) {
            System.out.println("setProperty(object,string,object):" +
                ex.toString());
        }

        return null;
    }

    public static void transfer(Object from, Object to) {
        Field[] fields = from.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            try {
                setProperty(to, field.getName(),
                    getPropertyWithFastGetMethod(from, field.getName()));
            } catch (Exception ex) {
                System.out.println("Transfer edilemeyen saha : " +
                    field.getName());
            }
        }
    }

    public static boolean equals(Object from, Object to) {
        Field[] fromFields = from.getClass().getDeclaredFields();
        boolean sonuc = true;
        int i = 0;

        try {
            for (i = 0; (i < fromFields.length) && sonuc; i++) {
                String fieldName = fromFields[i].getName();
                sonuc = checkEquals(getPropertyWithFastGetMethod(from, fieldName),
                        getPropertyWithFastGetMethod(to, fieldName));
            }
        } catch (Exception ex) {
            System.out.println("Saha Problemi : " + fromFields[i].getName());
            sonuc = false;
        }

        return sonuc;
    }

    private static boolean checkEquals(Object fromResult, Object toResult) {
        boolean returnValue = false;

        if ((fromResult == null) && (toResult != null)) {
            returnValue = false;
        }

        if ((fromResult != null) && (toResult == null)) {
            returnValue = false;
        }

        if ((fromResult == null) && (toResult == null)) {
            returnValue = true;
        } else {
            returnValue = fromResult.toString().equals(toResult.toString());
        }

        return returnValue;
    }

    public static String ilkKarakterBuyut(String string) {
        return string.substring(0, 1).toUpperCase(Locale.ENGLISH) +
        string.substring(1);
    }
}
