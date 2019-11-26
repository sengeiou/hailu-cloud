package com.hailu.cloud.api.admin.module.xinan.controller;

import com.hailu.cloud.api.admin.module.xinan.service.impl.RescueInfoServiceBackstage;
import com.hailu.cloud.api.admin.module.xinan.service.impl.RescueServiceBackstage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/pc/xinAnBackstage")
@Api(tags = "心安-救助-管理后台")
public class RescueContorller {

    @Autowired
    private RescueInfoServiceBackstage rescueInfoServiceBackstage;

    @Autowired
    private RescueServiceBackstage rescueServiceBackstage;


    @ApiOperation(value = "救助详情", notes = "<pre>" +
            "{\n" +
            "  'code': 0,\n" +
            "  'msg': '成功',\n" +
            "  'data': [\n" +
            "    {\n" +
            "      'instructions': '太爽了',                                     //救助详细说明\n" +
            "      'targetAmount': 10000000,                                    //目标金额\n" +
            "      'numberId': '1362898073',                                    //救助编号\n" +
            "      'cityId': null,                                              //城市Id\n" +
            "      'title': '好嗨哟',                                            //救助标题\n" +
            "      'provinceId': null,                                          //省份Id\n" +
            "      'rescueType': null,                                          //救助类型\n" +
            "      'helpTimes': null,                                           //帮助次数\n" +
            "      'examine': 审核中,                                             //审核\n" +
            "      'createdat': '2019-11-14 16:33:37',                          //创建时间\n" +
            "      'cash': null,                                                //现金额\n" +
            "      'imageList': [                                               //图片数组\n" +
            "        '/aadd/sdsd/rrr.jpg',\n" +
            "        '/aadd/sdsd/qqq.jpg',\n" +
            "        '/aadd/sdsd/www.jpg',\n" +
            "        '/aadd/sdsd/aaa.jpg'\n" +
            "      ],\n" +
            "      'memberId': '5075b82803bc4065975fbb545a89a91a',              //发起用户编号\n" +
            "      'updatedat': '2019-11-14 16:33:37'                           //更新时间\n" +
            "    }\n" +
            "  ],\n" +
            "  'serverTime': 1573799041109\n" +
            "}</pre>")
    @GetMapping("/rescueDetails")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "救助编号", required = true, paramType = "query", dataType = "String")
    })
    public Object findRescue(@NotBlank(message = "救助编号不能为空") String numberId) {
        return rescueInfoServiceBackstage.findRescue(numberId);
    }

    @ApiOperation(notes = "<pre>" +
            "{\n" +
            "    'code': 200,\n" +
            "    'message': '请求成功',\n" +
            "    'data': {\n" +
            "        'totalPage': 1,\n" +
            "        'total': 1,\n" +
            "        'datas': [{\n" +
            "                'numberId': '1362898073',\n" +
            "                'memberId': '5075b82803bc4065975fbb545a89a91a',    // 救助编号\n" +
            "                'targetAmount': 10000000.00,                       // 目标金额\n" +
            "                'title': '好嗨哟',                                  // 标题\n" +
            "                'examine': '审核通过',                              // 审核状态\n" +
            "                'createdat': '2019-11-14T08:33:37.000+0000',\n" +
            "                'updatedat': '2019-11-14T08:33:37.000+0000',\n" +
            "                'instructions': '太爽了'                            // 说明\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}" +
            "</pre>", value = "救助审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", defaultValue = "1", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", defaultValue = "10", paramType = "query", dataType = "String"),
    })
    @PostMapping("/rescueList")
    @ResponseBody
    public Object findXaRescueList(
            @Pattern(regexp = "^\\d*$", message = "请输入数字")
            @RequestParam(name = "pageNum", defaultValue = "1") String pageNum,
            @Range(min = 10, max = 200, message = "每页显示数量只能在10~200之间")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return rescueServiceBackstage.findXaRescueListAll(Integer.parseInt(pageNum), pageSize);
    }

    @ApiOperation(value = "更改救助审核状态", notes = "<pre>" +
            "{\n" +
            "  \"errno\": 0,\n" +
            "  \"errmsg\": \"成功\"\n" +
            "}</pre>")
    @PostMapping("/changeState")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "numberId", value = "救助编号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "examine", value = "审核(2-审核通过、3-审核不通过)", required = true, paramType = "query", dataType = "String")
    })
    public void updateExamineByNumberId(
            @NotBlank(message = "编号不能为空") String numberId,
            @NotBlank(message = "审核状态不能为空") String examine) {

        rescueServiceBackstage.updateByPrimaryKeySelective(numberId, examine);
    }

}
