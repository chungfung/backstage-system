package com.system.common.page;

import java.util.List;

public class PageResult<M> {

    private int pageNum =1;
    private int totalPageCount;
    private int totalCount;
    private int numPerPage=9999;
    private int upPageNo;
    private int nextPageNo;
    
    private List<M> list;
    @SuppressWarnings("unused")
	private int offset=0;
    
    
    public int getUpPageNo() {
        return upPageNo;
    }
    public void setUpPageNo(int upPageNo) {
        if(this.pageNum>1){
            this.upPageNo = this.pageNum-1;
        }
        
    }
    public int getNextPageNo() {
        return nextPageNo;
    }
    public void setNextPageNo(int nextPageNo) {
        if(this.pageNum>0 &&this.pageNum<this.totalPageCount){
            this.nextPageNo = pageNum+1;
        }
        
    }
    public List<M> getList() {
        return list;
    }
    public void setList(List<M> list) {
        this.list = list;
    }
    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int currentPageNo) {
        if(currentPageNo>0){
            this.pageNum = currentPageNo;
        }
        
    }
	public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount = totalCount;
        }
        
    }
    public int getNumPerPage() {
        return numPerPage;
    }
    public void setNumPerPage(int numPerPage) {
        if(numPerPage >0){
            this.numPerPage = numPerPage;
        }
        
    }
    
    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        if(this.getTotalCount()%this.numPerPage==0){
            this.totalPageCount = this.getTotalCount()/this.numPerPage;
        }else if(this.getTotalCount()%this.numPerPage>0){
            this.totalPageCount = this.getTotalCount()/this.numPerPage +1 ;
        }else{
            this.totalPageCount =0;
        }
    }
	public int getOffset() {
		return (this.pageNum-1)*this.numPerPage;
	}
	public void setOffset(int offset) {
		this.offset = (this.pageNum-1)*this.numPerPage;
	}	
}