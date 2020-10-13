package packwc;

import java.io.Serializable;

public class Ingredients implements Serializable {
	int chicken;	// 닭 2000
	int flour;		// 밀가루 1000
	int whale_bob;	// 고래밥 500
	int cheese;		// 치즈 2000
	int curry;		// 향신료 1500
	int seasoning;	// 양념 1000
	int soy_sauce;	// 간장 2000
	
	// 재료 구입
	int buyAll() {
		int money = 0;
		
		System.out.print("닭 주문 입력(skip:엔터)>");
		String chickenStr = MenuViewer.sc.nextLine();
		int chickenNum = chickenStr.isEmpty() ? 0 : Integer.parseInt(chickenStr);
		
		System.out.print("밀가루 주문 입력(skip:엔터)>");
		String flourStr = MenuViewer.sc.nextLine();
		int flourNum = flourStr.isEmpty() ? 0 : Integer.parseInt(flourStr);
		
		System.out.print("고래밥 주문 입력(skip:엔터)>");
		String whale_bobStr = MenuViewer.sc.nextLine();
		int whale_bobNum = whale_bobStr.isEmpty() ? 0 : Integer.parseInt(whale_bobStr);
		
		System.out.print("치즈 주문 입력(skip:엔터)>");
		String cheeseStr = MenuViewer.sc.nextLine();
		int cheeseNmu = cheeseStr.isEmpty() ? 0 : Integer.parseInt(cheeseStr);
		
		System.out.print("향신료 주문 입력(skip:엔터)>");
		String curryStr = MenuViewer.sc.nextLine();
		int curryNum = curryStr.isEmpty() ? 0 : Integer.parseInt(curryStr);
		
		System.out.print("시즈닝 주문 입력(skip:엔터)>");
		String seasoningStr = MenuViewer.sc.nextLine();
		int seasoningNum = seasoningStr.isEmpty() ? 0 : Integer.parseInt(seasoningStr);
		
		System.out.print("간장 주문 입력(skip:엔터)>");
		String soy_sauceStr = MenuViewer.sc.nextLine();
		int soy_sauceNum = seasoningStr.isEmpty() ? 0 : Integer.parseInt(soy_sauceStr);
		
		chicken += chickenNum;
		flour += flourNum;
		whale_bob += whale_bobNum;
		cheese += cheeseNmu;
		curry += curryNum;
		seasoning += seasoningNum;
		soy_sauce += soy_sauceNum;
		
		System.out.println("[ 닭 "+chickenNum+"마리 | 밀가루 "+flourNum+"kg | 고래밥 "+whale_bobNum+"개 | 치즈 "+cheeseNmu+"개 | 향신료 "+curryNum+"개 | 시즈닝 "+seasoningNum+"개 | 간장 "+soy_sauceNum+"개 ]");
        System.out.println("재료구입 완료");
		
		money += chickenNum*2000;
		money += flourNum*1000;
		money += whale_bobNum*500;
		money += cheeseNmu*2000;
		money += curryNum*1500;
		money += seasoningNum*1000;
		money += soy_sauceNum*2000;
		
		return money;
	}
	
	// 주문이 들어올때 재료 체크 - 없으면 일정량 재료 구입 10% 더 비싸게
	int check_buy(int name, int quantity) {
		int consume = 0;
		
		if(chicken < quantity && flour < quantity) {
			chicken += quantity;
			flour += quantity;
			
			System.out.println("급하게 동네마트에서 재료 공수");
			System.out.println("닭 "+quantity+"마리 / 밀가루 "+quantity+"kg 구입");
		}
		
		consume += quantity * (2000+200);
		consume += quantity * (1000+100);
		
		
		switch(name) {
		case 1:
			if(whale_bob < quantity) whale_bob += quantity;
			System.out.println("고래밥 "+quantity+"개 구입");
			consume += quantity * (500+50);
			break;
		case 2:
			if(cheese < quantity) cheese += quantity;
			System.out.println("치즈 "+quantity+"개 구입");
			consume += quantity * (2000+200);
			break;
		case 3:
			if(curry < quantity) curry += quantity;
			System.out.println("향신료 "+quantity+"개 구입");
			consume += quantity * (1500+150);
			break;
		case 4:
			if(seasoning < quantity) seasoning += quantity;
			System.out.println("시즈닝 "+quantity+"개 구입");
			consume += quantity * (1000+100);
			break;
		case 5:
			if(soy_sauce < quantity) soy_sauce += quantity;
			System.out.println("간장 "+quantity+"개 구입");
			consume += quantity * (2000+200);
			break;
		default:
			break;
		}
		
		return consume;
	}
	
	// 음식 주문에 따른 재료 소진
	void consume(int name, int quantity){
		chicken -= quantity;
		flour -= quantity;
		
		switch(name) {
			case 1:
				whale_bob -= quantity;
				break;
			case 2:
				cheese -= quantity;
				break;
			case 3:
				curry -= quantity;
				break;
			case 4:
				seasoning -= quantity;
				break;
			case 5:
				soy_sauce -= quantity;
				break;
		}
	}
	
	// 현재 재고량
	void showIngs() {
		System.out.println("========================현재 재고량=======================");
		System.out.println("[ 닭 "+chicken+"마리 | 밀가루 "+flour+"kg | 고래밥 "+whale_bob+"개 | 치즈 "+cheese+"개 | 향신료 "+curry+"개 | 시즈닝 "+seasoning+"개 | 간장 "+soy_sauce+"개 ]");
	}
}
