package packwc;

import java.io.Serializable;

public class Account implements Serializable {
	private int in_money;	// ����
	private int out_money;	// ����
	
	void deposit(int in_money) {	// ���� ���
		this.in_money += in_money;
	}
	
	void withdraw(int out_money) {	// ���� ���
		this.out_money += out_money;
	}
	
	int getMaechul() {	// ���� ��ȯ
		return in_money;
	}
	
	int getJichul() {	// ���� ��ȯ
		return out_money;
	}
	
	int getIncome() {	// ������ ��ȯ
		return in_money - out_money;
	}
}