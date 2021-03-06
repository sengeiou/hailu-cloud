package com.hailu.cloud.api.admin.module.merchant.controller;

import com.hailu.cloud.api.admin.module.merchant.entity.McStoreInformation;
import com.hailu.cloud.api.admin.module.merchant.model.McStoreInformationModel;
import com.hailu.cloud.api.admin.module.merchant.parmeter.McStoreInformationListParameter;
import com.hailu.cloud.api.admin.module.merchant.parmeter.UpdateMcStoreInformtionPaarameter;
import com.hailu.cloud.api.admin.module.merchant.service.impl.McStoreInformationAdminService;
import com.hailu.cloud.common.exception.BusinessException;
import com.hailu.cloud.common.model.page.PageInfoModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Author: zhangmugui
 * @Description: 店铺管理后台
 */
@RestController
@RequestMapping("/pc/mc-store")
@Validated
@Api(tags = "商户-店铺管理")
@Slf4j
public class McStoreInformationController {


    @Resource
    private McStoreInformationAdminService mcStoreInformationAdminService;


    @ApiOperation(value = "显示店铺信息列表")
    @PostMapping("mcStoreInformationList")
    public PageInfoModel<List<McStoreInformationModel>> selectMcStoreInformationList(@ModelAttribute McStoreInformationListParameter mcStoreInformationListParameter, HttpServletRequest request) {

        mcStoreInformationListParameter.setAccountType(1);
        return mcStoreInformationAdminService.selectLocalCircleEntryList(mcStoreInformationListParameter);

    }


    @ApiOperation(value = "显示店铺信息详情", notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'id': 5819938384165405,\n" +
            "        'mcNumberId': '5819938384165401',\n" +
            "        'shopName': '虾米',\n" +
            "        'phone': '13125676567',\n" +
            "        'provinceCode': '110000',\n" +
            "        'cityCode': '110100',\n" +
            "        'areaCode': '110101',\n" +
            "        'detailAddress': '九号',\n" +
            "        'storeTotalType': 5444781580777984,\n" +
            "        'createdat': '2019-11-26T09:39:08.000+0000',\n" +
            "        'updatedat': '2019-11-26T09:39:08.000+0000',\n" +
            "        'toExamine': 1\n" +
            "    }\n" +
            "}\n"
            + "<pre>")
    @PostMapping("mcStoreInformationDetails")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号ID", required = true, paramType = "query")
    })
    public McStoreInformation selectByPrimaryKey(@NotNull(message = "编号不能为空") Long id) {

        return mcStoreInformationAdminService.findMcStoreInformation(id);
    }


    @ApiOperation(value = "更改店铺审核状态", notes = "<pre>" +
            "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\"\n" +
            "}"
            + "pre")
    @PostMapping("changeMcStoreInformationState")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号id", required = true, paramType = "query"),
            @ApiImplicitParam(name = "toExamine", value = "状态", required = true, paramType = "query")
    })
    public void updateToExamineByNumberId(@NotNull(message = "编号不能为空") Long id, @NotNull(message = "更改的状态不能为空") Integer toExamine) {

        mcStoreInformationAdminService.updateMcStoreInformationStatus(id, toExamine);
    }


    @ApiOperation(value = "更改店铺商家信息", notes = "<pre>" +
            "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\"\n" +
            "}"
            + "pre")
    @PostMapping("updateMcStoreInformation")
    public void updateMcEntryInformation(@ModelAttribute UpdateMcStoreInformtionPaarameter updateMcStoreInformtionPaarameter) throws BusinessException {
        McStoreInformation mcStoreInformation = new McStoreInformation();
        BeanUtils.copyProperties(updateMcStoreInformtionPaarameter, mcStoreInformation);
        mcStoreInformation.setUpdateDateTime(new Date());
        mcStoreInformationAdminService.updateMcStoreInformation(mcStoreInformation);
    }


    @ApiOperation(value = "删除店铺信息", notes = "<pre>" +
            "{\n" +
            "  \"code\": 200,\n" +
            "  \"message\": \"请求成功\"\n" +
            "}"
            + "pre")
    @PostMapping("delMcEntryInformation")
    @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "query")
    public void deleteByPrimaryKey(@NotNull(message = "id编号不能为空") Long id) {
        mcStoreInformationAdminService.deleMcStoreInformation(id);
    }


}
