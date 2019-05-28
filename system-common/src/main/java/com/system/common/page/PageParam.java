package com.system.common.page;

import com.github.pagehelper.PageHelper;
import lombok.Data;

@Data
public class PageParam {

	// 第几页
	private Integer pageNum = 1;
	// 每页记录数
	private Integer numPerPage = 50;

	public PageParam() {
		super();
	}
	public PageParam(Integer pageNum, Integer numPerPage) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	/**
	 * 分页查询
	 */
	public void startPage(){
	    PageHelper.startPage(this.getPageNum(),this.getNumPerPage());
	}
}
