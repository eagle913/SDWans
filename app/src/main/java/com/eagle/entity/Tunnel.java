package com.eagle.entity;

public class Tunnel {
    private String name;
    private String bw;
    private String alias;
    private String secret_key;
    private String iscmpz;
    private String company_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBw() {
        return bw;
    }

    public void setBw(String bw) {
        this.bw = bw;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSecret_key() {
        return secret_key;
    }

    public void setSecret_key(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getIscmpz() {
        return iscmpz;
    }

    public void setIscmpz(String iscmpz) {
        this.iscmpz = iscmpz;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
