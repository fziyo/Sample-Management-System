package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.Year;

@Data
public class AssetCreateDto {
//    private String assetCode;
    private String name;
    private String model;
    private Integer categoryId;
    private Integer team_id;
    private Integer owner_id;
    private String sn;
    private String mac_addr;
    private Year release_year;
    private Integer asset_condition;
    private Integer commercial_status;
    private Integer is_active;
    private Integer status;
    private Integer current_user_id;
    private String location;
    private String description;
}
