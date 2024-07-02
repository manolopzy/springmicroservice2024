package com.worldexplorers.happylearning.gateway.authorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.worldexplorers.happylearning.gateway.authorization.user.RegistrationUser;
import com.worldexplorers.happylearning.gateway.authorization.user.User;

import reactor.core.publisher.Mono;

//@CrossOrigin//(origins = "*")
@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
	
	@GetMapping("/signin")
	public ResponseEntity<Mono<String>> helloWorld() {
		return new ResponseEntity<Mono<String>>(Mono.just("hello world"), HttpStatus.OK);
	}
	@PostMapping("/signup")
	public ResponseEntity<Mono<User>> signup(@RequestBody User user) {
		System.out.println("signup = " + user);
		return new ResponseEntity<Mono<User>>(HttpStatus.OK);
	}
	@PostMapping(path = "/signin")
	public ResponseEntity<Mono<AuthenticatedUser>> signin(@RequestBody RegistrationUser user) {
		System.out.println("user = " + user);
		AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);
		authenticatedUser.setJwt("lsdkjfldskjflk");
		return new ResponseEntity<Mono<AuthenticatedUser>>(Mono.just(authenticatedUser), HttpStatus.OK);
	}

	
//	@Autowired
//	  private EmployeeService employeeService;
//	 
//	  @RequestMapping(value = { "/create", "/" }, method = RequestMethod.POST)
//	  @ResponseStatus(HttpStatus.CREATED)
//	  public void create(@RequestBody Employee e) {
//	    employeeService.create(e);
//	  }
//	 
//	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	  public ResponseEntity<Mono<Employee>> findById(@PathVariable("id") Integer id) {
//	    Mono<Employee> e = employeeService.findById(id);
//	    return new ResponseEntity<Mono<Employee>>(e, HttpStatus.OK);
//	  }
//	 
//	  @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
//	  public Flux<Employee> findByName(@PathVariable("name") String name) {
//	    return employeeService.findByName(name);
//	  }
//	 
//	  @RequestMapping(method = RequestMethod.GET, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	  public Flux<Employee> findAll() {
//	    Flux<Employee> emps = employeeService.findAll();
//	    return emps;
//	  }
//	 
//	  @RequestMapping(value = "/update", method = RequestMethod.PUT)
//	  @ResponseStatus(HttpStatus.OK)
//	  public Mono<Employee> update(@RequestBody Employee e) {
//	    return employeeService.update(e);
//	  }
//	 
//	  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	  @ResponseStatus(HttpStatus.OK)
//	  public void delete(@PathVariable("id") Integer id) {
//	    employeeService.delete(id).subscribe();
//	  }
}
