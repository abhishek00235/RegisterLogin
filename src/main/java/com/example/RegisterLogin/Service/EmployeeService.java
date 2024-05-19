package com.example.RegisterLogin.Service;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;

import com.example.RegisterLogin.Entity.Employee;
import com.example.RegisterLogin.Repo.EmployeeRepo;
import com.example.RegisterLogin.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeRepo employeeRepo;

	public String addEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub

		Employee employee= new Employee(


				employeeDTO.getEmployeeid(),
				employeeDTO.getEmployeename(),
				employeeDTO.getEmail(),

				this.passwordEncoder.encode(employeeDTO.getPassword())
		);
		employeeRepo.save(employee);

		return employee.getEmployeename();



	}


	public LoginResponse loginEmployee(LoginDTO loginDTO) {
		// TODO Auto-generated method stub

		String msg = "";
		Employee employee1 = employeeRepo.findByEmail(loginDTO.getEmail());
		if (employee1 != null) {
			String password = loginDTO.getPassword();
			String encodedPassword = employee1.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {
				Optional<Employee> employee = employeeRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
				if (employee.isPresent()) {
					return new LoginResponse("Login Success", true);
				} else {
					return new LoginResponse("Login Failed", false);
				}
			} else {
				return new LoginResponse("password Not Match", false);
			}
		}else {
			return new LoginResponse("Email not exits", false);
		}
	}

}
