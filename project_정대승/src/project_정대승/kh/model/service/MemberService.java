package project_정대승.kh.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import project_정대승.kh.model.dto.Book;
import project_정대승.kh.model.dto.Member;

public class MemberService extends ObjectService{
	
	private Scanner sc = new Scanner(System.in);
	private List<Member> memberList = new ArrayList<Member>();
	private List<Book> deBookList = new ArrayList<Book>();
	
	// 로그인 정보 확인을 위한 변수 선언
	private Member loginMember = null;
	
	// 기본 메서드
	public MemberService() {
		// 도서, 회원의 초기 데이터 등록
		dataSet();
		
		// 등록된 초기 데이터(회원정보, 도서정보) 가져와서 List에 저장
		memberList = memberInputData();
		deBookList = bookInputData();
	}
	
	/**
	 * 메뉴 출력
	 */
	public void displayMenu() {
		
		try {
			int checkNum = 0;
			int menuNum = 0;
			
			do {
				
				if(loginMember == null) {
					System.out.println("=== 도서구매 프로그램 ===");
					System.out.println("1. 회원가입");
					System.out.println("2. 로그인");
					System.out.println("3. 전체 회원 조회");
					System.out.println("4. 비밀번호 찾기");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 입력 : ");
					menuNum = sc.nextInt();
					sc.nextLine();
					
					switch(menuNum) {
					case 1 : signUp(); break;
					case 2 : login(); break;
					case 3 : allMember(); break;
					case 4 : srchPassword(); break;
					case 0 : System.out.println("프로그램 종료..."); break;
					default : System.out.println("잘못 입력하셨습니다. 다시 입력하세요!");
					
					}
					
				} else {
					// 도서 메뉴 출력 메서드의 return값(int)을 받아서 값에 따라 조건 설정 
					checkNum = new BookStoreService().displayMenu(loginMember, deBookList);
					
					if(checkNum == 1) {
						// 도서 메뉴 출력에서 로그아웃 진행시
						loginMember = null;
						
					} else if(checkNum == 2) {
						// 도서 메뉴 출력에서 프로그램 종료 요청시
						menuNum = 0;
					}
					
				}
				
			} while (menuNum != 0);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			
		sc.close();
		
	}
	
	/**
	 * 1. 회원 가입 메서드
	 *    비밀번호가 동일할때만 회원가입 진행
	 */
	public void signUp() {
		
		System.out.println("\n<회원가입>");

		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		System.out.print("비밀번호 확인 : ");
		String memberPw2 = sc.next();
		
		System.out.print("이름 : ");
		String memberName = sc.next();
		
		// 비밀번호와 비밀번호 확인의 일치 여부
		if( memberPw.equals(memberPw2) ) {
			// 일치시 회원가입 진행
			memberList.add(new Member(memberId, memberPw, memberName, "사용자", null));
			System.out.println("회원 가입 성공\n");
			
		} else {
			System.out.println("회원 가입 실패!!! (비밀번호 불일치)\n");
			
		}
		
		
	}
	
	/**
	 * 2. 로그인
	 *    등록된 회원으로 로그인 진행
	 */
	public void login() {
		
		System.out.println("\n<로그인>");
		
		System.out.print("아이디 입력 : ");
		String id = sc.next();
		
		System.out.print("비밀번호 입력 : ");
		String pw = sc.next();
		
		// memberList 순회 진행
		for(int i = 0; i < memberList.size(); i++) {
			
			// 회원정보가 있을 경우
			if(memberList.get(i) != null) {
				// 입력한 id, pw가 둘다 일치할 경우 로그인 진행
				
				if( memberList.get(i).getId().equals(id) && 
						memberList.get(i).getPw().equals(pw)) {
					// loginMember에 해당 회원 값을 넣어준다
					loginMember = memberList.get(i);
					break;	
					
				}
				
			}
			
		}
		
		// 4) 로그인 성공/실패 여부에 따라 결과 값 반환
		if(loginMember == null) {
			// 로그인 실패
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.\n");
			
		} else {	
			// 로그인 성공
			System.out.println(loginMember.getName() + "님 환영합니다!\n");
			
		}
		
		
	}
	
	/**
	 * 3. 전체 회원 조회
	 */
	public void allMember() {
		
		System.out.println("\n<전체 회원 조회>");
		
		for(Member list : memberList) {
			System.out.println(list.getList());
			
		}
		
		System.out.println();
		
	}
	
	/**
	 * 4. 비밀번호 찾기
	 *    관리자를 제외한 입력한 id의 비밀번호 조회
	 */
	public void srchPassword() {
		
		System.out.println("\n<비밀번호 찾기>");
		
		System.out.print("비밀번호 찾을 유저 아이디 : ");
		String memberId = sc.next();
		
		boolean check = true;
		
		// 입력한 id가 관리자의 아이디인지 조건여부
		if(!memberId.equals("admin")) {
			for(Member temp : memberList) {
				
				// 입력한 id와 동일한 회원이 있을경우 해당 회원의 비밀번호 출력
				if(temp.getId().equals(memberId)) {
					System.out.println("해당 유저의 비밀번호 : " + temp.getPw() + "\n");
					check = false;
				}
				
			}
		} else {
			// 관리자는 비밀번호 못보여주게 막는다.
			System.out.println("관리자의 비밀번호는 알수 없습니다.\n");
			return;
		}
		
		// 입력한 id와 동일한 회원이 없을경우 유저가 없습니다 출력
		if(check) {
			System.out.println("해당 이름의 유저가 없습니다.\n");
		}
		
	}
	
}
