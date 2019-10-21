package com.dongpu.dao.imp;

import com.dongpu.dao.*;
import com.dongpu.model.*;
import com.dongpu.util.DatabaseBean;
import com.dongpu.util.MD5;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wy105
 */
public class UserDaoImp implements UserDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<User> GetAllUser() {
        List<User> users = new ArrayList<User>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_USER");
            rs = psmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserName(rs.getString("userName"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return users;
    }

    @Override
    public User GetAUser(String userId) {
        User user = new User();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_USER where userId = ?");
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getString("userId"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserName(rs.getString("userName"));

                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public User Login(String userId, String userPwd) {
        User user = new User();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_USER where userId = ? and userPwd = ?");
            psmt.setString(1, userId);

            psmt.setString(2, MD5.EncryptionStr(userPwd, MD5.MD5));
            rs = psmt.executeQuery();
            if (rs.next()) {
                user.setUserId(rs.getString("userId"));
                user.setUserPwd(rs.getString("userPwd"));
                user.setUserName(rs.getString("userName"));
                return user;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean InsertUser(User user) {
        try {
            String sql = "insert into TB_USER values(?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUserId());
            psmt.setString(2, MD5.EncryptionStr(user.getUserPwd(), MD5.MD5));
            psmt.setString(3, user.getUserName());
            return psmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateUser(User user) {
        try {
            String sql = "update TB_USER set userPwd = ? , userName = ? where userId = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, MD5.EncryptionStr(user.getUserPwd(), MD5.MD5));
            psmt.setString(2, user.getUserName());
            psmt.setString(3, user.getUserId());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteUser(String userId) {
        try {
            String sql = "delete from TB_USER where userId = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, userId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
