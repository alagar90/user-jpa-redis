package com.demo.user.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.demo.user.model.User;


public interface UserRepository extends PagingAndSortingRepository<User, String> {

	//List<User> findByLastName(@Param("name") String name);
	User findByEmailId(String emailId);

}
