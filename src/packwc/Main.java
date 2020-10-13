package packwc;

public class Main {
	public static void main(String[] args) {
		ChickenManager cm = new ChickenManager();
		
		while(true) {
			MenuViewer.showMenu();	// 메인메뉴
			
			try {
				int menuSelect = cm.menuSelect();
				
				// 메인메뉴 선택 스위치문
				switch(menuSelect) {
					case 1:
						cm.order();	// 주문
						break;
					case 2:
						boolean flag = false;
						flag = cm.manager_mode();		// 관리자 로그인 (성공시 true)
						while(flag) {
							MenuViewer.showManagerMenu();	// 관리자메뉴
							int managerSelect = cm.menuManagerSelect();
							switch(managerSelect) {
								case 1:
									cm.memberMenu();	// 회원관리
									break;
								case 2:
									cm.showAccount();	// 매출관리
									break;
								case 3:
									cm.order_ID();		// 주문내역
									break;
								case 4:
									cm.ings.showIngs(); // 재고현황
									break;
								case 5:
									cm.buyIngs();		// 재료구입
									break;
								case 6:
									cm.writeDataFile();	// 백업
									break;
								case 7:
									flag = false;		// 메인메뉴로
									break;
							}
						}
						break;
					case 3:
						System.out.println("프로그램을 종료합니다.");
						cm.writeDataFile();
						return;
				}
			}catch(MenuSelectException e) {
				System.err.println(e.getMessage());
            }catch(Exception e) {
                System.err.println("입력 오류");
            }
		}
	}
}
