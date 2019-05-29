package com.system.admin.controller;

import com.system.common.server.Server;
import com.system.framework.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 服务器监控
 * @author system
 */
@Controller
public class ServerController extends BaseController {

    @GetMapping("/server")
    public String server(ModelMap map) throws Exception {
        Server server = new Server();
        server.copyTo();
        map.put("server", server);
        return "server";
    }
}
