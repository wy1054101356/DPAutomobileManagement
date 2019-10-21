/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dongpu.model;

import java.util.List;

/**
 *
 * @author wy105
 */
public class Itmstr {

    private String itmId;//    -- 项目编号
    private String itmPid;//   -- 父项目编号 根项目为NULL
    private String itmName;//  -- 项目名称
    private int seq;//          -- 顺序号
    private int itmCount;//    -- 使用数量
    private String supId;//    -- 供应商编号 外键

    private String itmstrIsParent = "false";

    private String state = "open";
    private Boolean isChecked = false;//是否选中

    private List<Itmstr> children;    //子节点集合

    public Itmstr() {
        itmstrIsParent = "false";
        isChecked = false;
    }

    public Itmstr(String itmId, String itmPid, String itmName, int seq, int itmCount, String supId) {
        this.itmId = itmId;
        this.itmPid = itmPid;
        this.itmName = itmName;
        this.seq = seq;
        this.itmCount = itmCount;
        this.supId = supId;
        itmstrIsParent = "false";
    }

    public Itmstr(String itmId, String itmPid, String itmName, int seq, int itmCount, String supId, String state, Boolean isChecked) {

        this.itmId = itmId;

        this.itmPid = itmPid;

        this.itmName = itmName;

        this.seq = seq;

        this.itmCount = itmCount;

        this.supId = supId;

        this.state = state;

        this.isChecked = isChecked;

    }

    public String getItmId() {
        return itmId;
    }

    public void setItmId(String itmId) {
        this.itmId = itmId;
    }

    public String getItmPid() {
        return itmPid;
    }

    public void setItmPid(String itmPid) {
        this.itmPid = itmPid;
    }

    public String getItmName() {
        return itmName;
    }

    public void setItmName(String itmName) {
        this.itmName = itmName;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getItmCount() {
        return itmCount;
    }

    public void setItmCount(int itmCount) {
        this.itmCount = itmCount;
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getItmstrIsParent() {
        return itmstrIsParent;
    }

    public void setItmstrIsParent(String itmstrIsParent) {
        this.itmstrIsParent = itmstrIsParent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    public List<Itmstr> getChildren() {
        return children;
    }

    public void setChildren(List<Itmstr> children) {
        this.children = children;
    }

}
