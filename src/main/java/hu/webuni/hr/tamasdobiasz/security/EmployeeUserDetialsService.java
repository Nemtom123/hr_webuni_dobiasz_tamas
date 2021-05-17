package hu.webuni.hr.tamasdobiasz.security;

import hu.webuni.hr.tamasdobiasz.model.EmployeeUser;
import hu.webuni.hr.tamasdobiasz.repository.UserRepository;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeUserDetialsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeUser employeeUser = userRepository.findById(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		
		
		return new User(username, employeeUser.getPassword(), 
				employeeUser.getRoles().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
	}

}
