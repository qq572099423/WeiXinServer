package com.weixinserver.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ParseXML {
  /**
   * 根据xml文本构建实体类对象
   * 
   * @param clazz 实体类
   * @param xmlText xml文本
   * @return 返回实体类对象
   */
  public static <T> T parseXML2Obj(Class<T> clazz, String xmlText) {
    T t = null;
    try {
      // 构建xml文档
      Document doc = DocumentHelper.parseText(xmlText);
      // 获取root节点
      Element root = doc.getRootElement();
      Iterator<?> iter = root.elementIterator();
      // 得到实体类的实例
      t = clazz.newInstance();
      // 遍历xml
      while (iter.hasNext()) {
        Element elec = (Element) iter.next();
        try {
          // 得到属性
          Field field = clazz.getDeclaredField(elec.getName());
          // 得到set方法
          Method method = clazz.getDeclaredMethod("set" + elec.getName(), field.getType());
          // 通过set方法赋值
          method.invoke(t, elec.getData());
        } catch (NoSuchFieldException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (IllegalArgumentException e) {
          e.printStackTrace();
        }

      }
    } catch (DocumentException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    return t;
  }

  public static <T> String formatObject2XML(T t) {
    Class<?> c = t.getClass();
    Field[] fields = c.getDeclaredFields();
    StringBuilder sb = new StringBuilder();
    sb.append("<xml>");
    for (Field field : fields) {
      Method method;
      try {
        method = c.getDeclaredMethod("get" + field.getName());
        if (null == method.invoke(t)) {
          continue;
        }
        sb.append("<" + field.getName() + "><![CDATA[");
        sb.append(method.invoke(t));
        sb.append("]]></" + field.getName() + ">");

      } catch (NoSuchMethodException | SecurityException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
    sb.append("</xml>");
    return sb.toString();
  }
}
