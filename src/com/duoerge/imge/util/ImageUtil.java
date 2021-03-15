package com.duoerge.imge.util;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName : ImageUtil  //类名
 * @Description : 返回图片链接  //描述
 * @Author : Program_Dog  //作者
 * @Date: 2020-09-16 21:54  //时间
 */
public class ImageUtil {

    //	读文件
    public static List<String> InputFile(){
//        图片链接属性
        String msgUrl = null;
//        图片链接集合
        List<String> msgUrls = new ArrayList<String>();
        try {
//            读取放着图片链接的文档文件
            BufferedReader br = new BufferedReader(new InputStreamReader(ImageUtil.class.getClassLoader().getResourceAsStream("ImgesUrl.txt")));
//            把读取到的图片链接放入到图片集合里面
            while((msgUrl = br.readLine()) != null) {
                msgUrls.add(msgUrl);
            }
//            关闭流
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        返回图片链接集合
        return msgUrls;
    }

//    读取网络图片信息
    public static void getFileInfo(String filePath) {
        int HttpResult; // 服务器返回的状态
        try {
            URL url =new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn =(HttpURLConnection)urlconn;
            HttpResult = httpconn.getResponseCode();
            if(HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                Metadata metadata = ImageMetadataReader.readMetadata(urlconn.getInputStream());
                for (Directory directory : metadata.getDirectories()) {
                    for (Tag tag : directory.getTags()) {
    //                        System.out.println(tag);
                        if(tag.getTagName().equals("Image Width")) {
                            String a = tag.getDescription();
                            String regEx="[^0-9]";
                            Pattern p = Pattern.compile(regEx);
                            Matcher m = p.matcher(a);
                            System.out.println("图片的宽度是:"+tag.getDescription());
                            System.out.println( m.replaceAll("").trim());
                        }
                        if(tag.getTagName().equals("Image Height")) {
                            String a = tag.getDescription();
                            String regEx="[^0-9]";
                            Pattern p = Pattern.compile(regEx);
                            Matcher m = p.matcher(a);
                            System.out.println("图片的高度是:"+tag.getDescription());
                            System.out.println( m.replaceAll("").trim());
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageProcessingException e) {
            e.printStackTrace();
        }
    }
}
