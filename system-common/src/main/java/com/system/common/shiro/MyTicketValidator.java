package com.system.common.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.model.UserVO;
import com.common.property.Property;
import com.common.util.JsonUtils;
import org.jasig.cas.client.authentication.AttributePrincipalImpl;
import org.jasig.cas.client.validation.AbstractCasProtocolUrlBasedTicketValidator;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.AssertionImpl;
import org.jasig.cas.client.validation.TicketValidationException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author 陈葳
 * @Date 2019/4/10 17:13
 * @Version 1.0
 */
@Component
public class MyTicketValidator extends AbstractCasProtocolUrlBasedTicketValidator {

    protected MyTicketValidator() {
        super(Property.getProperty("server.sso.serverUrlPrefix"));
    }

    @Override
    protected String getUrlSuffix() {
        return "validate";
    }

    @Override
    protected Assertion parseResponseFromServer(String response) throws TicketValidationException {
        try {
            JSONObject json = JSON.parseObject(response);
            if (json.getInteger("status") == 200) {
                String data = json.getString("data");
                UserVO user = JsonUtils.jsonToPojo(data, UserVO.class);
                Map<String, Object> map = new HashMap<>();
                map.put("user", user);
                return new AssertionImpl(new AttributePrincipalImpl(user.getLoginName(), map));
            } else {
                throw new TicketValidationException(json.getString("msg"));
            }
        } catch (TicketValidationException e) {
            throw new TicketValidationException(e.getMessage());
        }
    }
}