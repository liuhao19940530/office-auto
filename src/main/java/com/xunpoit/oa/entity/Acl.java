package com.xunpoit.oa.entity;

public class Acl {
    private Integer id;

    private String mainType;

    private Integer mainId;

    private Integer moduleId;

    private Integer aclState;

    private Integer extendState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType == null ? null : mainType.trim();
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getAclState() {
        return aclState;
    }

    public void setAclState(Integer aclState) {
        this.aclState = aclState;
    }

    public Integer getExtendState() {
        return extendState;
    }

    public void setExtendState(Integer extendState) {
        this.extendState = extendState;
    }
}