package com.gara.springcloudconfigcommon.config;

import springfox.documentation.service.Contact;

/**
 * Contact类属性没有Setter方法，无法直接通过配置文件注入属性值。通过ContactBuilder类注入属性值，再构造Contact类。
 *
 */
public class ContactBuilder {

    private String name;
    private String url;
    private String email;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact build() {
        return new Contact(this.name, this.url, this.email);
    }

}
