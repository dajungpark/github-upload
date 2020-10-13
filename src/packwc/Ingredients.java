package packwc;

import java.io.Serializable;

public class Ingredients implements Serializable {
	int chicken;	// �� 2000
	int flour;		// �а��� 1000
	int whale_bob;	// ������ 500
	int cheese;		// ġ�� 2000
	int curry;		// ��ŷ� 1500
	int seasoning;	// ��� 1000
	int soy_sauce;	// ���� 2000
	
	// ��� ����
	int buyAll() {
		int money = 0;
		
		System.out.print("�� �ֹ� �Է�(skip:����)>");
		String chickenStr = MenuViewer.sc.nextLine();
		int chickenNum = chickenStr.isEmpty() ? 0 : Integer.parseInt(chickenStr);
		
		System.out.print("�а��� �ֹ� �Է�(skip:����)>");
		String flourStr = MenuViewer.sc.nextLine();
		int flourNum = flourStr.isEmpty() ? 0 : Integer.parseInt(flourStr);
		
		System.out.print("������ �ֹ� �Է�(skip:����)>");
		String whale_bobStr = MenuViewer.sc.nextLine();
		int whale_bobNum = whale_bobStr.isEmpty() ? 0 : Integer.parseInt(whale_bobStr);
		
		System.out.print("ġ�� �ֹ� �Է�(skip:����)>");
		String cheeseStr = MenuViewer.sc.nextLine();
		int cheeseNmu = cheeseStr.isEmpty() ? 0 : Integer.parseInt(cheeseStr);
		
		System.out.print("��ŷ� �ֹ� �Է�(skip:����)>");
		String curryStr = MenuViewer.sc.nextLine();
		int curryNum = curryStr.isEmpty() ? 0 : Integer.parseInt(curryStr);
		
		System.out.print("����� �ֹ� �Է�(skip:����)>");
		String seasoningStr = MenuViewer.sc.nextLine();
		int seasoningNum = seasoningStr.isEmpty() ? 0 : Integer.parseInt(seasoningStr);
		
		System.out.print("���� �ֹ� �Է�(skip:����)>");
		String soy_sauceStr = MenuViewer.sc.nextLine();
		int soy_sauceNum = seasoningStr.isEmpty() ? 0 : Integer.parseInt(soy_sauceStr);
		
		chicken += chickenNum;
		flour += flourNum;
		whale_bob += whale_bobNum;
		cheese += cheeseNmu;
		curry += curryNum;
		seasoning += seasoningNum;
		soy_sauce += soy_sauceNum;
		
		System.out.println("[ �� "+chickenNum+"���� | �а��� "+flourNum+"kg | ������ "+whale_bobNum+"�� | ġ�� "+cheeseNmu+"�� | ��ŷ� "+curryNum+"�� | ����� "+seasoningNum+"�� | ���� "+soy_sauceNum+"�� ]");
        System.out.println("��ᱸ�� �Ϸ�");
		
		money += chickenNum*2000;
		money += flourNum*1000;
		money += whale_bobNum*500;
		money += cheeseNmu*2000;
		money += curryNum*1500;
		money += seasoningNum*1000;
		money += soy_sauceNum*2000;
		
		return money;
	}
	
	// �ֹ��� ���ö� ��� üũ - ������ ������ ��� ���� 10% �� ��ΰ�
	int check_buy(int name, int quantity) {
		int consume = 0;
		
		if(chicken < quantity && flour < quantity) {
			chicken += quantity;
			flour += quantity;
			
			System.out.println("���ϰ� ���׸�Ʈ���� ��� ����");
			System.out.println("�� "+quantity+"���� / �а��� "+quantity+"kg ����");
		}
		
		consume += quantity * (2000+200);
		consume += quantity * (1000+100);
		
		
		switch(name) {
		case 1:
			if(whale_bob < quantity) whale_bob += quantity;
			System.out.println("������ "+quantity+"�� ����");
			consume += quantity * (500+50);
			break;
		case 2:
			if(cheese < quantity) cheese += quantity;
			System.out.println("ġ�� "+quantity+"�� ����");
			consume += quantity * (2000+200);
			break;
		case 3:
			if(curry < quantity) curry += quantity;
			System.out.println("��ŷ� "+quantity+"�� ����");
			consume += quantity * (1500+150);
			break;
		case 4:
			if(seasoning < quantity) seasoning += quantity;
			System.out.println("����� "+quantity+"�� ����");
			consume += quantity * (1000+100);
			break;
		case 5:
			if(soy_sauce < quantity) soy_sauce += quantity;
			System.out.println("���� "+quantity+"�� ����");
			consume += quantity * (2000+200);
			break;
		default:
			break;
		}
		
		return consume;
	}
	
	// ���� �ֹ��� ���� ��� ����
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
	
	// ���� �����
	void showIngs() {
		System.out.println("========================���� �����=======================");
		System.out.println("[ �� "+chicken+"���� | �а��� "+flour+"kg | ������ "+whale_bob+"�� | ġ�� "+cheese+"�� | ��ŷ� "+curry+"�� | ����� "+seasoning+"�� | ���� "+soy_sauce+"�� ]");
	}
}