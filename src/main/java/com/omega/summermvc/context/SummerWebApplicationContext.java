package com.omega.summermvc.context;

import com.omega.summermvc.annotation.Controller;
import com.omega.summermvc.util.XMLParserUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class SummerApplicationContext
 *
 * @author KennySo
 * @date 2024/7/9
 */
public class SummerWebApplicationContext {

    private final String configLocation;

    private final List<String> classFullPathList = new ArrayList<>();

    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();


    public SummerWebApplicationContext(String configLocation) {
        this.configLocation = configLocation;
    }

    /**
     * 测试 init() 方法时, 不能使用 Junit 来测试, 需要启动 Tomcat 来测试
     * (因为 Junit 测试时, 测试不到 Target 文件夹下的内容)
     */
    public void init() {
        // 获取需要自动扫描包
        String basePackage = XMLParserUtils.getBasePackage(configLocation);
        String[] basePackages = basePackage.split(",");
        for (String pack : basePackages) {
            // 对包进行扫描
            scanPackage(pack);
        }

        // 初始化 singletonObjects
        initSingletonObject();

    }

    /**
     * 扫描包下的 java 文件, 并放入 classNameList 中
     */
    public void scanPackage(String pack) {
        String directoryPath = SummerWebApplicationContext.class
                .getResource("/" + pack.replaceAll("\\.", "/")).getPath();

        File directoryFile = new File(directoryPath);
        File[] fileList = directoryFile.listFiles();
        if (fileList == null) {
            return;
        }
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 如果是文件夹, 则进行递归
                scanPackage(pack + "." + file.getName());
            } else {
                // 判断是否 class 文件
                if (file.getName().endsWith(".class")) {
                    // 添加文件的全路径
                    classFullPathList.add(pack + "." + file.getName().split("\\.")[0]);
                }
            }
        }
    }

    /**
     * 将扫描到的类, 在满足条件的情况下, 实例化并加入 ioc 容器中
     */
    public void initSingletonObject() {
        try {
            for (String classFullPath : classFullPathList) {
                Class<?> clazz = Class.forName(classFullPath);
                if (clazz.isAnnotationPresent(Controller.class)) {
                    String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
                    singletonObjects.put(beanName, clazz.newInstance());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ConcurrentHashMap<String, Object> getSingletonObjects() {
        return singletonObjects;
    }
}
