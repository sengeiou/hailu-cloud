package com.hailu.cloud.api.admin.module.xinan.parameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 心安期数统计
 *
 * @author zhijie
 */
@Data
@ApiModel
public class XaStatisticsParameterUpdate {

    /**
     * id
     */
    @ApiParam(name="id",value = "id",required = true)
    private Long id;

    /**
     * 帮助会员（单位：人）
     */
    @ApiParam(name="helpMenber",value = "帮助会员（单位：人）")
    private Integer helpMenber;

    /**
     * 预筹集互助金（单位：万元）
     */
    @ApiParam(name="preMutualCapital",value = " 预筹集互助金（单位：万元）")
    private BigDecimal preMutualCapital;

    /**
     * 分摊人数
     */
    @ApiParam(name="apportionmentNum",value = " 分摊人数")
    private BigDecimal apportionmentNum;

    /**
     * 全员每人预分摊
     */
    @ApiParam(name="averageMoney",value = " 全员每人预分摊")
    private BigDecimal averageMoney;

    /**
     * 本期时间（yyyy-MM）
     */
    @ApiParam(name="timeDate",value = " 本期时间（yyyy-MM）")
    private Date timeDate;

    /**
     * 期数
     */
    @ApiParam(name="timeDate",value = "期数")
    private Integer periodsNumber;



}