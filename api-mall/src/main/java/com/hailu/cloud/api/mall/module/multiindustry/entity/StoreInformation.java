package com.hailu.cloud.api.mall.module.multiindustry.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class StoreInformation implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商家编号
     */
    private String mcNumberId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺联系电话
     */
    private String phone;

    /**
     * 省Id
     */
    private String provinceCode;

    /**
     * 市Id
     */
    private String cityCode;

    /**
     * 区id
     */
    private String areaCode;

    /**
     * 店铺详细地址
     */
    private String detailAddress;

    /**
     * 店铺详情
     */
    private String storeDetails;

    /**
     * 人均价格
     */
    private BigDecimal perCapitaPrice;

    /**
     * 店铺子类型ID
     */
    private Long storeSonType;

    /**
     * 店铺总类型ID
     */
    private Long storeTotalType;

    /**
     * 营业状态(1-营业中，2-休息中)
     */
    private Long businessState;

    /**
     * 关闭时间
     */
    private Date closingTime;

    /**
     * 开发时间
     */
    private Date openingTime;

    /**
     * 创建时间
     */
    private Date createdat;

    /**
     * 更新时间
     */
    private Date updatedat;

    /**
     * 审核('审核中-1','审核通过-2','审核不通过-3')
     */
    private Long toExamine;

    /**
     * 店铺头像
     */
    private String defaultHead;

    /**
     * 每周营业日用（1星期日，2星期一）
     */
    private String weekDay;

    /**
     * mc_store_information
     */
    private static final long serialVersionUID = 1L;
}