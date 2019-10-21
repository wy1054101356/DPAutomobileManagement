package com.dongpu.dao;

import com.dongpu.model.*;
import com.dongpu.util.Pagination;
import java.util.List;

/**
 *
 * @author wy105
 */
public interface ItmstrDao {

    public List<Itmstr> GetAllItmstr();

    public List<Itmstr> GetAllItmstr(Pagination pagination);

    public List<Itmstr> GetAllItmstr(int mid, Pagination pagination);

    public void ProcessInsert(List<Itmstr> itmstrs);

    public List<Itmstr> GetItmstrsBySupId(String supId);

    public List<Itmstr> GetChildItmstr(String itmId);

    public Itmstr GetAItmstr(String itmId);

    public Itmstr GetParentItmstr(String itmId);

    public List<Itmstr> GetItmstrTree(String itmId);

    public String showTree(List<Itmstr> itmstrList);

    public boolean InsertItmstr(Itmstr itmstr);

    public boolean UpdateItmstr(Itmstr itmstr);

    public boolean DeleteItmstr(String itmId);
}
