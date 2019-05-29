package com.system.framework.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description
 * @Author 丰涌
 * @Date 2018/12/21 17:44
 * @Version 1.0
 */
public class BaseController {
	
	public static final String CODE_200 = "200";
	public static final String CODE_300 = "300";
	
	public static final String statusCode = "statusCode";
	public static final String message = "message";

	public ModelAndView pageView(String viewName) {
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	/**
	 * 返回json数据，并带上业务数据
	 * @param paramKeyAndVal [key,val,key,val ...]格式
	 * @return
	 */
	public static ModelAndView openJsonView(Object ...paramKeyAndVal) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject(statusCode, CODE_200);
		mav.addObject(message, "操作成功!");
		for (int i = 0; i < paramKeyAndVal.length; i=i+2) {
			mav.addObject(paramKeyAndVal[i].toString(), paramKeyAndVal[i+1]);
		}
	    return mav;
	}

	public static ModelAndView errorJsonView(Object ...paramKeyAndVal) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject(statusCode, CODE_300);
		mav.addObject(message, "操作失败!");
		for (int i = 0; i < paramKeyAndVal.length; i=i+2) {
			mav.addObject(paramKeyAndVal[i].toString(), paramKeyAndVal[i+1]);
		}

		return mav;
	}

	public ModelAndView jsonView() {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("statusCode", CODE_200);
		mav.addObject("message", "操作成功！");
		mav.addObject("navTabId", "");
		mav.addObject("rel", "");
		mav.addObject("callbackType","");
		mav.addObject("forwardUrl", "");
	    return mav; 
	}
	
	public ModelAndView jsonView(String msg) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("statusCode", msg==null?CODE_200:CODE_300);
		mav.addObject("message", msg==null?"操作成功！":msg);
		mav.addObject("navTabId", "");
		mav.addObject("callbackType","");
		mav.addObject("forwardUrl", "");
	    return mav; 
	}
	
	public ModelAndView jsonView(String msg, boolean closeCurrent) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("statusCode", msg==null?CODE_200:CODE_300);
		mav.addObject("message", msg==null?"操作成功！":msg);
		mav.addObject("navTabId", "");
		mav.addObject("callbackType",closeCurrent?"closeCurrent":"");
		mav.addObject("forwardUrl", "");
	    return mav; 
	}
	
	public ModelAndView jsonView(String msg, String navTabId, String rel, boolean closeCurrent) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("statusCode", msg==null?CODE_200:CODE_300);
		mav.addObject("message", msg==null?"操作成功！":msg);
		mav.addObject("navTabId", navTabId==null?"":navTabId);
		mav.addObject("rel", rel==null?"":rel);
		mav.addObject("callbackType",closeCurrent?"closeCurrent":"");
		mav.addObject("forwardUrl", "");
	    return mav; 
	}

	protected ModelAndView ajaxTodo(int statusCode, String message, String forwardUrl) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("forwardUrl", forwardUrl);
		return mav;
	}
	
	protected Map<String, String> getParameter(HttpServletRequest req, String ...fields) {
		Map<String, String> params = new HashMap<String, String>();
	    for (Iterator<String> iter = req.getParameterMap().keySet().iterator(); iter.hasNext(); ) {
	       String strKey = iter.next();
	       Object strObj = req.getParameter(strKey);
	       params.put(strKey, strObj==null?null:strObj.toString());
	    }
	    for (int i = 0; i < fields.length; i++) {
	    	Object value = req.getAttribute(fields[i]);
	    	if(value == null) {
	    		value = req.getSession().getAttribute(fields[i]);
	    	}
	    	params.put(fields[i], value==null?null:value.toString());
		}
	    return params;
	}
}
