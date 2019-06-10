package com.system.facade.service;

import com.system.facade.vo.RespondVO;

/**
 * @Description 文件下载及参数文件下载服务层接口
 * @Author 姚奕锋
 * @Date 2018/12/21 13:56
 * @Version 1.0
 */
 
public interface DownFileService {

	/**
	 * 下载文件
	 * @param fileName
	 * @param num
	 * @param writeDiskContent
	 * @param writeClientContent
	 * @param gizp
	 * @param writeContentType
	 * @return
	 */
	RespondVO downloadFile(String fileName, String num, byte[] writeDiskContent, byte[] writeClientContent, boolean gizp, String writeContentType);

	/**
	 * 下载参数文件
	 * @param filePath
	 * @param writeClientContent
	 * @param gizp
	 * @param writeContentType
	 * @return
	 */
	RespondVO downloadParamFile(String filePath, byte[] writeClientContent, boolean gizp, String writeContentType);
}
