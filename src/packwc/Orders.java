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
		String[] chicken = { "", "����ġŲ", "�Ѹ�Ŭ", "�ڸ���ī", "���ġŲ", "����ġŲ" };
		String[] sidemenu = { "", "ġŲ��", "�ҽ�", "����Ƣ��", "�ݶ�", "���̴�", "����" };
		String[] sunsal = { "", "����", "��" };

		System.out.println("�ֹ� ID: " + id);
		
		for (int i = 0; i < chickenList.size(); i++) {
			Chicken ctmp = chickenList.get(i);
			System.out.println("ġŲ: " + chicken[ctmp.name] + " | ����:" + ctmp.quantity + " | " + sunsal[ctmp.isSunSal]
					+ " | ����:" + ctmp.price * ctmp.quantity);
		}

		for (int i = 0; i < sidemenuList.size(); i++) {
			SideMenu stmp = sidemenuList.get(i);
			System.out.println(
					"���̵�޴�: " + sidemenu[stmp.name] + " | ����:" + stmp.quantity + " | ����:" + stmp.price * stmp.quantity);
		}

		System.out.println("�� ����: "+ total_money + "��");
		if(total_money > final_money) {
			System.out.println("���ε� ����: " + final_money + "��");
		}
	}
}
