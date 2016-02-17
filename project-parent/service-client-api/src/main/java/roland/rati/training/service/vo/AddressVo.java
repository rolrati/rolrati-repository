package roland.rati.training.service.vo;

import java.io.Serializable;

public class AddressVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private int zipcode;
	private String city;
	private String street;
	private int number;

	public AddressVo() {
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "AddressVo [zipcode=" + zipcode + ", city=" + city + ", street="
				+ street + ", number=" + number + "], id=" + id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
