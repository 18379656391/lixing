package com.lixing.lixingdemo.juc.AtomicReference;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/27
 * @desc 个人银行账号类
 * 约定：
 * 1.个人账号被设计为不可变对象，一旦创建就无法进行修改
 * 2.个人账号类只能包含两个字段，账号名，现金数字
 * 3.为了便与验证，约定个人账号的现金只能增多不能减少
 */
public class DebitCard {

    /**
     * 账户名
     */
    private final String account;

    /**
     * 账户金额
     */
    private final int amount;

    public DebitCard(String account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "account='" + account + '\'' +
                ", amount=" + amount +
                '}';
    }
}
