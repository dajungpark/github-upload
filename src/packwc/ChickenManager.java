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
	HashSet<Member> member_set = new HashSet<Member>();		// ȸ������ �÷���
	ArrayList<Orders> order_list = new ArrayList<Orders>();	// �ֹ��������� �÷���
	Account account = new Account();	// ����
	Ingredients ings = new Ingredients();	// ���
	
	private final String fileName = "Whale.dat";
	
	ChickenManager(){
		readDataFile();
	}
	
	//-------------------------------------//
	// [ȸ������ �޼ҵ�] //
	
	// ȸ����� �޼ҵ�
	void create_ID() throws MenuSelectException {
		System.out.println("[ 1. �Ϲ� ��� | 2. ������ ��� ]");
        System.out.print("����: ");
        int createSelect = memberInputSelect();
        System.out.println();
		
		System.out.print("ID�� �Է����ּ��� >");
		String id = MenuViewer.sc.nextLine();
		System.out.print("�̸��� �Է����ּ��� >");
		String name = MenuViewer.sc.nextLine();
		System.out.print("�ּҸ� �Է����ּ��� >");
		String address = MenuViewer.sc.nextLine();
		System.out.print("��ȭ��ȣ�� �Է����ּ��� >");
		String phoneNum = MenuViewer.sc.nextLine();
		System.out.print("���̸� �Է����ּ��� >");
		String ageStr = MenuViewer.sc.nextLine();
		int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
		
		Member input = null;
		
		if(createSelect==1) {
			input = new Member(id, name, address, phoneNum, age);
		}else if(createSelect==2) {
			System.out.print("�н����带 �Է����ּ��� >");
			String pwd = MenuViewer.sc.nextLine();
			input = new Manager(id, pwd, name, address, phoneNum, age);
		}
		
		if(!member_set.add(input)) {
			System.err.println("�̹� �����ϴ� ����Դϴ�");
			return;
		}
		
		System.out.println("�Էµ� ����");
		System.out.println("=====================================");
		System.out.println(input);
		System.out.println();
	}
	
	// ȸ���������� �޼ҵ�
	void update_ID() throws MenuSelectException {
		System.out.println();
		System.out.print("������ ID �Է�>");
		String id = MenuViewer.sc.nextLine();
		Member tmp = check_ID(id);
		if(tmp==null) {
			System.err.println("ã�� �� �����ϴ�.");
		}else {
			System.out.println();
			System.out.println("������ ����");
			System.out.println(tmp);
			
			member_set.remove(tmp);
			
			System.out.println();
			System.out.print("�̸� �� �Է�>");
			String name=MenuViewer.sc.nextLine();
			System.out.print("�ּ� �� �Է�>");
			String address=MenuViewer.sc.nextLine();
			System.out.print("��ȭ��ȣ �� �Է�>");
			String phoneNum=MenuViewer.sc.nextLine();
			System.out.print("���� �� �Է�>");
			String ageStr = MenuViewer.sc.nextLine();
			int age = ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
			
			Member update = null;
			
			if(tmp instanceof Manager) {
				System.out.print("�н����� �� �Է�>");
				String pwd = MenuViewer.sc.nextLine();
				update = new Manager(id, pwd, name, address, phoneNum, age);
			}else if(tmp instanceof Member) {
				update = new Member(id, name, address, phoneNum, age);
			}
			
			member_set.add(update);
			
			System.out.println();
			System.out.println("���� �Ϸ�");
			System.out.println(update);
		}
		System.out.println();
	}
	
	// ȸ���������� �޼ҵ�
	void remove_ID() {
		System.out.println();
		System.out.print("������ ID �Է�>");
		String id = MenuViewer.sc.nextLine();
		Member tmp = check_ID(id);
		if(tmp==null) {
			System.err.println("ã�� �� �����ϴ�.");
		} else {
			System.out.println();
			System.out.println("������ ����");
			System.out.println(tmp);
			
			if(!member_set.remove(tmp)) {
				System.err.println("���� ����");
				return;
			}
			
			System.out.println("�����Ǿ����ϴ�.");
		}
	}
	
	// ȸ��������� �޼ҵ�
	void showAll_ID() {
		Iterator it = member_set.iterator();
		
		System.out.println("=================== All Member Data ===================");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println();
	}
	
	// ȸ��������ȸ �޼ҵ�
	void search_ID() {
		 Iterator it = member_set.iterator();
	        System.out.println();
	        System.out.print("��ȸ�� ID �Է�>");
	        String id = MenuViewer.sc.nextLine();
	       
	        Member tmp = check_ID(id);
	        if(tmp == null) {
	        	System.out.println("��ȸ�� ID�� �������� ����.");
	        	return;
	        }
	        
	        System.out.println("�ش�Ǵ� ID �˻��� �Ϸ�Ǿ����ϴ�.");
	        System.out.println(tmp);
	}
	
	// IDüũ �޼ҵ�
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
	// [�ֹ� ���� �޼ҵ�] //
	
	void order() {
		ArrayList<Chicken> chicken_list = new ArrayList<Chicken>();		// ġŲ ��ü����Ʈ
		ArrayList<SideMenu> side_list = new ArrayList<SideMenu>();	// ���̵�޴� ��ü����Ʈ
		int total_Money = 0;	// �ֹ� ����
		int consume_Money = 0;	// ��� �ֹ� ����
		
		String id = null;	// ȸ�� ���̵� 
		while(true) {
			System.out.print("ȸ�� ���̵� �Է�(��ȸ��:����) > ");
			id = MenuViewer.sc.nextLine();	// id �Է¹ޱ�
			if(check_ID(id)==null && !id.isEmpty()) {
				System.out.println("�������� �ʴ� ȸ���Դϴ�.");
			}else if(id.isEmpty()) {
				id = "��ȸ��";
				break;
			}else {
				break;
			}
		}
		
		while(true) {	// �޴� �ֹ� �ޱ�
			try {
				MenuViewer.showChickenMenu();
				int name = foodSelect();	// �޴� ���� �Է�
				if(name == 0) {		// �ֹ� ����
					break;
				}
				System.out.print("�ֹ��� ���� �Է� : ");
				int quantity = Integer.parseInt(MenuViewer.sc.nextLine());	// �޴� ���� �Է�
				
				if(name < 6) {	// ġŲ �޴���
					MenuViewer.showSunSalMenu();
					int isSunSal = Integer.parseInt(MenuViewer.sc.nextLine());
					// ġŲ ���� �޼ҵ�� ����(����, ����, ���쿩��) ����
					chicken_list.add(makeChicken(name, quantity, isSunSal)); // ������Ų ġŲ ��ü�� ��ȯ����
				}else if(name > 5) {	// ���̵� �޴���
					if(name == 11) {	// �� �߿� ���ָ�
						if(id.equals("��ȸ��")) {
							System.out.println("�ź����� Ȯ�����ּ���");
							System.out.print("Ȯ�εǾ����� 1/��Ҵ� 0 �Է� >");
							int check = Integer.parseInt(MenuViewer.sc.nextLine());
							if(check == 1) {
								System.out.println("�ź��� Ȯ��");
							}else if(check == 0) {
								System.out.println("�Ǹ� ���");
								continue;
							}else {
								System.out.println("�Է� ����");
								continue;
							}
						}else {
							Member tmp = check_ID(id);
							System.out.println(tmp.age+"�� �Դϴ�.");
							if(tmp.age > 19) {
								System.out.println("�Ǹ� ����");
							}else {
								System.out.println("�Ǹ� �Ұ���");
								continue;
							}
						}
					}
					// ���̵� ���� �޼ҵ�� ����(����, ����) ����
					side_list.add(makeSide(name-5, quantity)); // ������Ų ���̵� ��ü�� ��ȯ����
				}
			}catch(MenuSelectException e) {
				System.err.println(e.getMessage());
            }catch(Exception e) {
                System.err.println("�Է� ����");
            }
		}
		
		System.out.println("[�ֹ� ���� : Press Any Key To Continue]");
		System.out.println("- �ֹ���Ҹ� ���Ͻø� \"cancel\"�� ��Ȯ�� �Է� -");
		System.out.print("�Է� >> ");
		String yorn = MenuViewer.sc.nextLine();
		if(yorn.equals("cancel")) {
			return;
		}else if(chicken_list.isEmpty() && side_list.isEmpty()) {
			return;
		}
		
		// ġŲ ��� �Һ� �� ����Ȯ�� / ���� ���ϱ�
        for (int i = 0; i < chicken_list.size(); i++) {
        	Chicken tmp = chicken_list.get(i);
            total_Money += tmp.price * tmp.quantity;					// ���� ���
            
            consume_Money += ings.check_buy(tmp.name, tmp.quantity);	// ���� ���
            ings.consume(tmp.name, tmp.quantity);						// ��� ����
        }
 
        // ���̵�޴� ���� ���ϱ�
        for (int i = 0; i < side_list.size(); i++) {
            total_Money += side_list.get(i).price * side_list.get(i).quantity;	// ���� ���
        }
		
		int dis_money = discount(total_Money, id);	// ����
		
		account.deposit(dis_money);			// ���� ����
		account.withdraw(consume_Money);	// ���� ����
		
		Orders od = new Orders(chicken_list, side_list, id, total_Money, dis_money);	// �ֹ����� ����
		
		od.showOrder();	// �ֹ����� ���
		
		order_list.add(od);	// �ֹ����� ����
	}
	
	
	Chicken makeChicken(int name, int quantity, int isSunSal) {
			Chicken ctmp = new Chicken(name, quantity, isSunSal);	// name(����)ġŲ ��ü ����
		return ctmp;
	}	// ġŲ ��ü ����
	
	SideMenu makeSide(int name, int quantity) {
			SideMenu stmp = new SideMenu(name, quantity);	// name(����)���̵� ��ü ����
		return stmp;
	}	// ���̵� ���� ��ü ����
	
	
	// ��޷�, ������� �� ������
	int discount(int price, String id) {
		if(id.equals("��ȸ��")) {
			return price;
		}
		
		Member member = check_ID(id);
		
		int dis_price = 0;
		
		// ��޿� ���� ���� ����
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
		
		// ȸ�� history_money�������� (+��� ����)
		member_set.remove(member);
		member_set.add(new Member(member, dis_price));
		
		return dis_price;
	}
	
	//------------------------------------------//
	// [�����ڸ�� �޼ҵ�] //
	
	// ������ �α��� �޼ҵ�
	boolean manager_mode() {
		System.out.println("������ ��� ����>>");

		System.out.print("ID �Է�:");
		String id = MenuViewer.sc.nextLine();

		Member tmp = check_ID(id);
			
		System.out.print("�н����� �Է�:");
		String pwd = MenuViewer.sc.nextLine();
		
		if(tmp instanceof Manager) {}
		else if(tmp instanceof Member) {
			System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			manager_mode();
			System.out.println();
		}
		
		Manager mtmp = (Manager)tmp;

		if ( (id.equals("admin") && pwd.equals("1234")) || ((id.equals(mtmp.id) && pwd.equals(mtmp.pwd))) ) {
			System.out.println("�α��� �Ǿ����ϴ�.");
			return true;
		}else {
			System.out.println("���̵� �Ǵ� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			manager_mode();
			System.out.println();
		}
		return false;
	}
	
	// ���� ��Ȳ (�������) �޼ҵ�
	void showAccount() {
		System.out.println("���� ��Ȳ: "+account.getMaechul()+"��");
		System.out.println("----------------------------------");
		System.out.println("���� ��Ȳ: "+account.getJichul()+"��");
		System.out.println("----------------------------------");
		System.out.println("�� ����: "+account.getIncome()+"��");
	}
	
	// ��ü �ֹ����� ��� (�ֹ�����) �޼ҵ�
	void orderCheck() {
		System.out.println("��ü �ֹ�����");
		for(int i=0; i<order_list.size(); i++) {
			order_list.get(i).showOrder();
			System.out.println();
		}
	}
	
	// �ֹ����� ID��ȸ �޼ҵ�
	void order_ID() {
		System.out.println();
		System.out.print("�ֹ���ȸ�� ID�Է�(��ü���:����)>");
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
	
	// ��� ���� �޼ҵ�
	void buyIngs() {
		account.withdraw(ings.buyAll());
	}
	
	//-------------------------------------------//
	// [�޴� �޼ҵ�] //
	
	// ȸ������ �޴� �޼ҵ�
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
            System.err.println("�Է� ����");
        }
    }
	
	//----------------------------------------------//
	// [�Է°� ����ó�� �޼ҵ�] //
	
	// ���� �̸� ���ð� �Է� �޼ҵ�
    public int foodSelect() throws MenuSelectException {
        int foodSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(foodSelect < 0 || foodSelect > 11) { // 0~11���� ���� �ƴϸ�
            throw new MenuSelectException("���� �޴� �Դϴ�.");
        }
        return foodSelect;
    }
    
    // ���� �޴� ���ð� �Է� �޼ҵ�
    public int menuSelect() throws MenuSelectException {
        int menuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(menuSelect < 1 || menuSelect > 3) { // 1~3���� ���� �ƴϸ�
            throw new MenuSelectException("�Է� ����");
        }
        return menuSelect;
    }
    
    // ������ �޴� ���ð� �Է� �޼ҵ�
    public int menuManagerSelect() throws MenuSelectException {
        int menuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(menuSelect < 1 || menuSelect > 7) { // 1~7���� ���� �ƴϸ�
            throw new MenuSelectException("�Է� ����");
        }
        return menuSelect;
    }
    
    // ȸ�� �޴� ���ð� �Է� �޼ҵ�
    public int memberMenuSelect() throws MenuSelectException {
        int memberMenuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(memberMenuSelect < 1 || memberMenuSelect > 6) { // 1~6���� ���� �ƴϸ�
            throw new MenuSelectException("�Է� ����");
        }
        return memberMenuSelect;
    }
    
    // ���� �޴� ���ð� �Է� �޼ҵ�
    public int accountMenuSelect() throws MenuSelectException {
        int accountMenuSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(accountMenuSelect < 1 || accountMenuSelect > 3) { // 1~3���� ���� �ƴϸ�
            throw new MenuSelectException("�Է� ����");
        }
        return accountMenuSelect;
    }
    
 // ���� �޴� ���ð� �Է� �޼ҵ�
    public int memberInputSelect() throws MenuSelectException {
        int MemberInputSelect = Integer.parseInt(MenuViewer.sc.nextLine());
        if(MemberInputSelect < 1 || MemberInputSelect > 2) { // 1~2���� ���� �ƴϸ�
            throw new MenuSelectException("�Է� ����");
        }
        return MemberInputSelect;
    }
    
    //---------------------------------------//
    // [FILE IO �޼ҵ�] //
    
    // ���������� ���� �޼ҵ�
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
    
    // ���������� �б� �޼ҵ�
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
