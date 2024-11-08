package Models;

public class City {
	private int city_id,state_id;
	private String name,pincode;
	public City(int city_id, String name, String pincode,int state_id) {
		super();
		this.city_id = city_id;
		this.name = name;
		this.pincode = pincode;
		this.state_id=state_id;
	}
	
	public City(int city_id,String name,int state_id) {
		this.city_id = city_id;
		this.state_id= state_id;
		this.name = name;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
}
