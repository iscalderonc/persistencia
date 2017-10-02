package com.baufest.josm2.persistence;

import java.util.List;

/**
 * Use it when trying to manage a long list of elements into pages.
 * 
 * @author ccancinos
 *
 * @param <T> The type of the elements in the list of results
 */
public class PageResult<T> {
	private long totalElementsCount;
	private List<T> list;

	
	public PageResult(long totalElementsCount, List<T> list) {
		super();
		this.totalElementsCount = totalElementsCount;
		this.list = list;
	}

	public long getTotalElementsCount() {
		return totalElementsCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setTotalElementsCount(long totalElementsCount) {
		this.totalElementsCount = totalElementsCount;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
