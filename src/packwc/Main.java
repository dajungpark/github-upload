package packwc;

public class Main {
	public static void main(String[] args) {
		ChickenManager cm = new ChickenManager();
		
		while(true) {
			MenuViewer.showMenu();	// ���θ޴�
			
			try {
				int menuSelect = cm.menuSelect();
				
				// ���θ޴� ���� ����ġ��
				switch(menuSelect) {
					case 1:
						cm.order();	// �ֹ�
						break;
					case 2:
						boolean flag = false;
						flag = cm.manager_mode();		// ������ �α��� (������ true)
						while(flag) {
							MenuViewer.showManagerMenu();	// �����ڸ޴�
							int managerSelect = cm.menuManagerSelect();
							switch(managerSelect) {
								case 1:
									cm.memberMenu();	// ȸ������
									break;
								case 2:
									cm.showAccount();	// �������
									break;
								case 3:
									cm.order_ID();		// �ֹ�����
									break;
								case 4:
									cm.ings.showIngs(); // �����Ȳ
									break;
								case 5:
									cm.buyIngs();		// ��ᱸ��
									break;
								case 6:
									cm.writeDataFile();	// ���
									break;
								case 7:
									flag = false;		// ���θ޴���
									break;
							}
						}
						break;
					case 3:
						System.out.println("���α׷��� �����մϴ�.");
						cm.writeDataFile();
						return;
				}
			}catch(MenuSelectException e) {
				System.err.println(e.getMessage());
            }catch(Exception e) {
                System.err.println("�Է� ����");
            }
		}
	}
}
