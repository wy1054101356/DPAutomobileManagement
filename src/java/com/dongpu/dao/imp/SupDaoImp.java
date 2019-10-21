package com.dongpu.dao.imp;

import com.dongpu.dao.*;
import com.dongpu.model.*;
import com.dongpu.util.DatabaseBean;
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
public class SupDaoImp implements SupDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<Sup> GetAllSup() {
        List<Sup> sups = new ArrayList<Sup>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_SUP");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Sup sup = new Sup();
                sup.setSupId(rs.getString("supId"));
                sup.setSupName(rs.getString("supName"));
                sups.add(sup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return sups;
    }

    @Override
    public Sup GetASup(String supId) {
        Sup sup = new Sup();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_SUP where supId = ?");
            psmt.setString(1, supId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                sup.setSupId(rs.getString("supId"));
                sup.setSupName(rs.getString("supName"));
                return sup;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public boolean InsertSup(Sup sup) {
        try {
            String sql = "insert into TB_SUP values(?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sup.getSupId());
            psmt.setString(2, sup.getSupName());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateSup(Sup sup) {
        try {
            String sql = "update TB_SUP set supName = ? where supId = ? ";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, sup.getSupName());
            psmt.setString(2, sup.getSupId());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteSup(String supId) {
        try {
            String sql = "delete from TB_SUP where supId = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, supId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
