package com.dongpu.dao;

import com.dongpu.model.Itmstr;
import com.dongpu.model.Sup;
import java.util.List;

/**
 *
 * @author wy105
 */
public interface SupDao {

    public List<Sup> GetAllSup();

    public Sup GetASup(String supId);

    public boolean InsertSup(Sup sup);

    public boolean UpdateSup(Sup sup);

    public boolean DeleteSup(String supId);
}
