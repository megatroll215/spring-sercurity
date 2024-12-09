package com.tuan.springsercurity.controller;

import com.tuan.springsercurity.dtos.response.LoginResponse;
import com.tuan.springsercurity.model.LoginRequest;
import jakarta.xml.bind.JAXBElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import javax.xml.namespace.QName;

@Endpoint
@Slf4j
public class SoapController {
    private static final String NAMESPACE_URI = "http://example.com/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginRequest")
    @ResponsePayload // Add this to specify that the response is sent back in the SOAP Body
    public LoginResponse  processRequest(@RequestPayload LoginRequest loginRequest,
                                 @SoapHeader("header") SoapHeaderElement header) {
        log.info("header: ", header.getText());
        LoginResponse response = new LoginResponse();
        response.setResult(loginRequest.getUsername()+ ":" + loginRequest.getPassword());
        return response;
    }
}
