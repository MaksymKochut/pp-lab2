package patient;

public class Patient {
	private int 	id;
	private String 	name;
	private String 	secondName;
	private String 	middleName;

	private String 	address;
	private String 	phoneNumber;
	private int 	cardNumber;
	private String 	diagnosis;

	public Patient() {}

	// конструктор
	public Patient(int p_id, String p_name, String p_secondName, String p_middleName,
					String p_address, String p_phoneNumber, int p_cardNumber, String p_diagnosis){
		id = p_id;
		name = p_name;
		secondName = p_secondName;
		middleName = p_middleName;
		address = p_address;
		phoneNumber = p_phoneNumber;
		cardNumber = p_cardNumber;
		diagnosis = p_diagnosis;
	}

	// Геттери
	public int getId() { return id; }
	public String getName() { return name; }
	public String getSecondName() { return secondName; }
	public String getMiddleName() { return middleName; }

	public String getAddress() { return address; }
	public String getPhoneNumber() { return phoneNumber; }
	public int getCardNumber() { return cardNumber; }
	public String getDiagnosis() { return diagnosis; }

	// Сеттери
	public void setId(int new_id) { id = new_id; }
	public void setName(String new_name) { name = new_name; }
	public void setSecondName(String new_secondName) { secondName = new_secondName; }
	public void setMiddleName(String new_middleName) { middleName = new_middleName; }

	public void setAddress(String new_address) { address = new_address; }
	public void setPhoneNumber(String new_phoneNumber) { phoneNumber = new_phoneNumber; }
	public void setCardNumber(int new_cardNumber) { cardNumber = new_cardNumber; }
	public void setDiagnosis(String new_diagnosis) { diagnosis = new_diagnosis; }

	public String toString(){
		return String.format("ID: %d, %s %s %s, Address: %s, Phone Number: %s, Card N%d, Diagnosis: %s",
			id, name, middleName, secondName, address, phoneNumber, cardNumber, diagnosis);
	}
}
