package com.hailu.cloud.api.merchant.module.merchant.service.impl;

import com.hailu.cloud.api.merchant.module.merchant.dao.McEntryInformationMapper;
import com.hailu.cloud.api.merchant.module.merchant.entity.McEntryInformation;
import com.hailu.cloud.api.merchant.module.merchant.eunms.Mceunm;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.feigns.BasicFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: QiuFeng:WANG
 * @Description: 商户入驻模块
 * @Date: 16:32 2019/11/2 0002
 */
@Service
public class McEntryinFormationService {

    @Resource
    private McEntryInformationMapper mcEntryinFormationMapper;

    @Autowired
    private BasicFeignClient basicFeignClient;

    /**
     * 添加入驻信息
     * @param mcEntryinFormation
     * @return
     */
    public Object insertSelective(McEntryInformation mcEntryinFormation) throws BusinessException {
        if (mcEntryinFormation == null) {
            throw new BusinessException("入驻信息为空");
        }
        boolean boo = selectMcEntryinFormationById(mcEntryinFormation.getMcNumberId());
        if (boo){
            throw new BusinessException("入驻信息以填写");
        }
        //生成时间戳
        long time = System.currentTimeMillis();
        //生成随机ID
        String numberid = String.valueOf(basicFeignClient.uuid().getData());
        mcEntryinFormation.setNumberId(numberid);
        mcEntryinFormation.setDateTime(time);
        mcEntryinFormation.setUpdateDateTime(time);
        mcEntryinFormation.setToExamine(Mceunm.IN_AUDIT.getKey());
        int result = mcEntryinFormationMapper.insertSelective(mcEntryinFormation);
        if (result > 0) {
            Map<String, Object> stringObjectMap = new HashMap<>(1);
            stringObjectMap.put("numberId",numberid);
            return stringObjectMap;
        }
        throw new BusinessException("插入数据失败");
    }


    /**
     * 入驻信息详情
     * @param numberId
     * @return
     */
    public Object selectByPrimaryKey(String numberId){
        return mcEntryinFormationMapper.selectByPrimaryKey(numberId);
    }

    /**
     * 更改审核信息
     * @param mcEntryinFormation
     * @return
     */
    public void updateMcEntryInformation(McEntryInformation mcEntryinFormation){
        mcEntryinFormation.setUpdateDateTime(System.currentTimeMillis());
        mcEntryinFormation.setToExamine(1);
        mcEntryinFormation.setNumberId(null);
        mcEntryinFormationMapper.updateByPrimaryKeySelective(mcEntryinFormation);
    }

    /**
     * 查询入驻信息是否存在
     * @param mcnumberid
     * @return
     */
    public boolean selectMcEntryinFormationById(String mcnumberid){
        if (mcnumberid.isEmpty()){
            return true;
        }
        int result = mcEntryinFormationMapper.selectMcEntryinFormationById(mcnumberid);
        if (result == 0) {
            return false;
        }
        return true;
    }


}
