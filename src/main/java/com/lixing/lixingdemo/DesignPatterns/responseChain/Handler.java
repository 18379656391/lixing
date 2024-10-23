package com.lixing.lixingdemo.DesignPatterns.responseChain;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 * 责任链模式-链式编程
 */
public abstract class Handler<T> {

    protected Handler next;

    private void next(Handler next) {
        this.next = next;
    }

    public abstract void doHandler(Member member);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;


        public Builder<T> addHandler(Handler handler) {
            if (null == this.head) {
                this.head = this.tail = handler;
            }
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }
}
