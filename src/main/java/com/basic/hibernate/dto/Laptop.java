package com.basic.hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Laptop {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
		private String companyName;
		private int ramSize;
		
		@ManyToOne()
		private User user;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public int getRamSize() {
			return ramSize;
		}
		public void setRamSize(int ramSize) {
			this.ramSize = ramSize;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return "Laptop [id=" + id + ", companyName=" + companyName + ", ramSize=" + ramSize + "]";
		}
		
		
}
