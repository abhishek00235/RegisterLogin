package com.example.RegisterLogin.Repo;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.RegisterLogin.Entity.Employee;

@EnableJpaRepositories
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{

	Optional findOneByEmailAndPassword(String email, String encodedPassword);

	Employee findByEmail(String email);

	

}
