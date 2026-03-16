//package com.Draw_App.service.util;
//
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Paths;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.security.CodeSource;
//import java.security.ProtectionDomain;
//
//public class PathFinder {
//    //сначала сделал переменные типа Path но потом всё же переделал
//    // в Стринг (как в исходнике), всё равно потом оперировать Стрингом, но как лучше? Стринг иммутабельный, это точно +.
//    String decodedPath;
//    String jarFilePath;
//    String directoryJarPath;
//
//    public PathFinder() {
//    }
//
//    public String getDirectoryJarPath() {
//
//        ProtectionDomain pd = PathFinder.class.getProtectionDomain();
//        System.out.println(pd);
//        CodeSource cs = pd.getCodeSource();
//        System.out.println(cs);
//        URL url = cs.getLocation();
//        System.out.println(url);
//        try {
//            decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
//            jarFilePath = Paths.get(decodedPath).toString();
//            directoryJarPath = Paths.get(jarFilePath).getParent().toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return directoryJarPath;
//
//
//    }
//
//}