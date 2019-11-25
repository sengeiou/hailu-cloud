package com.hailu.cloud.api.merchant.module.merchant.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangmugui
 */
@Data
public class McEntryInformation implements Serializable {
    /**
     * 编号
     */
    private String numberId;

    /**
     * 商家编号
     */
    private String mcNumberId;

    /**
     * 商户类型 1、个体店 ； 2、个体工商店
     */
    private Integer mcType;

    /**
     * 店铺名称
     */
    private String shopName;


    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 身份证正面
     */
    private String idcardImgx;

    /**
     * 身份证反面
     */
    private String idcardImgy;

    /**
     * 身份证有效期
     */
    private String idcardtermofValidity;

    /**
     * 身份证是否为长期
     */
    private Boolean longTermCertificate;

    /**
     * 经营类型表一级编号id
     */
    private Integer firstManagementTypeId;



    /**
     * 经营类型表二级编号id
     */
    private Integer secondManagementTypeId;



    /**
     * 营业执照注册号
     */
    private String businessLicenseNumber;

    /**
     * 执照名称
     */
    private String businessName;

    /**
     * 法人姓名
     */
    private String nameOfLegalPerson;

    /**
     * 执照有效日期
     */
    private String licenseDate;

    /**
     * 营业执照是否为长期
     */
    private Boolean longTermLicense;

    /**
     * 营业执照正面照
     */
    private String licensePositive;

    /**
     * 第三方链接
     */
    private String thirdPartyLinks;

    /**
     * 入驻邀请码
     */
    private String invitationCode;

    /**
     * 是否为服务商
     */
    private Boolean serviceProviderOrNot;

    /**
     * 审核
     */
    private Integer toExamine;

    /**
     * 店铺市Id
     */
    private Long cityCode;

    /**
     * 店铺省Id
     */
    private Long provinceCode;

    /**
     * 店铺区Id
     */
    private Long areaCode;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Long createdat;

    /**
     * 更改时间
     */
    private Long updatedat;

    /**
     *开户类型（1、借记卡；2、信用卡 ）
     */
    private Integer bankType;


    /**
     * 开户行
     */
    private String bank;


    /**
     * 银行卡号
     */
    private String bankNumber;

    /**
     * 开户省份code
     */
    private String bankProvince;

    /**
     * 开户市区code
     */
    private String bankCity;

    /**
     * 附件数组（用；隔开）
     */
    private String enclosures;

    /**
     * 1、平台代发；2、商家发；3、各开各票
     */
    private Integer billingMethod;


    /**
     * 开户账号
     */
    private String accountNumber;


    /**
     * mc_entry_information
     */
    private static final long serialVersionUID = 1L;
}