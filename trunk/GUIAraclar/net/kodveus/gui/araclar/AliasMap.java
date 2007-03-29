package net.kodveus.gui.araclar;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

public class AliasMap implements Serializable {

    private static final long serialVersionUID = 1L;
    public final String ILISKI_HATA = "Nesneler arasi iliski icin girilen verilerde hata";
    private Class attended;
    private ArrayList<String> uniqueFields;
    private ArrayList<String> alias;
    private ArrayList<String> attributes;
    private ArrayList<String> notNullFields;
    private ArrayList<String> longFields;
    private Hashtable<Class,String> relatedObjects;
    private String idFieldName;
    private String foreignKeyValueField;
    private String foreignKeyPropName;
    private String foreignAlias;
    private Class foreignClass;

    public AliasMap(Class cls) {
        this.attended = cls;
        this.alias = new ArrayList<String>();
        this.attributes = new ArrayList<String>();
        this.uniqueFields = new ArrayList<String>();
        this.notNullFields = new ArrayList<String>();
        this.longFields = new ArrayList<String>();
    }

    public void clearMap() {
        this.alias = new ArrayList<String>();
        this.attributes = new ArrayList<String>();
    }

    public void addAlias(String alias, String attribute)
        throws Exception {
        String first = attribute.substring(0, 1);
        String second = attribute.substring(1, attribute.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        if (this.attended.getMethod(name, (Class[]) null) == null) {
            throw new Exception(this.attended.getName() + " sinifinda " + name +
                " metodu bulunamadi.");
        }

        this.alias.add(alias);
        this.attributes.add(attribute);
    }

    public void addAlias(String alias, String attribute, boolean notNull)
        throws Exception {
        this.addAlias(alias, attribute);

        if (notNull) {
            this.addNotNullField(attribute);
        }
    }

    public Object setValueOfAttribute(Object obj, String alias, Object value) {
        int i = this.alias.indexOf(alias);
        String attribute = (String) this.attributes.get(i);

        try {
            ClassParser.setProperty(obj, attribute, (String) value);
        } catch (Exception ex) {
        }

        return obj;
    }

    public Object setValueOfAttributeWithName(Object obj, String name,
        Object value) {
        try {
            ClassParser.setProperty(obj, name, (String) value);
        } catch (Exception ex) {
        }

        return obj;
    }

    public Object getValueOfAttribute(Object obj, String alias)
        throws Exception {
        int i = this.alias.indexOf(alias);
        String attribute = (String) this.attributes.get(i);
        String first = attribute.substring(0, 1);
        String second = attribute.substring(1, attribute.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        return obj.getClass().getMethod(name, (Class[]) null).invoke(obj,
            (Object[]) null);
    }

    public Class getTypeOfAttribute(String alias) throws Exception {
        int i = this.alias.indexOf(alias);
        String attribute = (String) this.attributes.get(i);
        String first = attribute.substring(0, 1);
        String second = attribute.substring(1, attribute.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;
        Method method = this.attended.getMethod(name, (Class[]) null);

        return method.getReturnType();
    }

    public String getAttributeName(String alias) {
        int i = this.alias.indexOf(alias);

        return (String) this.attributes.get(i);
    }

    public Object getValueOfAttributeWithName(Object obj, String attribute)
        throws Exception {
        String first = attribute.substring(0, 1);
        String second = attribute.substring(1, attribute.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        return obj.getClass().getMethod(name, (Class[]) null).invoke(obj,
            (Object[]) null);
    }

    public int getAliasCount() {
        return this.alias.size();
    }

    public String getAlias(int at) {
        return (String) alias.get(at);
    }

    public String getAlias(String prop) {
        if (!this.attributes.contains(prop)) {
            return null;
        }

        int i = this.attributes.indexOf(prop);

        return this.getAlias(i);
    }

    public void setUniqueField(String propertyname) throws Exception {
        String first = propertyname.substring(0, 1);
        String second = propertyname.substring(1, propertyname.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        checkIfExists(name);

        this.uniqueFields.add(propertyname);
    }

    public void addNotNullField(String propertyname) throws Exception {
        String first = propertyname.substring(0, 1);
        String second = propertyname.substring(1, propertyname.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        checkIfExists(name);

        this.notNullFields.add(propertyname);
    }

    public void addLongField(String propertyname) throws Exception {
        String first = propertyname.substring(0, 1);
        String second = propertyname.substring(1, propertyname.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        checkIfExists(name);

        this.longFields.add(propertyname);
    }

    public boolean isLong(String prop) {
        if (longFields.indexOf(prop) < 0) {
            return false;
        }

        return true;
    }

    public boolean isNotNull(String prop) {
        if (notNullFields.indexOf(prop) < 0) {
            return false;
        }

        return true;
    }

    public ArrayList<String> getUniqueFields() {
        return this.uniqueFields;
    }

    public void setIdFieldName(String idFieldName) throws Exception {
        String first = idFieldName.substring(0, 1);
        String second = idFieldName.substring(1, idFieldName.length());
        String name = "get" + first.toUpperCase(Locale.ENGLISH) + second;

        checkIfExists(name);

        this.idFieldName = idFieldName;
    }

    private void checkIfExists(String name)
        throws NoSuchMethodException, Exception {
        if (this.attended.getMethod(name, (Class[]) null) == null) {
            throw new Exception(this.attended.getName() + " sinifinda " + name +
                " methodu bulunamadi.");
        }
    }

    public String getIdFieldName() {
        return this.idFieldName;
    }

    public static Class getFieldType(Object dvo, String attName) {
        try {
            Field field = dvo.getClass().getDeclaredField(attName);

            return field.getType();
        } catch (Exception ex) {
            return null;
        }
    }

    public void addRelation(String foreignField, Class relatedObject,
        String valueField, String alias) throws Exception {
        if ((foreignField == null) || (relatedObject == null)) {
            throw new Exception(ILISKI_HATA);
        }

        this.foreignClass = relatedObject;
        this.foreignKeyPropName = foreignField;
        this.foreignKeyValueField = valueField;
        this.foreignAlias = alias;
    }

    public String getForeignKeyPropName() {
        return this.foreignKeyPropName;
    }

    public String getForeignAlias() {
        return this.foreignAlias;
    }

    public Class getForeignPropType() {
        return this.foreignClass;
    }

    public String getForeignKeyValueField() {
        return this.foreignKeyValueField;
    }

    public void addRelatedObjects(Class cls, String field) {
        if (relatedObjects == null) {
            relatedObjects = new Hashtable<Class,String>();
        }

        relatedObjects.put(cls, field);
    }

    public Hashtable getRelatedObjects() {
        if (relatedObjects == null) {
            return new Hashtable();
        }
        return this.relatedObjects;
    }
}
