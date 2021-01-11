/**
 * 
 */
package com.rest.app.util;

import java.util.List;

/**
 * @author danielf
 *
 */
public class PaginateValues {

	private long count;
	private int row;
	private int pages;
	private List<Integer> rowoptions;

	public List<Integer> getRowoptions() {
		return rowoptions;
	}

	public void setRowoptions(List<Integer> rowoptions) {
		this.rowoptions = rowoptions;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
