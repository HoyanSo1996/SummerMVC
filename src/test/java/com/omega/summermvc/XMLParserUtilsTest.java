package com.omega.summermvc;

import com.omega.summermvc.util.XMLParserUtils;
import org.junit.jupiter.api.Test;

/**
 * Class SummerMVCTest
 *
 * @author KennySo
 * @date 2024/7/9
 */
public class XMLParserUtilsTest {

    @Test
    public void testGetBasePackage() {
        String basePackage = XMLParserUtils.getBasePackage("summerApplicationContext.xml");
        System.out.println("basePackage = " + basePackage);
    }
}
