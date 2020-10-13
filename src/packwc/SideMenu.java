package packwc;

import java.io.Serializable;

public class SideMenu implements Serializable {
	int name;
	int quantity;
	int price;
	
	SideMenu(int name, int quantity){
		switch(name) {
			case 1:
				price = 500;
				break;
			case 2:
				price = 500;
				break;
			case 3:
				price = 3000;
				break;
			case 4:
				price = 2000;
				break;
			case 5:
				price = 2000;
				break;
			case 6:
				price = 4000;
				break;
		}
		
		this.name = name;
		this.quantity = quantity;
	}
}
