package com.dxc.domain;

public class Book {
	
	private Integer id;
	
	private String bookName;
	
	private String author;
	
	private Integer totalNum;
	
	private Double price;

	public Book(Integer id, String bookName, String author, Integer totalNum, Double price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.totalNum = totalNum;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author=" + author + ", totalNum=" + totalNum
				+ ", price=" + price + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
