package com.tuan.springsercurity.dtos.response;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginResponse")
public class LoginResponse {
    private String result;

    @XmlElement(name = "Result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
