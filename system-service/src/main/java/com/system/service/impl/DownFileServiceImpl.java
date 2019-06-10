package com.system.service.impl;

import com.system.common.utils.StringUtils;
import com.system.facade.service.DownFileService;
import com.system.facade.vo.RespondVO;
import com.system.service.exception.BusinessException;
import com.system.service.exception.code.StatusCodes;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Description 文件下载及参数文件下载服务层接口实现
 * @Author 姚奕锋
 * @Date 2018/12/21 13:57
 * @Version 1.0
 */
 
@Service
public class DownFileServiceImpl implements DownFileService {

	private static Logger logger = LoggerFactory.getLogger(DownFileServiceImpl.class);

	@Override
	public RespondVO downloadFile(String fileName, String num, byte[] writeDiskContent, byte[] writeClientContent, boolean gizp, String writeContentType){

		String contentDisposition;
		String binfileMD5;
		String contentType;
		String contentEncoding;
		Long contentLength;
		byte[] content;
		Integer httpStaus;
		try {
			String resFileSaveName = StringUtils.getResFileName(fileName, num);
			// 组装返回结果,写出文件到客户端
			content = writeClientContent;
			logger.debug("文件[{}]写出大小：{}", resFileSaveName, content.length);
			contentEncoding = gizp ? "gzip" : "identity";
			contentType = writeContentType;
			contentDisposition = "attachment;filename=" + resFileSaveName;
			contentLength = (long) content.length;
			binfileMD5 = DigestUtils.md5Hex(content);
			httpStaus = 200;
			return new RespondVO(contentDisposition, binfileMD5, contentType, contentEncoding, contentLength, httpStaus, content);
		} catch (Exception e) {
			logger.error(fileName + "返回文件发生异常：", e);
			throw new BusinessException(StatusCodes.FILE_OPR_ERR, fileName + "返回文件发生异常：" + e.getMessage());
		}

	}

	@Override
	public RespondVO downloadParamFile(String filePath, byte[] writeClientContent, boolean gizp, String writeContentType) {

		String contentDisposition;
		String binfileMD5;
		String contentType;
		String contentEncoding;
		Long contentLength;
		byte[] content;
		Integer httpStaus;
		try {
			// 获取返回的文件名
			String resFileSaveName = StringUtils.getFileNameByFileName(filePath);
			// 组装返回结果，写出文件到客户端
			content = writeClientContent;
			logger.debug("文件[{}]大小：{}", resFileSaveName, content.length);
			contentEncoding = gizp ? "gzip" : "identity";
			contentType = writeContentType;
			contentDisposition = "attachment;filename=" + resFileSaveName;
			contentLength = (long) content.length;
			binfileMD5 = DigestUtils.md5Hex(content);
			httpStaus = 200;
			return new RespondVO(contentDisposition, binfileMD5, contentType, contentEncoding, contentLength, httpStaus, content);
		} catch (Exception e) {
			logger.error(filePath + "返回参数文件发生异常：", e);
			throw new BusinessException(StatusCodes.FILE_OPR_ERR, filePath + "返回文件发生异常：" + e.getMessage());
		}
	}

	/**
	 * 处理下载文件时候发生的异常
	 * @param codes
	 * @param e
	 * @return
	 * @throws IOException
	 */
	private RespondVO dealException(Exception e, StatusCodes codes) throws IOException {
		String content_Type = "text/html;charset=UTF-8";
		Integer httpstaus = codes.code();
		return new RespondVO(null, null, content_Type, null, null, httpstaus, null);
	}
}
