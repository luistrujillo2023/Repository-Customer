package com.luistrujillo.document.dto;

public class CustomerDTO {
	
	private String id ; 
    //nombre
	private String firstName ; 
    //apellido 
	private String lastname;
			  
	//email
	private String email;
    //telefono 
    private String telephone;
    //tipo de cliente 
    private String customertype;
    
	public CustomerDTO() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}
    
    
	        
	        
	        

}
