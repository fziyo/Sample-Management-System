package com.fziyo.sms.model.dto;

import lombok.Data;

import java.time.Year;

@Data
public class AssetUpdateDto {
    private Integer id;
    private String name;
    private String model;
    private Integer categoryId;
    private Integer teamId;
    private Integer ownerId;
    private String macAddr;
    private Year releaseYear;
    private Integer assetCondition;
    private Integer commercialStatus;
    private Integer isActive;
    private Integer status;
    private Integer currentUserId;
    private String location;
    private String description;
}
