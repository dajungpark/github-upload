package packwc;

import java.util.Scanner;

public class MenuViewer {
	static Scanner sc = new Scanner(System.in);
	
	 public static void showMenu() {
	     System.out.println("[ 1. 주문 | 2. 관리자모드 | 3. 프로그램 종료 ]");
	     System.out.print("선택: ");
	 }
	 
	 public static void showManagerMenu() {
		 System.out.println("[ 1. 회원관리 | 2. 매출관리 | 3. 주문내역 | 4. 재고현황 | 5. 재료구입  | 6. 백업 | 7. 메인메뉴 ]");
	     System.out.print("선택: ");
	 }
	    
	 public static void showMemberMenu() {
	     System.out.println("[ 1. 회원등록 | 2. 회원정보조회 | 3. 회원정보수정 | 4. 회원정보삭제 | 5. 총 회원  | 6. 관리자메뉴 ]");
	     System.out.print("선택: ");
	 }
	    
    public static void showChickenMenu() {
    	System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━[ 메뉴판 ]━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃ 1. 고래밥치킨	┃ 2. 뿌링클	┃ 3. 자메이카	┃ 4. 양념치킨	┃ 5. 간장치킨	┃		┃");
        System.out.println("┃ 6. 치킨무	┃ 7. 소스	┃ 8. 감자튀김	┃ 9. 콜라	┃ 10. 사이다	┃ 11. 맥주	┃");
        System.out.println("┃ 0. 주문 종료	┃										┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        System.out.print("선택: ");
    }
    
    public static void showSunSalMenu() {
        System.out.println("[ 1. 순살 | 2. 뼈 ]");
        System.out.print("선택: ");
    }  
}