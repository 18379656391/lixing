package com.lixing.lixingdemo.volatileDemo;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-15 15:25
 * 研究多线程下变量访问的不可见性现象
 * 准备两个线程
 * 定义一个成员变量
 * 开启两个线程，其中一个线程负责修改，另一个负责读取
 */
public class VisibilityDemo {
    // 主线程
    public static void main(String[] args) {
        FlagDemo demo = new FlagDemo();
        // 开启子线程
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            demo.setFlag(true);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

        // 主线程
        while (true) {
            if (demo.isFlag()) {
                System.out.println("主线程进入循环执行···");
            } else {
                System.out.println("flag is false");
            }
        }
    }
}


class FlagDemo {
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
