package com.hailu.cloud.common.model.auth;

import com.hailu.cloud.common.model.merchant.StoreInformationModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商户账号共用一个账号体系，用于登录时返回给客户端的登录信息
 *
 * @Author xuzhijie
 * @Date 2019/11/6 10:50
 */
@Getter
@Setter
public class MerchantUserLoginInfoModel extends LoginModel {

    /**
     * 编号
     */
    private String numberid;

    /**
     * 登陆账号
     */
    private String landingaccount;

    /**
     * 登陆账号
     */
    private String landingpassword;

    /**
     * 网络名称
     */
    private String networkname;

    /**
     * 账号类型 1、生活圈入驻用户；2、百货入驻用户；3、供应商入驻用户
     */
    private int accounttype;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 入驻审核状态 '0 资料填写  1 审核中','2 审核通过','3 审核不通过
     */
    private Integer toExamine;


    /**
     * 店铺列表
     */
    private List<StoreInformationModel> stores;

}
