package project_정대승.kh.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_정대승.kh.model.dto.Book;
import project_정대승.kh.model.dto.Member;

public class BookStoreService extends ObjectService{
	
	private Scanner sc = new Scanner(System.in);
	private List<Book> bookList = new ArrayList<Book>();
	private List<Book> userBook = new ArrayList<Book>();
	
	
	// 로그인한 회원 정보
	private Member loginMember = null;
	
	
	/**
	 * 메뉴 출력 
	 * return 값 int로 반환해서 MemberService에서 이용
	 * @param member
	 * @return
	 */
	public int displayMenu(Member member, List<Book> deBookList) {
		
		// MemberService에서 사용할 int 변수 초기화
		int checkNum = 0;
		
		// 기록된 도서 목록들 가져와서 bookList 변수에 넣어
		bookList = deBookList;
		
		// 로그인한 회원 정보를 loginMember 변수에 넣기
		loginMember = member;
		
		try {
			
			int menuNum = 0;
			
			do {
				
				// 로그인한 객체의 이름이 관리자일 경우
				if(member.getName().equals("관리자")) {
				
					System.out.println("===도서 목록 프로그램===");
					System.out.println("1. 도서 등록");
					System.out.println("2. 도서 조회");
					System.out.println("3. 도서 수정");
					System.out.println("4. 도서 삭제");
					System.out.println("5. 로그아웃");
					System.out.println("0. 프로그램 종료");
	
					System.out.print("메뉴를 입력하세요 : ");
					menuNum = sc.nextInt();
					
					switch(menuNum) {
					case 1 : 
						insertBook();
						break;
					case 2 :
						totalList();
						break;
					case 3 :
						updateBook();
						break;
					case 5 : 
						System.out.println("로그아웃 진행\n");
						// 로그아웃시 checkNum을 1로 대입
						checkNum = 1;
						menuNum = 0;
						break;
					case 4 :
						delBook();
						break;
					case 0 : 
						System.out.println("종료되었습니다.");
						// 프로그램 종료시 checkNum을 2로 대입
						checkNum = 2;
						menuNum = 0;
						break;
					default : System.out.println("메뉴에 있는 번호만 입력하세요!"); break;
					}
					
				// 로그인한 객체의 이름이 관리자가 아닐경우
				} else {
					
					System.out.println("===도서 목록 프로그램===");
					System.out.println("1. 도서 구매");
					System.out.println("2. 도서 조회");
					System.out.println("3. 구매한 도서 조회");
					System.out.println("4. 도서 구매 취소");
					System.out.println("5. 로그아웃");
					System.out.println("0. 프로그램 종료");
	
					System.out.print("메뉴를 입력하세요 : ");
					menuNum = sc.nextInt();
					
					switch(menuNum) {
					case 1 : 
						buyBook();
						break;
					case 2 :
						totalList();
						break;
					case 3 :
						userBookList();
						break;
					case 4 :
						cancleBook();
						break;
					case 5 :
						System.out.println("로그아웃 진행\n");
						// 로그아웃시 checkNum을 1로 대입
						checkNum = 1;
						menuNum = 0;
						break;
					case 0 : 
						System.out.println("종료되었습니다.");
						// 프로그램 종료시 checkNum을 2로 대입
						checkNum = 2;
						menuNum = 0;
						break;
					default : System.out.println("메뉴에 있는 번호만 입력하세요!"); break;
					}
					
				} 
				
				
			} while(menuNum != 0);
			
		} catch (Exception e) {
			e.printStackTrace(); 
			
		}
		
		// 호출한 MemberService로 checkNum 반환
		return checkNum;
		
	}
	
