package base.dao;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import base.po.Device;
import base.po.User;

public interface UserRepository extends GraphRepository<User>{
	List<User> findByUserName(String userName);
	List<User> findByUserNameAndDescribe(String userName,String describe);
}
