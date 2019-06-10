package com.system.facade.exception.code;

/**
 * @ClassName ErrorCode
 * @Description 服务层异常基类，异常代码格式[01][001]<br/>
 * 	后三位代表错误码编号00：系统异常 03:服务层异常<br/>
 * @Author ChungFung
 * @Date 2018\11\13 0008 10:10
 * @Version 1.0
 */

public class ErrorCode {

	/** 字典值已经存在 */
	public static final String CODE_03001 = "03001";

	/** {0}已分配,不能删除 */
	public static final String CODE_03002 = "03002";
}
