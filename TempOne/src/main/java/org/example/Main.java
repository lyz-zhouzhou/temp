package org.example;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.example.entity.BaiDuPageUrl;
import org.example.entity.JsonText;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        System.out.println("Hello world!");
        downLoadBaiduImage();
    }

    private static void downLoadBaiduImage() {
        //String result = restTemplate.getForObject(new BaiDuPageUrl("校花", 100).toString(), String.class);
        JSONObject parseObject = JSONObject.parseObject(JsonText.jsonText());
        JSONArray jsonArray = parseObject.getJSONArray("data");
        System.out.println(jsonArray);
        //System.out.println(result);
        String filePath = "/Users/laiyangzhou/IdeaProjects/Temp/TempOne/picture/百度图片/";
        //JSONObject jsonObject = JSONObject.parseObject(result);
        //JSONArray array = jsonObject.getJSONArray("data");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray replaceUrl = jsonObject.getJSONArray("replaceUrl");
            System.out.println(replaceUrl);
            JSONObject imageobject = replaceUrl.getJSONObject(0);
            String imageUrl = imageobject.getString("ObjURL");
            //JSONObject o = array.getJSONObject(i);
            //String url = o.getString("hoverURL");
            try {
                //path = path.substring(0,path.length()-2);
                File file;
                File dirFile;
                FileOutputStream fos = null;
                HttpURLConnection httpCon;
                URLConnection con;
                URL urlObj;
                InputStream in;
                byte[] size = new byte[1024];
                int num = 0;
                try {
                    String downloadName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                    dirFile = new File(filePath);
                    if (!dirFile.exists()) {
                        if (dirFile.mkdir()) {
                            System.out.println("文件夹创建成功");
                        }
                    } else {
                        file = new File(filePath + downloadName);
                        fos = new FileOutputStream(file);
                        if (imageUrl.startsWith("http")) {
                            urlObj = new URL(imageUrl);
                            con = urlObj.openConnection();
                            httpCon = (HttpURLConnection) con;
                            in = httpCon.getInputStream();
                            while ((num = in.read(size)) != -1) {
                                for (int j = 0; j < num; j++) {
                                    fos.write(size[j]);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        assert fos != null;
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(o.getString("middleURL"));
        }
        //System.out.println(result);
    }
}