package com.lixing.lixingdemo.applicationListener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-18 11:06:44
 */
@Component
public class EmailListener implements ApplicationListener<EmailEvent> {

    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("邮件地址" + event.getAddress());
        System.out.println("邮件内容" + event.getText());
    }
}
