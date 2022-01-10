package com.lixing.lixingdemo.supplier;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BankBusinessHandler {
    private void getNumber(String nubmer) {
        if(nubmer.startsWith("vip")) {
            System.out.println("VipnumberNo:20210827" + (int) (Math.random() * 10000));
        }else {
            System.out.println("numberNo:20210827" + (int) (Math.random() * 10000));
        }
    }

    private void judge() {
        System.out.println("give a praised");
    }

    public void save(BigDecimal bigDecimal) {
        execute(()->"",a -> System.out.println("save" + bigDecimal));
    }

    public void saveVip(BigDecimal decimal) {
        execute(() -> "vip", a -> System.out.println("saveVip" + decimal));
    }

    private void execute(Supplier<String> str,Consumer<BigDecimal> consumer) {
        String number = str.get();
        getNumber(number);
        consumer.accept(null);
        judge();
    }

    public static void main(String[] args) {
        BankBusinessHandler bbh = new BankBusinessHandler();
        bbh.saveVip(new BigDecimal("1000"));

        bbh.save(new BigDecimal("5000"));
    }
}
