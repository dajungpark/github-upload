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
		return "[������]ID:"+id+", �̸�:"+name+", �ּ�:"+address+", ��ȭ��ȣ:"+phoneNum+", ����:"+age;
	}
}