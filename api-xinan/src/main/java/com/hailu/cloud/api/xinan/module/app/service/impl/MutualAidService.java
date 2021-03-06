package com.hailu.cloud.api.xinan.module.app.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hailu.cloud.api.xinan.module.app.dao.MutualaidMapper;
import com.hailu.cloud.api.xinan.module.app.entity.MutualAid;
import com.hailu.cloud.api.xinan.module.app.model.MutualAidModel;
import com.hailu.cloud.common.model.page.PageInfoModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: QiuFeng:WANG
 * @Description: 互助表Service
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class MutualAidService {
    @Resource
    private MutualaidMapper mutualAidMapper;

    /**
     * 添加互助信息
     * @param mutualAid
     * @return
     */
    public int insertSelective(MutualAid mutualAid){
        if(mutualAid != null){
            return mutualAidMapper.insertSelective(mutualAid);
        }
        return 0;
    }

    /**
     * 删除互助信息
     * @param numberId
     * @return
     */
    public int deleteByPrimaryKey(String numberId){
        return mutualAidMapper.deleteByPrimaryKey(numberId);
    }

    /**
     * 互助列表
     * @param page
     * @param size
     * @return
     */
    public PageInfoModel<List<MutualAidModel>> findMutualAidList(Integer page, Integer size, Integer rescueType ){
        Page objects = PageHelper.startPage(page, size);
        List<MutualAidModel> data = mutualAidMapper.findMutualaidList(rescueType);
        return new PageInfoModel<>(objects.getPages(), objects.getTotal(), data);
    }


    /**
     * 互助详细信息
     * @mbggenerated 2019-11-12
     */
    public MutualAid findMutualAid(Long numberId){
        return mutualAidMapper.findMutualAid(numberId);
    }

}
