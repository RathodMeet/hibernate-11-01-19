package com.basic.hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vehicle {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		private String name;
		private int number;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		@Override
		public String toString() {
			return "Vehicle [name=" + name + ", number=" + number + "]";
		}
}
