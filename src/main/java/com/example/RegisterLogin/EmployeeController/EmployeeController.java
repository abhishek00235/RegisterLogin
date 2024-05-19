package com.example.RegisterLogin.EmployeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RegisterLogin.DTO.EmployeeDTO;
import com.example.RegisterLogin.DTO.LoginDTO;
import com.example.RegisterLogin.Service.EmployeeService;
import com.example.RegisterLogin.response.LoginResponse;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
		try {
			String result = employeeService.addEmployee(employeeDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee: " + e.getMessage());
		}
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDTO loginDTO) {
		try {
			LoginResponse loginResponse = employeeService.loginEmployee(loginDTO);
			return ResponseEntity.ok(loginResponse);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}
}

