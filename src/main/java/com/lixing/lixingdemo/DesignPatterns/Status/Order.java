package com.lixing.lixingdemo.DesignPatterns.Status;

import com.google.common.base.Objects;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/1/2
 */
public class Order {

    private StatusEnum status;

    public Order() {
        this.status = StatusEnum.UNPAYED;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void nextStatus() {
        status.nextStatus(this);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("status", status)
                .toString();
    }
}
