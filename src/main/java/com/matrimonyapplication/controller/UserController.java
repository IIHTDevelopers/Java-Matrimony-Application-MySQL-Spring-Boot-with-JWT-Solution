package com.matrimonyapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrimonyapplication.dto.UserDto;
import com.matrimonyapplication.entity.AuthRequest;
import com.matrimonyapplication.service.impl.JwtService;
import com.matrimonyapplication.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getEmail());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
		UserDto createdUser = userService.registerUser(userDto);
		return ResponseEntity.ok(createdUser);
	}

	@GetMapping("/profile/{userId}")
	public ResponseEntity<UserDto> getUserProfile(@PathVariable Long userId) {
		System.out.println(userId);
		UserDto userDto = userService.getUserProfile(userId);
		return ResponseEntity.ok(userDto);
	}

	@PutMapping("/profile")
	public ResponseEntity<UserDto> updateUserProfile(@RequestParam Long userId, @RequestBody UserDto userDto) {
		UserDto updatedUser = userService.updateUserProfile(userId, userDto);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestParam Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/matches")
	public ResponseEntity<List<UserDto>> findMatches(@RequestParam Long userId) {
		List<UserDto> matches = userService.findMatches(userId);
		return ResponseEntity.ok(matches);
	}
}