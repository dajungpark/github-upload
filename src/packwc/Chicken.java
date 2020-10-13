package packwc;

import java.io.Serializable;
import java.util.Objects;

public class Chicken implements Serializable {
	int name;
	int price;
	int quantity;
	int isSunSal;
	
	Chicken(int name, int quantity, int isSunSal){
		switch(name) {
		case 1:
			price = 15000;
			break;
		case 2:
			price = 21000;
			break;
		case 3:
			price = 19000;
			break;
		case 4:
			price = 16000;
			break;
		case 5:
			price = 23000;
			break;
		}
		
		this.name = name;
		this.quantity = quantity;
		this.isSunSal = isSunSal;
	}

}
