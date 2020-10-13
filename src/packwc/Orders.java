package packwc;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {
	ArrayList<Chicken> chickenList;
	ArrayList<SideMenu> sidemenuList;
	String id;
	int total_money;
	int final_money;

	Orders(ArrayList<Chicken> chickenList, ArrayList<SideMenu> sidemenuList, String id, int total_money, int final_money) {
		this.chickenList = chickenList;
		this.sidemenuList = sidemenuList;
		this.id = id;
		this.total_money = total_money;
		this.final_money = final_money;
	}

	Orders() {
	}

	void showOrder() {
		String[] chicken = { "", "고래밥치킨", "뿌링클", "자메이카", "양념치킨", "간장치킨" };
		String[] sidemenu = { "", "치킨무", "소스", "감자튀김", "콜라", "사이다", "맥주" };
		String[] sunsal = { "", "순살", "뼈" };

		System.out.println("주문 ID: " + id);
		
		for (int i = 0; i < chickenList.size(); i++) {
			Chicken ctmp = chickenList.get(i);
			System.out.println("치킨: " + chicken[ctmp.name] + " | 수량:" + ctmp.quantity + " | " + sunsal[ctmp.isSunSal]
					+ " | 가격:" + ctmp.price * ctmp.quantity);
		}

		for (int i = 0; i < sidemenuList.size(); i++) {
			SideMenu stmp = sidemenuList.get(i);
			System.out.println(
					"사이드메뉴: " + sidemenu[stmp.name] + " | 수량:" + stmp.quantity + " | 가격:" + stmp.price * stmp.quantity);
		}

		System.out.println("총 가격: "+ total_money + "원");
		if(total_money > final_money) {
			System.out.println("할인된 가격: " + final_money + "원");
		}
	}
}
