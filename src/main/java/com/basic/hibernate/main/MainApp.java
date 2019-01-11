package com.basic.hibernate.main;

import com.basic.hibernate.dto.User;

public class MainApp {

	public static void main(String[] args) {
		
		User user = new User();
		user.setName("name 2");
		user.setSurname("surname 2");
		
		HibernateRepository hibernateRepository = new HibernateRepository();
		/*hibernateRepository.getUserById(2);
		
		hibernateRepository.saveUser(user);*/
		
		/*hibernateRepository.saveUserWithOneToOne();*/
		
		/*hibernateRepository.saveUserByOneToMany();*/
		
		/*hibernateRepository.getUserAndUserListByHQL();*/
		
		/*hibernateRepository.getUniqueUserById();*/
		
		hibernateRepository.getUserListByNativeQuery();
	}

}
