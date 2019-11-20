package com.hailu.cloud.api.merchant.module.dao;

import com.hailu.cloud.api.merchant.module.entity.McEntryInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McEntryInformationMapper {
    /**
     *
     * @mbggenerated 2019-11-19
     */
    int deleteByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insert(McEntryInformation record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int insertSelective(McEntryInformation record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    McEntryInformation selectByPrimaryKey(String numberId);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKeySelective(McEntryInformation record);

    /**
     *
     * @mbggenerated 2019-11-19
     */
    int updateByPrimaryKey(McEntryInformation record);

     /**
     * 查询审核是否通过
     * @param memberid
     * @param toexamine
     * @return
     */
    int selectByMcnumberidAndToexamine(@Param("memberid") String memberid, @Param("toexamine") Integer toexamine);


    /**
     * 查询列表
     * @param shopname
     * @param phone
     * @return
     */
    List<McEntryInformation> selectMcEntryinFormationList(@Param("shopname") String shopname, @Param("phone") String phone);

    /**
     * 根据商户id查询入驻信息是否存在
     * @param mcnumberid
     * @return
     */
    int selectMcEntryinFormationById(@Param("mcnumberid") String mcnumberid);

}