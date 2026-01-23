package com.Draw_App.service.util;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class PathFinder {
    //сначала сделал переменные типа Path но потом всё же переделал
    // в Стринг (как в исходнике), всё равно потом оперировать Стрингом, но как лучше? Стринг иммутабельный, это точно +.
    String decodedPath;
    String jarFilePath;
    String directoryJarPath;

    public String getDirectoryJarPath() {

        ProtectionDomain pd = PathFinder.class.getProtectionDomain();
        CodeSource cs = pd.getCodeSource();
        URL url = cs.getLocation();
        try {
            decodedPath = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8);
            jarFilePath = Paths.get(decodedPath).toString();
            directoryJarPath = Paths.get(jarFilePath).getParent().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directoryJarPath;


    }

}

//Исходник:
//String decodedPath = URLDecoder.decode(url.getPath(), "UTF-8");
/// / Если это JAR, то путь будет к самому JAR,
/// / чтобы получить папку, можно использовать Paths.get(decodedPath).getParent().toString();
//String jarFilePath = Paths.get(decodedPath).toString(); // Путь к самому JAR
//String directoryPath = Paths.get(jarFilePath).getParent().toString(); // Путь к папке