package com.lixing.lixingdemo.controller.dto;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2023/12/24
 */
public class RequestDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tenantId;
    private String userToken;
    private String operatorCode;

    private String ip;
    private Integer pageNo = 1;
    private Integer pageSize = 20;
    private Boolean isNeedPage = true;

    private T param;
    private String userName;
    private String systemFlag;
    private String sysSource;

    public RequestDTO() {
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public String getUserToken() {
        return this.userToken;
    }

    public String getOperatorCode() {
        return this.operatorCode;
    }

    public String getIp() {
        return this.ip;
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public Boolean getIsNeedPage() {
        return this.isNeedPage;
    }


    public T getParam() {
        return this.param;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSystemFlag() {
        return this.systemFlag;
    }

    public String getSysSource() {
        return this.sysSource;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setIsNeedPage(Boolean isNeedPage) {
        this.isNeedPage = isNeedPage;
    }


    public void setParam(T param) {
        this.param = param;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public void setSysSource(String sysSource) {
        this.sysSource = sysSource;
    }

}
