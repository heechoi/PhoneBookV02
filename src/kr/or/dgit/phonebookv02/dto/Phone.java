package kr.or.dgit.phonebookv02.dto;

public class Phone {
	private String name;
	private String addr;
	private String tel;
	
	public Phone(String name, String addr, String tel) {
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}
	
	public Phone(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", name, addr, tel);
	}

	public Object[] toArray() {
		return new Object[]{name,addr,tel};
	}


	
}
