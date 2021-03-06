package com.hailu.cloud.api.merchant.module.merchant.model;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 店铺功能表
 *
 * @author zhijie
 */
@Data
@ApiModel
public class McStoreFunctionModel {

    /**
     * 店铺功能表id
     */
    @ApiModelProperty("店铺功能表id")
    private Long id;

    /**
     * 店铺表id
     */
    @ApiModelProperty(value = "店铺表id",required = true,dataType = "long")
    private Long storeId;

    /**
     * 是否预定：1、开启预定；2、关闭预定
     */
    @ApiModelProperty("是否预定：1、开启预定；2、关闭预定")
    private Integer reserveStatus;

    /**
     * 是否开启自动接单：1-开启自动接单；2-关闭自动接单
     */
    @ApiModelProperty("是否开启自动接单：1-开启自动接单；2-关闭自动接单")
    private Integer automaticStatus;

    /**
     * 相同时段最高接单数
     */
    @ApiModelProperty("相同时段最高接单数")
    private Integer maxOrderNum;

    /**
     * 预约保留时间（单位：分钟）
     */
    @ApiModelProperty("预约保留时间（单位：分钟）")
    private Integer appointmentSaveTime;


}
