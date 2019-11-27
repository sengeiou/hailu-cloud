package com.hailu.cloud.api.merchant.module.merchant.model.bak;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class McStoreInformationModel {
    @ApiModelProperty("编号")
    @NotNull(message = "编号不能为空")
    private Long id;


    @ApiModelProperty("商家编号")
    @NotBlank(message = "商家编号不能为空")
    private String mcNumberId;


    @ApiModelProperty("店铺名称")
    private String shopName;


    @ApiModelProperty("店铺联系电话")
    private String phone;


    @ApiModelProperty("省Id")
    private String provinceCode;


    @ApiModelProperty("市Id")
    private String cityCode;


    @ApiModelProperty("区id")
    private String areaCode;


    @ApiModelProperty("店铺详细地址")
    private String detailAddress;


    @ApiModelProperty("店铺详情")
    private String storeDetails;


    @ApiModelProperty("人均价格")
    private java.math.BigDecimal perCapitaPrice;


    @ApiModelProperty("营业状态(1-营业中，2-休息中)")
    private Long businessState;

    @ApiModelProperty("关闭时间")
    private java.util.Date closingTime;


    @ApiModelProperty("开店时间")
    private java.util.Date openingTime;


    @ApiModelProperty("审核(''审核中-1'',''审核通过-2'',''审核不通过-3'')'")
    private Integer toExamine;


    @ApiModelProperty("每周营业日用，“；”隔开（例1；2；3；4:）")
    private String weekDay;
}
