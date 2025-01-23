package com.lixing.lixingdemo.DesignPatterns.Status;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2025/1/2
 */
public enum StatusEnum {
    UNPAYED{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(PAYED);
        }
    },
    PAYED{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(DELIVERED);
        }
    },
    DELIVERED{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(FINISHED);
        }
    },
    FINISHED{
        @Override
        public void nextStatus(Order order) {
            order.setStatus(FINISHED);
        }
    };




    public abstract void nextStatus(Order order);
}
