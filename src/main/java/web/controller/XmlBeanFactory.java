package web.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;


public class XmlBeanFactory {
    Map<String, Controller> beans = new HashMap<>();
    private static Map<String, Object> beans2 = new HashMap<>();


    public Map<String, Controller> getBeans() {
        return beans;

    }

    public static Map<String, Object> getBeans2() {
        return beans2;
    }

    public XmlBeanFactory(String controllerPath, String modelPath) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(modelPath, new MyDefaultHandler2());
        parser.parse(controllerPath, new MyDefaultHandler());
    }

    class MyDefaultHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("bean".equals(qName)) {
                String id = attributes.getValue("id");
                String className = attributes.getValue("class");
                try {
                    Constructor c = Class.forName(className).getConstructor();
                    Controller o = (Controller) c.newInstance();
                    beans.put(id, o);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    class MyDefaultHandler2 extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ("bean".equals(qName)) {
                String id = attributes.getValue("id");
                String className = attributes.getValue("class");
                try {
                    Constructor c = Class.forName(className).getConstructor();
                    Object o = c.newInstance();
                    beans2.put(id, o);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

}
