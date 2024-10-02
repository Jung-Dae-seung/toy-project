package project_정대승.kh.model.dto;

import java.io.Serializable;
import java.util.List;

public class Member implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;				// 회원id
	private String pw;				// 회원password
	private String name;			// 회원이름
	private String type;			// 회원타입(관리자/유저)
	private List<Book> userBook;  	// 회원별 구매 도서 목록
	
	public Member() {}

	public Member(String id, String pw, String name, String type, List<Book> userBook) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.type = type;
		this.userBook = userBook;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public List<Book> getUserBook() {
		return userBook;
	}

	public void setUserBook(List<Book> userBook) {
		this.userBook = userBook;
	}

	@Override
	public String toString() {
		return "회원 [아이디 : " + id + ", 비밀번호 : " + pw + ", 이름 : " + name + ", 유형 : " + type + "]";
	}
	
	public String getList() {
		return "회원 [아이디 : " + id + ", 이름 : " + name + ", 유형 : " + type + "]";
	}
	
	
	
	
}
