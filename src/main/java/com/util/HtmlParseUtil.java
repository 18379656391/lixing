package com.util;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-24 15:50
 */

public class HtmlParseUtil {

    @SneakyThrows
    public void searchGoods(String keyWord){
        // 获取请求,需要联网
        String url = "https://search.jd.com/Search?keyword=" + keyWord;
        // 解析网页(Jsoup返回的Document就是浏览器的Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        Elements li = element.getElementsByTag("li");
        //System.out.println(li.html());
        for (Element e : li) {
            if (!e.attr("class").equalsIgnoreCase("ps-item")) {
                // System.out.println(e.html());
                // 直接获取url获取不到，图片特别多的网站都是采用懒加载的方式加载图片
                String imgUrl = e.getElementsByTag("img").eq(0).attr("data-lazy-img");
                String prdTitle = e.getElementsByClass("p-name").eq(0).text();
                String prdPrice = e.getElementsByClass("p-price").eq(0).text();
                System.out.println("产品名称" + prdTitle);
                System.out.println("产品价格" + prdPrice);
                System.out.println("产品图片" + imgUrl);
                System.out.println("------------------------------------");
            }
        }
    }

    public static void main(String[] args) {

    }
}
