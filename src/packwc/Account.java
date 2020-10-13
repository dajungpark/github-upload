package packwc;

import java.io.Serializable;

public class Account implements Serializable {
	private int in_money;	// 수입
	private int out_money;	// 지출
	
	void deposit(int in_money) {	// 수입 계산
		this.in_money += in_money;
	}
	
	void withdraw(int out_money) {	// 지출 계산
		this.out_money += out_money;
	}
	
	int getMaechul() {	// 매출 반환
		return in_money;
	}
	
	int getJichul() {	// 지출 반환
		return out_money;
	}
	
	int getIncome() {	// 순수익 반환
		return in_money - out_money;
	}
}