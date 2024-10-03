package com.tuan.springsercurity.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

public class Creator {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static ObjectNode createObject()
    {
        return objectMapper.createObjectNode();
    }

    public static Gson createGson()
    {
        return new Gson();
    }

}
