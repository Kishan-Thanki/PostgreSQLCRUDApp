package Models;

public class Migrants {
	private int migrants_id,birthplace_id;
	private String first_name,middle_name,last_name,birthdate,contact_no,email,gender;
	private boolean is_married;
	
	public Migrants(int migrants_id,int birthplace_id, String first_name, String middle_name, String last_name, String birthdate,
			String contact_no, String email, String gender, boolean is_married) {
		super();
		this.migrants_id = migrants_id;
		this.birthplace_id = birthplace_id;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.birthdate = birthdate;
		this.contact_no = contact_no;
		this.email = email;
		this.gender = gender;
		this.is_married = is_married;
	}
	public int getMigrants_id() {
		return migrants_id;
	}
	public void setMigrants_id(int migrants_id) {
		this.migrants_id = migrants_id;
	}
	public int getBirthplace_id() {
		return birthplace_id;
	}
	public void setBirthplace_id(int birthplace_id) {
		this.birthplace_id = birthplace_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public boolean isIs_married() {
		return is_married;
	}
	public void setIs_married(boolean is_married) {
		this.is_married = is_married;
	}

	
}