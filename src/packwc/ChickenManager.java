package packwc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ChickenManager {
	HashSet<Member> member_set = new HashSet<Member>();		// 회원관리 컬렉션
	ArrayList<Orders> order_list = new ArrayList<Orders>();	// 주문내역관리 컬렉션
	Account account = new Account();	// 계좌
	Ingredients ings = new Ingredients();	// 재료
	
	private final String fileName = "Whale.dat";
	
	ChickenManager(){
		readDataFile();
	}
	
	//-------------------------------------//
	// [회원관리 메소드] //
	
	// 회원등록 메소드
	void create_ID() throws MenuSelectException {
		System.out.println("[ 1. 일반 등록 | 2. 관리자 등록 ]");
        System.out.print("선택: ");
        int createSelect = memberInputSelect();
        System.out.println();
		
		System.out.print("ID를 입력해주세요 >");
		String id = MenuViewer.sc.nextLine();
		System.out.print("이름을 입력해주세요 >");
		String name = MenuViewer.sc.nextLine();
		System.out.print("주소를 입력해주세요 >");
		String address = MenuViewer.sc.nextLine();
		System.out.print("전화번호를 입력해주세요 >");
		String phoneNum = MenuViewer.sc.nextLine();
		System.out.print("나이를 입력해주세요 >");
		String ageStr = MenuViewer.sc.nextLine();
		int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
		
		Member input = null;
		
		if(createSelect==1) {
			input = new Member(id, name, address, phoneNum, age);
		}else if(createSelect==2) {
			System.out.print("패스워드를 입력해주세요 >");
			String pwd = MenuViewer.sc.nextLine();
			input = new Manager(id, pwd, name, address, phoneNum, age);
		}
		
		if(!member_set.add(input)) {
			System.err.println("이미 존재하는 사람입니다");
			return;
		}
		
		System.out.println("입력된 정보");
		System.out.println("=====================================");
		System.out.println(input);
		System.out.println();
	}
	
	// 회원정보수정 메소드
	void update_ID() throws MenuSelectException {
		System.out.println();
		System.out.print("수정할 ID 입력>");
		String id = MenuViewer.sc.nextLine();
		Member tmp = check_ID(id);
		if(tmp==null) {
			System.err.println("찾을 수 없습니다.");
		}else {
			System.out.println();
			System.out.println("수정할 정보");
			System.out.println(tmp);
			
			member_set.remove(tmp);
			
			System.out.println();
			System.out.print("이름 새 입력>");
			String name=MenuViewer.sc.nextLine();
			System.out.print("주소 새 입력>");
			String address=MenuViewer.sc.nextLine();
			System.out.print("전화번호 새 입력>");
			String phoneNum=MenuViewer.sc.nextLine();
			System.out.print("나이 새 입력>");
			String ageStr = MenuViewer.sc.nextLine();
			int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
			
			Member update = null;
			
			if(tmp instanceof Manager) {
				System.out.print("패스워드 새 입력>");
				String pwd = MenuViewer.sc.nextLine();
				update = new Manager(id, pwd, name, address, phoneNum, age);
			}else if(tmp instanceof Member) {
				update = new Member(id, name, address, phoneNum, age);
			}
			
			member_set.add(update);
			
			System.out.println();
			System.out.println("수정 완료");
			System.out.println(update);
		}
		System.out.println();
	}
	
	// 회원정보삭제 메소드
	void remove_ID() {
		System.out.println();
		System.out.print("삭제할 ID 입력>");
		String id = MenuViewer.sc.nextLine();
		Member tmp = check_ID(id);
		if(tmp==null) {
			System.err.println("찾을 수 없습니다.");
		} else {
			System.out.println();
			System.out.println("삭제할 정보");
			System.out.println(tmp);
			
			if(!member_set.remove(tmp)) {
				System.err.println("삭제 실패");
				return;
			}
			
			System.out.println("삭제되었습니다.");
		}
	}
	
	// 회원정보출력 메소드
	void showAll_ID() {
		Iterator it = member_set.iterator();
		
		System.out.println("=================== All Member Data ===================");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println();
	}
	
	// 회원정보조회 메소드
	void search_ID() {
		 Iterator it = member_set.iterator();
	        System.out.println();
	        System.out.print("조회할 ID 입력>");
	        String id = MenuViewer.sc.nextLine();
	       
	        Member tmp = check_ID(id);
	        if(tmp == null) {
	        	System.out.println("조회할 ID가 존재하지 않음.");
	        	return;
	        }
	        
	        System.out.println("해당되는 ID 검색이 완료되었습니다.");
	        System.out.println(tmp);
	}
	
	// ID체크 메소드
	Member check_ID(String id) {
		Iterator it = member_set.iterator();
		
		while(it.hasNext()) {
			Member tmp = (Member)it.next();
			if(tmp.id.equals(id)) {
				return tmp;
			}
		}
		return null;
	}
	
	//------------------------------------------//
	// [주문 관련 메소드] //
	
	void order() {
		ArrayList<Chicken> chicken_list = new ArrayList<Chicken>();		// 치킨 객체리스트
		ArrayList<SideMenu> side_list = new ArrayList<SideMenu>();	// 사이드메뉴 객체리스트
		int total_Money = 0;	// 주문 가격
		int consume_Money = 0;	// 재료 주문 가격
		
		String id = null;	// 회원 아이디 
		while(true) {
			System.out.print("회원 아이디 입력(비회원:엔터) > ");
			id = MenuViewer.sc.nextLine();	// id 입력받기
			if(check_ID(id)==null && !id.isEmpty()) {
				System.out.println("존재하지 않는 회원입니다.");
			}else if(id.isEmpty()) {
				id = "비회원";
				break;
			}else {
				break;
			}
		}
		
		while(true) {	// 메뉴 주문 받기
			try {
				MenuViewer.showChickenMenu();
				int name = foodSelect();	// 메뉴 종류 입력
				if(name == 0) {		// 주문 종료
					break;
				}
				System.out.print("주문할 개수 입력 : ");
				int quantity = Integer.parseInt(MenuViewer.sc.nextLine());	// 메뉴 수량 입력
				
				if(name < 6) {	// 치킨 메뉴면
					MenuViewer.showSunSalMenu();
					int isSunSal = Integer.parseInt(MenuViewer.sc.nextLine());
					// 치킨 생성 메소드로 변수(종류, 수량, 순살여부) 전달
					chicken_list.add(makeChicken(name, quantity, isSunSal)); // 생성시킨 치킨 객체를 반환받음
				}else if(name > 5) {	// 사이드 메뉴면
					if(name == 11) {	// 그 중에 맥주면
						if(id.equals("비회원")) {
							System.out.println("신분증을 확인해주세요");
							System.out.print("확인되었으면 1/취소는 0 입력 >");
							int check = Integer.parseInt(MenuViewer.sc.nextLine());
							if(check == 1) {
								System.out.println("신분증 확인");
							}else if(check == 0) {
								System.out.println("판매 취소");
								continue;
							}else {
								System.out.println("입력 오류");
								continue;
							}
						}else {
							Member tmp = check_ID(id);
							System.out.println(tmp.age+"세 입니다.");
							if(tmp.age > 19) {
								System.out.println("판매 가능");
							}else {
								System.out.println("판매 불가능");
								continue;
							}
						}
					}
					// 사이드 생성 메소드로 변수(종류, 수량) 전달
					side_list.add(makeSide(name-5, quantity)); // 생성시킨 사이드 객체를 반환받음
				}
			}catch(MenuSelectException e) {
				System.err.println(e.getMessage());
            }catch(Exception e) {
                System.err.println("입력 오류");
            }
		}
		
		System.out.println("[주문 진행 : Press Any Key To Continue]");
		System.out.println("- 주문취소를 원하시면 \"cancel\"를 정확히 입력 -");
		System.out.print("입력 >> ");
		String yorn = MenuViewer.sc.nextLine();
		if(yorn.equals("cancel")) {
			return;
		}else if(chicken_list.isEmpty() && side_list.isEmpty()) {
			return;
		}
		
		// 치킨 재료 소비 및 구입확인 / 수입 더하기
        for (int i = 0; i < chicken_list.size(); i++) {
        	Chicken tmp = chicken_list.get(i);
            total_Money += tmp.price * tmp.quantity;					// 수입 계산
            
            consume_Money += ings.check_buy(tmp.name, tmp.quantity);	// 지출 계산
            ings.consume(tmp.name, tmp.quantity);						// 재료 소진
        }
 
        // 사이드메뉴 수입 더하기
        for (int i = 0; i < side_list.size(); i++) {
            total_Money += side_list.get(i).price * side_list.get(i).quantity;	// 수입 계산
        }
		
		int dis_money = discount(total_Money, id);	// 할인
		
		account.deposit(dis_money);			// 수입 정산
		account.withdraw(consume_Money);	// 지출 정산
		
		Orders od = new Orders(chicken_list, side_list, id, total_Money, dis_money);	// 주문내역 생성
		
		od.showOrder();	// 주문내역 출력
		
		order_list.add(od);	// 주문내역 저장
	}
	
	
	Chicken makeChicken(int name, int quantity, int isSunSal) {
			Chicken ctmp = new Chicken(name, quantity, isSunSal);	// name(종류)치킨 객체 생성
		return ctmp;
	}	// 치킨 객체 생성
	
	SideMenu makeSide(int name, int quantity) {
			SideMenu stmp = new SideMenu(name, quantity);	// name(종류)사이드 객체 생성
		return stmp;
	}	// 사이드 음식 객체 생성
	
	
	// 배달료, 등급할인 등 돈관련
	int discount(int price, String id) {
		if(id.equals("비회원")) {
			return price;
		}
		
		Member member = check_ID(id);
		
		int dis_price = 0;
		
		// 등급에 따른 할인 적용
		switch(member.grade) {
			case 1:
				dis_price = (int) (price*(1.00 - 0.05));	// 5%
				break;
			case 2:
				dis_price = (int) (price*(1.00 - 0.1));		// 10%
				break;
			case 3:
				dis_price = (int) (price*(1.00 - 0.15));	// 15%
				break;
			default:
				dis_price = price;
				break;
		}
		
		// 회원 history_money정보수정 (+등급 조정)
		member_set.remove(member);
		member_set.add(new Member(member, dis_price));
		
		return dis_price;
	}
	
	//------------------------------------------//
	// [관리자모드 메소드] //
	
	// 관리자 로그인 메소드
	boolean manager_mode() {
		System.out.println("관리자 모드 실행>>");

		System.out.print("ID 입력:");
		String id = MenuViewer.sc.nextLine();

		Member tmp = check_ID(id);
			
		System.out.print("패스워드 입력:");
		String pwd = MenuViewer.sc.nextLine();
		
		if(tmp instanceof Manager) {}
		else if(tmp instanceof Member) {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			manager_mode();
			System.out.println();
		}
		
		Manager mtmp = (Manager)tmp;

		if ( (id.equals("admin") && pwd.equals("1234")) || ((id.equals(mtmp.id) && pwd.equals(mtmp.pwd))) ) {
			System.out.println("로그인 되었습니다.");
			return true;
		}else {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			manager_mode();
			System.out.println();
		}
		return false;
	}
	
	// 수입 현황 (매출관리) 메소드
	void showAccount() {
		System.out.println("매출 현황: "+account.getMaechul()+"원");
		System.out.println("----------------------------------");
		System.out.println("지출 현황: "+account.getJichul()+"원");
		System.out.println("----------------------------------");
		System.out.println("순 이익: "+account.getIncome()+"원");
	}
	
	// 전체 주문내역 출력 (주문내역) 메소드
	void orderCheck() {
		System.out.println("전체 주문내역");
		for(int i=0; i<order_list.size(); i++) {
			order_list.get(i).showOrder();
			System.out.println();
		}
	}
	
	// 주문내역 ID조회 메소드
	void order_ID() {
		System.out.println();
		System.out.print("주문조회할 ID입력(전체출력:엔터)>");
		String id = MenuViewer.sc.nextLine();
		if(id.isEmpty()) {
			orderCheck();
		}else {
			Iterator it = order_list.iterator();
			while(it.hasNext()) {
				Orders tmp = (Orders)it.next();
				if(tmp.id.equals(id)) {
					tmp.showOrder();
				}
			}
		}
	}
	
	// 재료 구입 메소드
	void buyIngs() {
		account.withdraw(ings.buyAll());
	}
	
	//-------------------------------------------//
	// [메뉴 메소드] //
	
	// 회원관리 메뉴 메소드
	public void memberMenu() {
		try {
			while(true) {
	    		MenuViewer.showMemberMenu();
	    		int memberMenuSelect = memberMenuSelect();
	    		switch(memberMenuSelect) {
	    			case 1:
	    				create_ID();
	    				break;
	    			case 2:
	    				search_ID();
	    				break;
	    			case 3:
	    				update_ID();
	    				break;
	    			case 4:
	    				remove_ID();
	    				break;
	    			case 5:
	    				showAll_ID();
	    				break;
	    			case 6:
	    				return;
	    		}
	    	}
		}catch(MenuSelectException e) {
			System.err.println(e.getMessage());
        }catch(Exception e) {
            System.err.println("입력 오류");
        }
    }
	
	//----------------------------------------------//
	// [입력값 예외처리 메소드] //
	
	// 음식 이름 선택값 입력 메소드
    public int foodSelect() throws MenuSelectException {
        int foodSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(foodSelect < 0 || foodSelect > 11) { // 0~11사이 값이 아니면
            throw new MenuSelectException("없는 메뉴 입니다.");
        }
        return foodSelect;
    }
    
    // 메인 메뉴 선택값 입력 메소드
    public int menuSelect() throws MenuSelectException {
        int menuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(menuSelect < 1 || menuSelect > 3) { // 1~3사이 값이 아니면
            throw new MenuSelectException("입력 오류");
        }
        return menuSelect;
    }
    
    // 관리자 메뉴 선택값 입력 메소드
    public int menuManagerSelect() throws MenuSelectException {
        int menuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(menuSelect < 1 || menuSelect > 7) { // 1~7사이 값이 아니면
            throw new MenuSelectException("입력 오류");
        }
        return menuSelect;
    }
    
    // 회원 메뉴 선택값 입력 메소드
    public int memberMenuSelect() throws MenuSelectException {
        int memberMenuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(memberMenuSelect < 1 || memberMenuSelect > 6) { // 1~6사이 값이 아니면
            throw new MenuSelectException("입력 오류");
        }
        return memberMenuSelect;
    }
    
    // 매출 메뉴 선택값 입력 메소드
    public int accountMenuSelect() throws MenuSelectException {
        int accountMenuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(accountMenuSelect < 1 || accountMenuSelect > 3) { // 1~3사이 값이 아니면
            throw new MenuSelectException("입력 오류");
        }
        return accountMenuSelect;
    }
    
 // 매출 메뉴 선택값 입력 메소드
    public int memberInputSelect() throws MenuSelectException {
        int MemberInputSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(MemberInputSelect < 1 || MemberInputSelect > 2) { // 1~2사이 값이 아니면
            throw new MenuSelectException("입력 오류");
        }
        return MemberInputSelect;
    }
    
    //---------------------------------------//
    // [FILE IO 메소드] //
    
    // 데이터파일 저장 메소드
    public void writeDataFile() {
        try (FileOutputStream fos = new FileOutputStream(fileName); 
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream out = new ObjectOutputStream(bos)) {
            
            out.writeObject(member_set);
            out.writeObject(order_list);
            out.writeObject(account);
            out.writeObject(ings);
            
            System.out.println(fileName+" : File Write Success...");
        }catch(IOException e) {
            System.err.println(fileName+" : File Write Fail...");
        }
    }
    
    // 데이터파일 읽기 메소드
    public void readDataFile() {
        try (FileInputStream fis = new FileInputStream(fileName); 
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream in = new ObjectInputStream(bis)) {
            
            member_set = (HashSet<Member>)in.readObject();
            order_list = (ArrayList<Orders>)in.readObject();
            account = (Account)in.readObject();
            ings = (Ingredients)in.readObject();
            
            System.out.println(fileName+" : File Read Success...");
        }catch(Exception e) {
            System.err.println(fileName+" : File Read Fail...");
        }
    }
    
}
