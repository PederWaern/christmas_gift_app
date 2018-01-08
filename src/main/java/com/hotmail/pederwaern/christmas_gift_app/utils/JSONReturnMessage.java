package com.hotmail.pederwaern.christmas_gift_app.utils;

public class JSONReturnMessage {
    private int id;
    private String type;
    public JSONReturnMessage(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public String format(){
        return String.format("%s with Id: %d was deleted", type, id);
    }

}
