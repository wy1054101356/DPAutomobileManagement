package com.dongpu.model;

/**
 *
 * @author wy105
 */
public class Sup {

    private String supId;   // -- 供应商编号 10-99 主键
    private String supName; // -- 供应商名称
    private String supIsParents = "false";

    public Sup() {
        supIsParents = "false";
    }

    public Sup(String supId, String supName) {
        this.supId = supId;
        this.supName = supName;
        supIsParents = "false";
    }

    public String getSupId() {
        return supId;
    }

    public void setSupId(String supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupIsParents() {
        return supIsParents;
    }

    public void setSupIsParents(String supIsParents) {
        this.supIsParents = supIsParents;
    }

}
