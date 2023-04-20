package com.brooklyn.xeke.payload;

import java.util.List;


public class BookingDetailResponse {
	private List<BookingDetailDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	private String sortField;
	private String orderBy;
	private String reverseOrderBy;
	
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getReverseOrderBy() {
		return reverseOrderBy;
	}
	public void setReverseOrderBy(String reverseOrderBy) {
		this.reverseOrderBy = reverseOrderBy;
	}
	public List<BookingDetailDTO> getContent() {
		return content;
	}
	public void setContent(List<BookingDetailDTO> content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
}
