package numbers2;

public class PhoneBookVO {
	private Long Id;
	private String Name;
	private String Hp;
	private String Tel;
	
	
	public PhoneBookVO() {
		
	}
	public PhoneBookVO(Long id) {
		Id = id;
	}

	public PhoneBookVO(String name, String hp, String tel) {
		Name = name;
		Hp = hp;
		Tel = tel;
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		this.Id = id;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getPhonenum() {
		return Hp;
	}

	public void setPhonenum(String hp) {
		this.Hp = hp;
	}

	public String getHomenum() {
		return Tel;
	}

	public void setHomenum(String tel) {
		this.Tel = tel;
	}
	@Override
	public String toString() {
		return "Phone [name="+Name+", Hp="+Hp+", Tel="+Tel;
	}
}
