package com.dongpu.dao.imp;

import com.dongpu.dao.*;
import com.dongpu.model.*;
import com.dongpu.util.DatabaseBean;
import com.dongpu.util.Pagination;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wy105
 */
public class ItmstrDaoImp implements ItmstrDao {

    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    CallableStatement cs = null;

    @Override
    public List<Itmstr> GetAllItmstr() {
        List<Itmstr> itmstrs = new ArrayList<Itmstr>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_ITMSTR");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public void ProcessInsert(List<Itmstr> itmstrs) {
        try {
            conn = DatabaseBean.getConnection();
            conn.setAutoCommit(false);
            //构造预处理statement
            psmt = conn.prepareStatement("insert into TB_ITMSTR values(?,?,?,?,?,?)");
            for (int i = 0; i < itmstrs.size(); i++) {
                psmt.setString(1, itmstrs.get(i).getItmId());
                psmt.setString(2, itmstrs.get(i).getItmPid());
                psmt.setString(3, itmstrs.get(i).getItmName());
                psmt.setInt(4, itmstrs.get(i).getSeq());
                psmt.setInt(5, itmstrs.get(i).getItmCount());
                psmt.setString(6, itmstrs.get(i).getSupId());
                psmt.addBatch();

                if (i == itmstrs.size() - 1) {
                    psmt.executeBatch();
                    conn.commit();
                    psmt.clearBatch();
                    //  break;
                }
//                if ((i + 1) % 1000 == 0) {
//                    psmt.executeBatch();
//                    conn.commit();
//                    psmt.clearBatch();
//                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
    }

    @Override
    public List<Itmstr> GetAllItmstr(Pagination pagination) {
        List<Itmstr> itmstrs = new ArrayList<>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select count(*) as counts from TB_ITMSTR");
            rs = psmt.executeQuery();
            rs.next();
            //求总记录数
            pagination.setCountSize(rs.getInt("counts"));
            int start = (pagination.getPageNo() - 1) * pagination.getPageSize() + 1;//开始位置
            int end = pagination.getPageNo() * pagination.getPageSize();//结束位置
            psmt = conn.prepareStatement("SELECT * FROM(SELECT A.*, ROWNUM RN FROM "
                    + "(SELECT * FROM TB_ITMSTR) A WHERE ROWNUM <= ?) WHERE RN >= ?");
            psmt.setInt(1, end);
            psmt.setInt(2, start);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public List<Itmstr> GetAllItmstr(int mid, Pagination pagination) {
        List<Itmstr> itmstrs = new ArrayList<>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select count(*) as counts from TB_ITMSTR");
            rs = psmt.executeQuery();
            rs.next();
            //求总记录数
            pagination.setCountSize(rs.getInt("counts"));
            int start = (pagination.getPageNo() - 1) * pagination.getPageSize() + 1;//开始位置
            int end = pagination.getPageNo() * pagination.getPageSize();//结束位置
            psmt = conn.prepareStatement("SELECT * FROM(SELECT A.*, ROWNUM RN FROM "
                    + "(SELECT * FROM TB_ITMSTR) A WHERE ROWNUM <= ?) WHERE RN >= ?");
            psmt.setInt(1, end);
            psmt.setInt(2, start);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public List<Itmstr> GetChildItmstr(String itmId) {
        List<Itmstr> itmstrs = new ArrayList<Itmstr>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_ITMSTR where itmPid = '" + itmId + "'");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public List<Itmstr> GetItmstrsBySupId(String supId) {
        List<Itmstr> itmstrs = new ArrayList<Itmstr>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_ITMSTR where supId = '" + supId + "'");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public Itmstr GetAItmstr(String itmId) {
        Itmstr itmstr = new Itmstr();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_ITMSTR where itmId = ?");
            psmt.setString(1, itmId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                return itmstr;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return null;
    }

    @Override
    public Itmstr GetParentItmstr(String itmId) {

        Itmstr itmstr = GetAItmstr(itmId);
        if (itmstr != null) {
            Itmstr itmstrParent = GetAItmstr(itmstr.getItmId());
            if (!itmstrParent.getItmPid().equals("NULL")) {
                return itmstrParent;
            }
        }
        return null;
    }

    @Override
    public List<Itmstr> GetItmstrTree(String itmId) {
        List<Itmstr> itmstrs = new ArrayList<Itmstr>();
        try {
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement("select * from TB_ITMSTR start with itmId = '" + itmId + "' connect by prior itmId = itmPid");
            rs = psmt.executeQuery();
            while (rs.next()) {
                Itmstr itmstr = new Itmstr();
                itmstr.setItmId(rs.getString("itmId"));
                itmstr.setItmPid(rs.getString("itmPid"));
                itmstr.setItmName(rs.getString("itmName"));
                itmstr.setSeq(rs.getInt("seq"));
                itmstr.setItmCount(rs.getInt("itmCount"));
                itmstr.setSupId(rs.getString("supId"));

                itmstrs.add(itmstr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return itmstrs;
    }

    @Override
    public String showTree(List<Itmstr> itmstrList) {
        List<Itmstr> nodeList = new LinkedList<Itmstr>();  //存放父节点的list
        String json = null;

        for (Itmstr node1 : itmstrList) {
            boolean isChildMark = false;  //标志该节点是否是子节点，isChildMark为false表示不是子节点
            for (Itmstr node2 : itmstrList) {
                if (node1.getItmPid() != null && node1.getItmPid().equals(node2.getItmId())) {
                    isChildMark = true;
                    if (node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<Itmstr>());
                    }
                    node2.getChildren().add(node1);
                    //node2.setState("closed");
                    break;
                }
            }
            if (!isChildMark) {
                nodeList.add(node1);   //将父节点放到nodeList中
            }

        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            json = mapper.writeValueAsString(nodeList);

        } catch (JsonProcessingException ex) {
            Logger.getLogger(ItmstrDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;

    }

    @Override
    public boolean InsertItmstr(Itmstr itmstr) {
        try {
            String sql = "insert into TB_ITMSTR values(?,?,?,?,?,?)";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, itmstr.getItmId());
            psmt.setString(2, itmstr.getItmPid());
            psmt.setString(3, itmstr.getItmName());
            psmt.setInt(4, itmstr.getSeq());
            psmt.setInt(5, itmstr.getItmCount());
            psmt.setString(6, itmstr.getSupId());
            return psmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean UpdateItmstr(Itmstr itmstr) {
        try {
            String sql = "update TB_ITMSTR set itmPid = ? , itmName = ?, "
                    + "seq = ?, itmCount = ?, supId = ? where itmId = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, itmstr.getItmPid());
            psmt.setString(2, itmstr.getItmName());
            psmt.setInt(3, itmstr.getSeq());
            psmt.setInt(4, itmstr.getItmCount());
            psmt.setString(5, itmstr.getSupId());
            psmt.setString(6, itmstr.getItmId());
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

    @Override
    public boolean DeleteItmstr(String itmId) {
        try {
            String sql = "delete from TB_ITMSTR where itmId = ?";
            conn = DatabaseBean.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, itmId);
            return psmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseBean.close(rs, psmt, conn);
        }
        return false;
    }

}
