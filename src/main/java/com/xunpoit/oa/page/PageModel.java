package com.xunpoit.oa.page;

import java.util.List;

import com.xunpoit.oa.entity.Org;

public class PageModel<T> {

	private int items;
	
	private int pageSize;
	
	private List<T> dataList;

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

}
