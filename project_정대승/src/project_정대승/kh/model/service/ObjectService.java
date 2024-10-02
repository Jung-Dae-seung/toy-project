package project_정대승.kh.model.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import project_정대승.kh.model.dto.Book;
import project_정대승.kh.model.dto.Member;

public class ObjectService {
	
	public void dataSet() {
		bookOutputData();
		memberOutputData();
		
	}

	public void bookOutputData() {
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		List<Book> bookList = new ArrayList<Book>();

		try {

			fos = new FileOutputStream("src/project_정대승/kh/model/Book.dat");
			oos = new ObjectOutputStream(fos);

			bookList.add(new Book(1, "당신이 누군가를 죽였다", "히가시노 게이고", 17820, "북다", 3));
			bookList.add(new Book(2, "불변의 법칙", "모건 하우절", 22500, "서삼독", 5));
			bookList.add(new Book(3, "THE MONEY BOOK", "토스", 19800, "비바리퍼플리카", 2));
			bookList.add(new Book(4, "모순", "양귀자", 11700, "모순", 7));

			for (int i = 0; i < bookList.size(); i++) {
				oos.writeObject(bookList.get(i));
			}

		} catch (Exception e) {
			System.out.print("");

		} finally {

			try {
				if (oos != null) oos.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}
	
	public List<Book> bookInputData() {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<Book> bkList = new ArrayList<Book>();
		
		try {
			
			fis = new FileInputStream("src/project_정대승/kh/model/Book.dat");
			ois = new ObjectInputStream(fis);

			while (true) {
				Book book = (Book) ois.readObject();
				bkList.add(book);
			}

		} catch (Exception e) {
			System.out.print("");

		} finally {
			try {
				
				if(ois != null)	ois.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		return bkList;
		
	}

	public void memberOutputData() {
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		List<Member> memList = new ArrayList<Member>();

		try {

			fos = new FileOutputStream("src/project_정대승/kh/model/Member.dat");
			oos = new ObjectOutputStream(fos);

			memList.add(new Member("hong123", "hg123", "홍길동", "사용자", null));
			memList.add(new Member("kim456", "km456", "김길동", "사용자", null));
			memList.add(new Member("admin", "ad123", "관리자", "관리자", null));

			for (int i = 0; i < memList.size(); i++) {
				oos.writeObject(memList.get(i));
			}

			fis = new FileInputStream("src/project_정대승/kh/model/Member.dat");
			ois = new ObjectInputStream(fis);

			List<Member> memberList = new ArrayList<Member>();

			try {

				while (true) {
					Member member = (Member) ois.readObject();
					memberList.add(member);
				}

			} catch (Exception e) {
				System.out.print("");

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				if (oos != null) oos.close();
				if (ois != null) ois.close();

			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}
	
	public List<Member> memberInputData() {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			
			fis = new FileInputStream("src/project_정대승/kh/model/Member.dat");
			ois = new ObjectInputStream(fis);


			while (true) {
				Member member = (Member) ois.readObject();
				memberList.add(member);
			}

		} catch (Exception e) {
				System.out.print("");

		} finally {
			try {
				
				if(ois != null)	ois.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		return memberList;
		
	}

}
