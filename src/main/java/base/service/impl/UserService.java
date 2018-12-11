package base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import base.dao.Location_Has_Context_Repository;
import base.dao.Location_Has_User_Repository;
import base.dao.UserRepository;
import base.po.Context;
import base.po.Location;
import base.po.Location_Has_Context;
import base.po.Location_Has_User;
import base.po.User;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	Location_Has_User_Repository location_Has_User_Repository;
	
	public long getNumberOfUser() {
		return userRepository.count();
	}
	
	public User createUser(User u) {
		return userRepository.save(u);
	}
	
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User findLocationById(Long id) {
		return userRepository.findOne(id);
	}

	public void deleteall(){
		userRepository.deleteAll();
	}
	public List<User> findByUserName(String userName){
		return userRepository.findByUserName(userName);
	}
	public List<User> findByUserNameAndDescribe(String userName,String describe){
		return userRepository.findByUserNameAndDescribe(userName, describe);
	}
	
	
	public void addUser(Location location,User user,int num){
		Location_Has_User location_Has_User=new Location_Has_User();
		location_Has_User.setUser(user);
		location_Has_User.setLocation(location);
		location_Has_User.setNumber(num);
		location_Has_User_Repository.save(location_Has_User);
	}
	
	public void addUser(Location location,User user){
		Location_Has_User location_Has_User=new Location_Has_User();
		location_Has_User.setUser(user);
		location_Has_User.setLocation(location);
		location_Has_User_Repository.save(location_Has_User);
	}
}
