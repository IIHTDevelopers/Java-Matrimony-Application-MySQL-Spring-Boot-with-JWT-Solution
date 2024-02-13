package com.matrimonyapplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.matrimonyapplication.dto.UserDto;
import com.matrimonyapplication.entity.User;
import com.matrimonyapplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Lazy
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userDetail = userRepository.findByName(username);
		return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	public UserDto authenticateUser(String email, String password) {
		// This method should be implemented with your authentication logic
		throw new UnsupportedOperationException("Authentication logic needs to be implemented.");
	}

	public UserDto getUserProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
		return convertToDto(user);
	}

	public UserDto updateUserProfile(Long userId, UserDto userDto) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
		user.setName(userDto.getName());
		user.setDob(userDto.getDob());
		user.setGender(userDto.getGender());
		User updatedUser = userRepository.save(user);
		return convertToDto(updatedUser);
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	public List<UserDto> findMatches(Long userId) {
		throw new UnsupportedOperationException("Matching logic needs to be implemented.");
	}

	public UserDto registerUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		User user = userDtoToUser(userDto);
		user = userRepository.save(user);
		UserDto userDTO = convertToDto(user);
		return userDTO;
	}

	public static UserDto convertToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setDob(user.getDob());
		userDto.setEmail(user.getEmail());
		userDto.setGender(user.getGender());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setRoles(user.getRoles());
		return userDto;
	}

	public static User userDtoToUser(UserDto user) {
		User userDto = new User();
		userDto.setId(user.getId());
		userDto.setDob(user.getDob());
		userDto.setEmail(user.getEmail());
		userDto.setGender(user.getGender());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setRoles(user.getRoles());
		return userDto;
	}
}