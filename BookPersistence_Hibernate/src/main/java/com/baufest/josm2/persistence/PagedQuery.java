package com.baufest.josm2.persistence;

/**
 * Groups the common properties for a paged query.
 * 
 * @author scortes
 */
public class PagedQuery {

	private String sortFieldName;
	
	private SortMode sortMode;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	public PagedQuery(Integer pageNum, Integer pageSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public PagedQuery(String sortFieldName, String sortMode, Integer pageNum,
			Integer pageSize) {
		super();
		this.sortFieldName = sortFieldName;
		this.sortMode = sortMode != null ? SortMode.valueOf(sortMode.toUpperCase()) : SortMode.DESC;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}

	public String getSortFieldName() {
		return sortFieldName;
	}

	public void setSortFieldName(String sortFieldName) {
		this.sortFieldName = sortFieldName;
	}

	public SortMode getSortMode() {
		return sortMode;
	}

	public void setSortMode(SortMode sortMode) {
		this.sortMode = sortMode;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize != null ? pageSize : Integer.MAX_VALUE;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getOffset(){
		Long offset = 0L;
		if(pageSize != null && pageSize > 0 && pageNum != null && pageNum > 0)
			offset = Long.valueOf(pageSize * (pageNum -1)); 
		return  offset;
	}
	
	public enum SortMode{
		ASC,DESC
	}

	@Override
	public String toString() {
		return "PagedQuery [sortFieldName=" + sortFieldName + ", sortMode="
				+ sortMode + ", pageNum=" + pageNum + ", pageSize=" + pageSize
				+ "]";
	}
	
	
}
