package com.system.service.common.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

/**
 * @Description 单点登录返回信息
 * @Author 陈葳
 * @Date 2019/4/10 10:17
 * @Version 1.0
 */
@Data
public class ReturnT {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ReturnT build(Integer status, String msg, Object data) {
        return new ReturnT(status, msg, data);
    }

    public static ReturnT ok(Object data) {
        return new ReturnT(data);
    }

    public static ReturnT ok() {
        return new ReturnT(null);
    }

    public ReturnT() {

    }

    public static ReturnT build(Integer status, String msg) {
        return new ReturnT(status, msg, null);
    }

    public ReturnT(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ReturnT(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    /**
     * 将json结果集转化为ReturnT对象
     * @param jsonData json数据
     * @param clazz    ReturnT中的object类型
     * @return
     */
    public static ReturnT formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ReturnT.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static ReturnT format(String json) {
        try {
            return MAPPER.readValue(json, ReturnT.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static ReturnT formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}