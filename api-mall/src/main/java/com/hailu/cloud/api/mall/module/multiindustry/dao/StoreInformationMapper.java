package com.hailu.cloud.api.mall.module.multiindustry.dao;

import com.hailu.cloud.api.mall.module.multiindustry.entity.StoreInformation;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationListResult;
import com.hailu.cloud.api.mall.module.multiindustry.model.StoreInformationResultModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreInformationMapper {

    /**
     * 分类查询商铺列表
     * @param storeTotalType
     * @param storeSonType
     * @return
     */
    List<StoreInformationListResult> findStoreInformationList(
            @Param("storeTotalType") Long storeTotalType,
            @Param("storeSonType") Long storeSonType,
            @Param("cityCode") String cityCode,
            @Param("areaCode") String areaCode,
            @Param("tagId") Integer tagId,
            @Param("shopName") String shopName);

    /**
     * 店铺详细信息
     * @param id
     * @return
     */
    StoreInformation findStoreInformation(@Param("id") Long id);


    /**
     *店铺详细信息连相册
     */
    StoreInformationResultModel findStoreInformationLeftAlbum(@Param("id") Long id);


}