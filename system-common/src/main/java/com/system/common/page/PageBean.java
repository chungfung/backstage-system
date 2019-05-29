package com.system.common.page;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <T>
 */
@Data
public class PageBean<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 结果集
	 */
	private List<T> list;

	/**
	 * 第几页
	 */
	private int pageNum;

	/**
	 * 每页记录数
	 */
	private int pageSize;

	/**
	 * 总页数
	 */
	private int pages;

	/**
	 * 当前页的数量<=pageSize
	 */
	private int size;

	/**
	 * 列表对象汇总信息
	 */
	private T summary;

	public PageBean(List<T> list) {
		
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			this.pages = page.getPages();
			this.list = page;
			this.size = page.size();
		}
	}
}
