package com.fziyo.sms.model.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Permission Table
 * t_permission
 */
@Data
public class Permission implements Serializable {
    private Integer id;

    /**
     * Permission Name
     */
    private String name;

    /**
     * Permission Code (e.g. asset:list)
     */
    private String code;

    private String url;

    /**
     * 1=menu 2=button
     */
    private Byte type;

    private Integer parentId;

    /**
     * Order
     */
    private Integer orderNo;

    /**
     * Menu Icon
     */
    private String icon;

    /**
     * Frontend Component
     */
    private String component;

    private static final long serialVersionUID = 1L;
}