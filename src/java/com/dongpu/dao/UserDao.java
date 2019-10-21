package com.dongpu.dao;

import com.dongpu.model.User;
import java.util.List;

/**
 *
 * @author wy105
 */
public interface UserDao {

    public List<User> GetAllUser();

    public User GetAUser(String userId);

    public User Login(String userId, String userPwd);

    public boolean InsertUser(User user);

    public boolean UpdateUser(User user);

    public boolean DeleteUser(String userId);

}
