/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.util;

import com.dongpu.model.Itmstr;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wy105
 */
public class FunctionTest {

    public static void main(String[] argg) {
        List<Itmstr> itmstrs = new ArrayList<>();
        itmstrs = DaoFactory.GetItmstrDaoImp().GetAllItmstr();
        System.out.println(DaoFactory.GetItmstrDaoImp().showTree(itmstrs));
    }
}
