package packwc;

import java.io.Serializable;
import java.util.Objects;

public class Member implements Serializable {
	String id;
	String name;
	String address;
	String phoneNum;
	int history_money;
	int age;
	int grade;
	
	Member(String id, String name, String address, String phoneNum, int age){
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.age = age;
	}
	
	Member(Member m, int money){
		this.id = m.id;
		this.name = m.name;
		this.address = m.address;
		this.phoneNum = m.phoneNum;
		this.age = m.age;
		this.history_money = m.history_money + money;
		
		if(this.history_money >= 100000) {
			this.grade = 1;
		}else if(this.history_money >= 150000) {
			this.grade = 2;
		}else if(this.history_money >= 200000) {
			this.grade = 3;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		Member tmp = (Member)obj;
		return id.equals(tmp.id);
	}

	@Override
	public String toString() {
		return "ID:"+id+", 이름:"+name+", 주소:"+address+", 전화번호:"+phoneNum+", 나이:"+age+", 등급:"+grade;
	}
}
