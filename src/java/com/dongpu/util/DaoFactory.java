/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.util;
import com.dongpu.dao.imp.*;
/**
 *
 * @author wy105
 */
public class DaoFactory {
    public static ItmstrDaoImp GetItmstrDaoImp(){
        return new ItmstrDaoImp();
    }
    public static SupDaoImp GetSupDaoImp(){
        return new SupDaoImp();
    }
    public static UserDaoImp GetUserDaoImp(){
        return new UserDaoImp();
    }
}
