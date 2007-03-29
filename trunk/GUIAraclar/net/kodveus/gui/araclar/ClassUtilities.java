package net.kodveus.gui.araclar;

//Bu sinif class uzerinde islem yapan metodlari barindirir
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;

public class ClassUtilities
{
	public static Object getProperty(Object obj ,String propertyname)
    {
		String name="get"+ilkKarakterBuyut(propertyname);
        try{
            Method method = getMethod(obj.getClass(),name);
            if(method != null){
                return method.invoke(obj,(Object[])null);
            }
        }catch(Exception ex){
            System.out.println("getProperty(object,string)"+ex.toString());
        }
		return null;
	}

     public static Object getPropertyWithFastGetMethod(Object obj ,String propertyname)
    {
		String name="get"+ilkKarakterBuyut(propertyname);
        try{
            //Method method = obj.getClass().getMethod(name,null);
            Method method = findMethod(obj.getClass(),name);
            if(method != null){
                return method.invoke(obj,(Object[])null);
            }
        }catch(Exception ex){
            System.out.println("getProperty(object,string)"+ex.toString());
        }
		return null;
	}
    
    public static Method findMethod(Class cls,String methodName){        
        try{
            Method method = cls.getMethod(methodName,(Class[])null);
            if(method != null){
                return method;
            }else{
                if(cls.getSuperclass()!= null){
                    return findMethod(cls.getSuperclass(),methodName);
                }
            }
        }catch(Exception ex){
            if(cls.getSuperclass()!= null){
                    return findMethod(cls.getSuperclass(),methodName);
            }
        }
        return null;
    }


    public static Class getPropertyType(Object obj,String propertyname){
		String name="get"+ilkKarakterBuyut(propertyname);
        try{
            Method method = getMethod(obj.getClass(),name);
            if(method != null){
                return method.getReturnType();
            }
        }catch(Exception ex){
            System.out.println("getPropertyType(object,string)"+ex.toString());
        }
		return null;
	}

	public static Object setProperty(Object obj ,String propertyname , String value)
    {
		String name="set"+ilkKarakterBuyut(propertyname);
        try{
            Method method = getMethod(obj.getClass(),name);
            Object props[] = new Object[]{value};
            if(method != null){
                method.invoke(obj, props);
                return obj;
            }
        }catch(Exception ex){
            System.out.println("setProperty(object,string,string):"+ex.toString());
        }
		return null;
	}

	public static Object setProperty(Object obj ,String propertyname , Object value)
    {
		String name="set"+ilkKarakterBuyut(propertyname);
        try{
            Method method = getMethod(obj.getClass(),name);
            Object props[] = new Object[]{value};
            if(method != null){
                method.invoke(obj, props);
                return obj;
            }
        }catch(Exception ex){
            System.out.println("setProperty(object,string,object):"+ex.toString());
        }
		return null;
	}

	public static Method getMethod(Class parseClass,String name){
		Method methods[] = parseClass.getMethods();
		for(int i = 0;i<methods.length;i++){
			if(methods[i].getName().equals(name)){
				return methods[i];
			}
		}
		return null;
	}

    public static void formKriterleriniTemizle(Object form){
        if(form==null)
            return;
        Field[] sahalar = form.getClass().getDeclaredFields();
        String isim;
        for(int i = 0;i<sahalar.length;i++){
            isim = sahalar[i].getName();
            if(isim.indexOf("SorguKriteri")>=0){
                setProperty(form,isim,null);
            }else
            if(sahalar[i].getType().getName().indexOf("String")>=0){
                setProperty(form,isim,"");
            }
        }
    }

	public static void transfer(Object from , Object to){

		Field fields[] = from.getClass().getDeclaredFields();
		for(int i =  0 ; i<fields.length ; i++){
			Field field = fields[i];
            try{
                setProperty(to,field.getName(),getPropertyWithFastGetMethod(from,field.getName()));
            }catch(Exception ex){
                System.out.println("Transfer edilemeyen saha : "+field.getName());
            }
		}
	}

	public static boolean equals(Object from , Object to){
		Field fromFields[] = from.getClass().getDeclaredFields();
        boolean sonuc = true;
        int i=0;
        try{
            for(i =  0 ; i<fromFields.length && sonuc ; i++){
                String fieldName = fromFields[i].getName();
                sonuc=checkEquals(getPropertyWithFastGetMethod(from,fieldName),getPropertyWithFastGetMethod(to,fieldName));
            }
        }catch(Exception ex){
            System.out.println("Saha Problemi : "+fromFields[i].getName());
            sonuc = false;
        }
        return sonuc;
	}

    private static boolean checkEquals(Object fromResult,Object toResult){
            boolean returnValue = false;
            if(fromResult==null && toResult!=null)
                returnValue = false;
            if(fromResult!=null && toResult==null)
                returnValue = false;
            if(fromResult==null && toResult==null){
                returnValue = true;
            }else{
                returnValue = fromResult.toString().equals(toResult.toString());
            }
            return returnValue;
    }

	private static String ilkKarakterBuyut(String string){
        return string.substring(0,1).toUpperCase(Locale.ENGLISH)+string.substring(1);
    }

}
