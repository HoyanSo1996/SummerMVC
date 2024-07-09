package com.omega.summermvc.util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Class XMLParserUtils
 *
 * @author KennySo
 * @date 2024/7/9
 */
public class XMLParserUtils {

    public static String getBasePackage(String xmlFilePath) {
        InputStream inputStream = XMLParserUtils.class.getClassLoader().getResourceAsStream(xmlFilePath);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element componentScanElement = rootElement.element("component-scan");
            Attribute attribute = componentScanElement.attribute("base-package");
            return attribute.getValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
