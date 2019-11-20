package com.hailu.cloud.api.merchant.module.entity;

import lombok.Data;

/**
 * 商品参数
 * @author leiqi
 *
 */
@Data
public class GoodsParameterVo {
	private int id;
	private int goodsId;
	private String parameterName;//参数名称
	private String parameterValue;//参数值
}
