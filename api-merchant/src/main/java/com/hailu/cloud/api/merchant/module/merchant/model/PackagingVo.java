package com.hailu.cloud.api.merchant.module.merchant.model;

import lombok.Data;

/**
 * 包装售后
 *
 * @author leiqi
 */
@Data
public class PackagingVo {
    private int id;
    private int goodsId;
    private String name;//名称
    private String context;//内容

}
