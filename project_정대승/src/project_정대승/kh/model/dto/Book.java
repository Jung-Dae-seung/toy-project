package project_정대승.kh.model.dto;

import java.io.Serializable;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int bno;			// 책 번호
	private String bookName;	// 책 이름
	private String author;		// 책 저자
	private int price;			// 책 가격
	private String publisher;	// 책 출판사
	private int	stock;			// 책 재고
	
	public Book() {}

	public Book(int bno, String bookName, String author, int price, String publisher, int stock) {
		super();
		this.bno = bno;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.publisher = publisher;
		this.stock = stock;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "책 번호 : " + bno + ", "
				+ "책 이름 : " + bookName 
				+ ", 책 저자 : " + author 
				+ ", 책 가격 : " + price + "원 "
				+ ", 책 출판사 : " + publisher 
				+ ", 책 재고 : " + stock + "권";
	}
	
	public String userBookList() {
		return "책 번호 : " + bno + ", "
				+ "책 이름 : " + bookName 
				+ ", 책 저자 : " + author 
				+ ", 책 가격 : " + price + "원 "
				+ ", 책 출판사 : " + publisher 
				+ ", 책 재고 : " + stock + "권"
				+ ", 총합 : " + price * stock + "원";
	}
	
	
	
}
