package org.example.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class BaiDuPageUrl {
    private String queryWord;
    private int pn;
    private String gsm;

    public BaiDuPageUrl(String queryWord, int pn) {
        // 对查询关键字进行url编码
        try {
            this.queryWord = URLEncoder.encode(queryWord, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.pn = pn;
        this.gsm = Integer.toHexString(pn);
    }

    // pn 和 gsm 应该是一起变化的
    public void setPn(int pn) {
        this.pn = pn;
        this.gsm = Integer.toHexString(pn);
    }

    @Override
    public String toString() {
//        return "https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord="
//                + queryWord + "&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&z=&ic=&hd=&latest=&copyright=&word="
//                + queryWord + "&s=&se=&tab=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&expermode=&force=&pn="
//                + pn+ "&rn=30&gsm=" + gsm + "&" + new Date().getTime() + "=";
        return  "https://image.baidu.com/search/acjson?tn=resultjson_com&logid=10660754132115598609&ipn=rj&ct=201326592&is=&fp=result&" +
                "queryWord="+queryWord+"&cl=2&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=&z=&ic=&hd=&latest=&copyright=&" +
                "word="+queryWord+"&s=&se=&tab=&width=&height=&face=&istype=&qc=&nc=&fr=&expermode=&force=&" +
                "pn="+pn+"&rn=30&gsm="+ gsm +"&" + new Date().getTime() + "=";
    }
}
