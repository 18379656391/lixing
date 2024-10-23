package com.lixing.lixingdemo.DesignPatterns.builder;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-18 16:18
 */
public class BuiderTest {
    public static void main(String[] args) {
        CompanyClient client1 = new CompanyClient.Builder()
                .setCompanyName("恒生电子")
                .setCompanyAddress("诚业路")
                .setCompanyRegfunds(5)
                .setmPerson("40000")
                .setmType("金融行业").build();
        System.out.println("company1:" + client1.toString());

        CompanyClient client2 = new CompanyClient.Builder()
                .setCompanyName("阿里巴巴")
                .setCompanyAddress("杭州阿里园")
                .setCompanyRegfunds(10)
                .setmPerson("100000")
                .setmType("电商").build();
        System.out.println("company2:" + client2.toString());
    }
}
