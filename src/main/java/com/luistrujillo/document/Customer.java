package com.luistrujillo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "customer")
public class Customer {
	
	   //identificador
		@Id
		private String id ; 
		
		@Field(name = "firstName")
		//nombre
		private String firstName ; 
		
		@Field(name = "lastname")
		//apellido 
		private String lastname;
		  
		@Field(name = "email")
		//email
		private String email;
		
		
		@Field(name = "telephone")
	    //telefono 
	    private String telephone;
	    
		@Field(name = "customertype")
	    //tipo de cliente 
	    private String customertype;
		
		//nombre del documento  
		@Field(name = "document_number")
		private String documentNumber;
	    
	    
		
		public Customer() {
			
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



		public String getDocumentNumber() {
			return documentNumber;
		}



		public void setDocumentNumber(String documentNumber) {
			this.documentNumber = documentNumber;
		} 
	   
	    
	 
		

}
