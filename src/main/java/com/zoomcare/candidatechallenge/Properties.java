package com.zoomcare.candidatechallenge;
public class Properties {

    private long Id;
    private String key;
    private String value;

    public Properties(){
        this.Id = 0;
    }
    
    public Properties(long id, String val, String key) {
        this.Id = id;
        this.value = val;
        this.key = key;
    }

    public void setEmployeeId(long id){
        this.Id = id;
    }

    public void setKey(String key){
        this.key = key;
    }

    public void setValue(String val){
        this.value = val;
    }

    public long getEmployeeId() {
        return this.Id;
    }

    public String getValue() {
        return this.value;
    }

    public String getKey(){
        return this.key;
    }
}
