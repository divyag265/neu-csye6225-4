package neu.edu.Model;

public class CustomerModel {

	private int customerId;
	private String userName;
	private String name;
	private String email;
	private String streetAddress;
	private String city;
	private String state;	
	private String gender;



	public CustomerModel(int customerId, String userName, String name, String email, String streetAddress, String city,
			String state, String gender) {
		super();
		this.customerId = customerId;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.gender = gender;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
