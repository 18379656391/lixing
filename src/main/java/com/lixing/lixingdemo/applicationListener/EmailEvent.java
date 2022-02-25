package com.lixing.lixingdemo.applicationListener;

import org.springframework.context.ApplicationEvent;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 11:01:57
 */
public class EmailEvent extends ApplicationEvent {
    private String address;
    private String text;

    public EmailEvent(Object source, String address, String text) {
        super(source);
        this.address = address;
        this.text = text;
    }

    public EmailEvent(Object source) {
        super(source);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
