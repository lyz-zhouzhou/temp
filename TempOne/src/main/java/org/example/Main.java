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
    public static void main(String[] args) {
        downImage("校花",100);
    }
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void downImage(String keyword, int page) {

//        String url = "https://image.baidu.com/search/acjson?tn=resultjson_com&logid=10660754132115598609&ipn=rj&ct=201326592&is=&fp=result&" +
//                "queryWord="+keyword+"&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&hd=&latest=&copyright=&" +
//                "word="+keyword+"&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=&fr=&expermode=&force=&" +
//                "pn="+page+"&rn=30&gsm=5a&1606053649620=";

        String result = restTemplate.getForObject(new BaiDuPageUrl(keyword, page).toString(), String.class);
        String path = "/Users/laiyangzhou/IdeaProjects/Temp/TempOne/picture/百度图片";
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONArray array = jsonObject.getJSONArray("data");

        for (int i = 0; i < array.size(); i++) {
            JSONObject o = array.getJSONObject(i);
            try {
                download(o.getString("hoverURL"), path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(o.getString("middleURL"));
        }

        System.out.println(result);
    }

    private static void sop(Object obj) {
        System.out.println(obj);
    }

    private static void download(String url, String path) {
        //path = path.substring(0,path.length()-2);
        File file = null;
        File dirFile = null;
        FileOutputStream fos = null;
        HttpURLConnection httpCon = null;
        URLConnection con = null;
        URL urlObj = null;
        InputStream in = null;
        byte[] size = new byte[1024];
        int num = 0;
        try {
            String downloadName = url.substring(url.lastIndexOf("/") + 1);
            dirFile = new File(path);
            if (!dirFile.exists() && path.length() > 0) {
                if (dirFile.mkdir()) {
                    sop("creat document file \"" + path.substring(0, path.length() - 1) + "\" success...\n");
                }
            } else {
                file = new File(path + downloadName);
                fos = new FileOutputStream(file);
                if (url.startsWith("http")) {
                    urlObj = new URL(url);
                    con = urlObj.openConnection();
                    httpCon = (HttpURLConnection) con;
                    in = httpCon.getInputStream();
                    while ((num = in.read(size)) != -1) {
                        for (int i = 0; i < num; i++) {
                            fos.write(size[i]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | NullPointerException notFoundE) {
            sop("找不到该网络图片....");
        } catch (IOException ioE) {
            sop("产生IO异常.....");
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
    }
}