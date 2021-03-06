package com.hailu.cloud.api.merchant.module.merchant.dao;

import com.hailu.cloud.api.merchant.module.merchant.entity.McStoreFunction;
import com.hailu.cloud.api.merchant.module.merchant.model.McStoreFunctionModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McStoreFunctionMapper {


    /**
     * 插入数据
     * @param mcStoreFunction
     * @return
     */
    int  insertSelective(McStoreFunction mcStoreFunction);

    /**
     * 更新数据
     * @param mcStoreFunction
     * @return
     */
    int updateByObject(McStoreFunction mcStoreFunction);

    /**
     * 根据参数查数列
     * @param parameter
     * @return
     */
    List<McStoreFunctionModel> findListByParameter(Object parameter);


    /**
     * 根据店铺id找数据
     * @param storeId
     * @return
     */
    McStoreFunction findObjectByStoreId(@Param("storeId") Long storeId);



}