	/**
	 * 1. 도서 등록(관리자)
	 */
	public void insertBook() {
	
		System.out.println("\n<도서 등록>");
		
		System.out.print("도서 번호 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		
		System.out.print("도서 제목 : ");
		String bookName = sc.nextLine();
		
		System.out.print("도서 저자 : ");
		String author = sc.nextLine();
		
		System.out.print("도서 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.print("도서 출판사 : ");
		String publisher = sc.nextLine();
		
		System.out.println("재고 수량 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		bookList.add(new Book(bno, bookName, author, price, publisher, stock));
		
		System.out.println("등록 완료");
		
	}
	
	/**
	 * 2. 도서 조회(공용)
	 */
	public void totalList() {
		
		System.out.println("\n<전체 도서 조회>");
		
		for(Book list : bookList) {
			System.out.println(list);
		}
		
		System.out.println();
		
	}
	
	/**
	 * 3. 도서 수정(관리자)
	 */
	public void updateBook() {
		
		System.out.println("\n<도서 수정>");
		System.out.print("수정할 도서 번호를 입력하세요 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		
		// 수정할 Book 데이터를 넣기 위한 객체 생성
		Book updateBook = null;
		
		// 입력한 도서 번호화 일치한 데이터가 있을시
		// updateBook에 해당 데이터를 넣어준다
		for(Book book : bookList) {
			if(bno == book.getBno()) {
				updateBook = book;
			}
		}
		
		if(updateBook == null) {
			// 입력한 도서 번호화 일치한 데이터가 없을시
			System.out.println("해당 도서번호의 책이 없습니다.\n");
			
		} else {
		
			System.out.println("\n1. 도서명");
			System.out.println("2. 도서 저자");
			System.out.println("3. 도서 가격");
			System.out.println("4. 도서 출판사");
			System.out.println("5. 도서 수량");
			System.out.println("0. 수정 종료");
			System.out.print("어떤 정보를 수정하시겠습니까? : ");
			int updateNum = sc.nextInt();
			
			// 입력한 updateNum에 따라 수정할 내용을 찾아감
			try {
				switch (updateNum) {
				case 1:
					System.out.println("<도서명 수정>");
					System.out.print("수정할 도서명을 입력하세요 : ");
					updateBook.setBookName(sc.next());
					
					break;
				case 2:
					System.out.println("<도서 저자 수정>");
					System.out.print("수정할 저자명을 입력하세요 : ");
					updateBook.setAuthor(sc.next());
					
					break;
				case 3:
					System.out.println("<도서 가격 수정>");
					System.out.print("수정할 가격을 입력하세요 : ");
					updateBook.setPrice(sc.nextInt());
					
					break;
				case 4:
					System.out.println(">도서 출판사 수정>");
					System.out.print("수정할 출판사명을 입력하세요 : ");
					updateBook.setPublisher(sc.next());
					
					break;
				case 5:
					System.out.println("<도서 재고수량 수정>");
					System.out.print("수정할 수량을 입력하세요 : ");
					updateBook.setStock(sc.nextInt());
					
					break;
				case 0:
					break;
					
				default:
					System.out.println("등록된 번호만 입력해주세요.\n");
					break;
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
		System.out.println("수정 완료\n");
		
	}
	
	/**
	 * 4. 도서 삭제(관리자)
	 */
	public void delBook() {
		
		System.out.println("\n<도서 삭제>");
		
		System.out.print("삭제할 도서의 번호를 입력하세오 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		
		// 삭제할 Book 데이터를 넣기 위한 객체 생성
		Book delBook = null;
		
		// 입력한 도서 번호화 일치한 데이터가 있을시
		// delBook에 해당 데이터를 넣어준다
		for(Book book : bookList) {
			if(book.getBno() == bno) {
				delBook = book;
			}
		}
		
		if(delBook == null) {
			// 입력한 도서 번호화 일치한 데이터가 없을시
			System.out.println("해당 번호가 없습니다\n");
			
		} else {
			// 입력한 도서 번호화 일치한 데이터가 있을시
			System.out.print("정말 삭제하시겠습니까?(Y/N) : ");
			char check = sc.next().toUpperCase().charAt(0);
			
			if(check == 'N') {
				System.out.println("삭제를 진행하지 않습니다.\n");
				
			} else if(check == 'Y') {
				bookList.remove(delBook);
			} 
			
		}
			
		System.out.println("해당 도서가 삭제되었습니다\n");
		
	}

	/**
	 * 도서 구매(사용자)
	 */
	public void buyBook() {
		
		System.out.println("\n<도서 구매>");
		System.out.println("도서 목록");
		
		// 전체 도서 목록
		for(Book list : bookList) {
			System.out.println(list);
		}
		
		System.out.println();
		
		System.out.print("구매할 도서의 번호를 입력해주세요 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		
		// 구매할 Book 데이터를 넣기 위한 객체 생성
		Book buyBook = null;
		
		// 입력한 도서 번호화 일치한 데이터가 있을시
		// buyBook에 해당 데이터를 넣어준다
		for(Book book : bookList) {
			if(book.getBno() == bno) {
				buyBook = book;
			}
		}
		
		if(buyBook == null) {
			// 입력한 도서 번호화 일치한 데이터가 없을시
			System.out.println("해당 번호의 책이 없습니다.\n");
			
		} else {
			// 입력한 도서 번호화 일치한 데이터가 있을시
			if(buyBook.getStock() == 0) {
				// 해당 도서의 재고 수량이 0일때
				System.out.println("해당 도서의 재고가 없습니다.\n");
				
			} else {
				// 해당 도서의 재고 수량이 1 이상일때
				System.out.print("구매할 수량을 입력해주세요 : ");
				int count = sc.nextInt();
				sc.nextLine();
				
				if(count > buyBook.getStock()) {
					// 구매할 수량이 재고 수량보다 많을 경우
					System.out.println("구매할 수량이 남은 재고보다 많습니다.\n");
					return;
					
				} else {
					// 구매할 수량이 재고 수량보다 적을 경우
					buyBook.setStock(buyBook.getStock() - count);
					
					boolean found = false;  

					// 로그인한 사용자의 구매한 도서 목록을 순회해서
					// 입력한 도서가 있는지 찾기
					for(int i = 0; i < userBook.size(); i++) {

					    if(userBook.get(i).getBno() == buyBook.getBno()) {
					    	// 도서 구매 수량 업데이트
					        userBook.get(i).setStock(userBook.get(i).getStock() + count);  
					        
					        // 로그인한 회원에게 구매한 도서 목록 데이터를 넣어준다
					        loginMember.setUserBook(userBook);
					        found = true;  
					        break;
					        
					    }
					}

					// 도서를 찾지 못했다면 새로 추가
					if (!found) {
					    userBook.add(new Book(buyBook.getBno(), buyBook.getBookName(), buyBook.getAuthor(), buyBook.getPrice(), buyBook.getPublisher(), count));
					    
					    // 로그인한 회원에게 구매한 도서 목록 데이터를 넣어준다
					    loginMember.setUserBook(userBook);
					}
					
					System.out.println("도서 구매 요청이 완료되었습니다.\n");
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * 구매한 도서 조회
	 */
	public void userBookList() {
		
		System.out.println("\n<" + loginMember.getName() + " 회원의 도서 구매 목록>" );
		
		// 로그인한 회원의 구매한 도서 목록들 조회
		for(Book temp : loginMember.getUserBook()) {
			System.out.println(temp.userBookList());
			
		}
		
		System.out.println();
		
	}
	
	/**
	 * 도서 구매 취소
	 */
	public void cancleBook() {
		
		System.out.println("\n<" + loginMember.getName() + " 의 도서 구매 취소>");
		System.out.println("구매한 도서 목록");
		
		for(Book temp : loginMember.getUserBook()) {
			System.out.println(temp);
			
		}
		
		// 도서 목록에서 사용할 변수
		Book cancleBook = null;
		
		System.out.print("구매 취소할 도서 번호를 입력해주세요 : ");
		int bno = sc.nextInt();
		sc.nextLine();
		
		// 로그인한 회원의 구매 도서 목록들을 순회를 돌아서
		// 구매 취소할 도서 번호와 일치한게 있을시
		// cancleBook 변수에 해당 도서 정보를 넣어줌
		for(int i = 0; i < loginMember.getUserBook().size(); i++) {
			if(loginMember.getUserBook().get(i).getBno() == bno) {
				cancleBook = loginMember.getUserBook().get(i);
			}
			
		}
		
		if(cancleBook == null) {
			System.out.println("해당 도서번호를 구매하지 않았습니다.\n");
			
		} else {
			System.out.print("\n정말로 해당 도서를 구매 취소 하시겠습니까? (Y/N) : ");
			char check = sc.next().toUpperCase().charAt(0);
			
			if(check == 'Y') {
				// 로그인한 회원에서 구매 취소할 도서 정보 삭제
				loginMember.getUserBook().remove(cancleBook);
				
				// 도서 목록 순회를 돌아서 구매 취소 도서 번호와 일치한 번호가 있을시
				for(int i = 0; i < bookList.size(); i++) {
					Book srchBook = bookList.get(i);
					
					// 취소한 도서의 수량만큼 다시 더해준다.
					if(srchBook.getBno() == cancleBook.getBno()) {
						srchBook.setStock(srchBook.getStock() + cancleBook.getStock());
						break;
						
					}
					
				}
				
				System.out.println("구매 취소가 확정되었습니다.\n");
				
			} else if(check == 'N') {
				System.out.println("취소하였습니다.\n");
				
			} else {
				System.out.println("잘못 입력하셨습니다.\n");
				
			}
			
		}
		
		
	}
	
}
