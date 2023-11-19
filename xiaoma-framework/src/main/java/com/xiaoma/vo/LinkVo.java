package com.xiaoma.vo;

import lombok.Data;

@Data
public class LinkVo {

    private String address;
    private String description;
    private Long id;
    private String logo;
    private String name;

    public LinkVo(){}
    public LinkVo(String address, String description, Long id, String logo, String name){
        this.address = address;
        this.description = description;
        this.id = id;
        this.logo = logo;
        this.name = name;
    }

}
