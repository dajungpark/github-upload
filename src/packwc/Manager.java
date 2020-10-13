package packwc;

public class Manager extends Member {
	String pwd;

	public Manager(Member m, int money) {
		super(m, money);
	}

	public Manager(String id, String pwd, String name, String address, String phoneNum, int age) {
		super(id, name, address, phoneNum, age);
		this.pwd = pwd;

	}
	
	@Override
	public String toString() {
		return "[관리자]ID:"+id+", 이름:"+name+", 주소:"+address+", 전화번호:"+phoneNum+", 나이:"+age;
	}
}