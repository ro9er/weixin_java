package com.xlsd.wx.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.xlsd.wx.annotation.XStreamCDATA;
import com.xlsd.wx.model.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 实现消息的格式转换(Map类型和XML的互转)
 */
public class MessageUtil {

    private final static XStream xStream = createXstream();

    /**
     * 将XML转换成Map集合
     */
    public static Map<String, String>xmlToMap(String body){
        try{
            Map<String, String> map = new HashMap<String, String>();
            SAXReader reader = new SAXReader();            // 使用dom4j解析xml
            Document doc = reader.read(new ByteArrayInputStream(body.getBytes("UTF-8")));

            Element root = doc.getRootElement();         // 获取根元素
            List<Element> list = root.elements();        // 获取所有节点

            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            return map;
        }catch (Exception e){
            //
            return new HashMap<>();
        }
    }

    /**
     * 将文本消息对象转换成XML
     */
    public static String textMessageToXML(TextMessage textMessage){

        //xStream.alias("xml", textMessage.getClass()); // 将xml的默认根节点替换成“xml”
        //xStream.processAnnotations(textMessage.getClass());
        return xStream.toXML(textMessage);

    }


    public static XStream createXstream() {
        XStream xStream =  new XStream(new XppDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    boolean cdata = false;
                    Class<?> targetClass = null;
                    @Override
                    public void startNode(String name,
                                          @SuppressWarnings("rawtypes") Class clazz) {
                        super.startNode(name, clazz);
                        //业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签
                        if(!name.equals("xml")){
                            cdata = needCDATA(targetClass, name);
                        }else{
                            targetClass = clazz;
                        }
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (cdata) {
                            writer.write(cDATA(text));
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        xStream.autodetectAnnotations(true);
        return xStream;
    }

    private static String cDATA(String rawText){
        return "<![CDATA[" + rawText + "]]>";
    }
    private static boolean existsCDATA(Class<?> clazz, String fieldAlias){
        //scan fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //1. exists XStreamCDATA
            if(field.getAnnotation(XStreamCDATA.class) != null ){
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                //2. exists XStreamAlias
                if(null != xStreamAlias){
                    if(fieldAlias.equals(xStreamAlias.value()))//matched
                        return true;
                }else{// not exists XStreamAlias
                    if(fieldAlias.equals(field.getName()))
                        return true;
                }
            }
        }
        return false;
    }
    private static boolean needCDATA(Class<?> targetClass, String fieldAlias){
        boolean cdata = false;
        //first, scan self
        cdata = existsCDATA(targetClass, fieldAlias);
        if(cdata) return cdata;
        //if cdata is false, scan supperClass until java.lang.Object
        Class<?> superClass = targetClass.getSuperclass();
        while(!superClass.equals(Object.class)){
            cdata = existsCDATA(superClass, fieldAlias);
            if(cdata) return cdata;
            superClass = superClass.getClass().getSuperclass();
        }
        return false;
    }


}