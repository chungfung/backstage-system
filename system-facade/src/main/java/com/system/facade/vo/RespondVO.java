package com.system.facade.vo;

import lombok.Data;

/**
 * @author 未知
 */
@Data
public class RespondVO {

	final private String content_Disposition;

	final private String binfile_md5;

	final private String content_Type;

	final private String content_Encoding;

	final private Long content_Length;

	final private Integer httpstaus;

	final private byte[] content;

	public RespondVO(String content_Disposition, String binfile_md5, String content_Type, String content_Encoding,
                     Long content_Length, Integer httpstaus, byte[] content) {
		super();
		this.content_Disposition = content_Disposition;
		this.binfile_md5 = binfile_md5;
		this.content_Type = content_Type;
		this.content_Encoding = content_Encoding;
		this.content_Length = content_Length;
		this.httpstaus = httpstaus;
		this.content = content;
	}
	
}
