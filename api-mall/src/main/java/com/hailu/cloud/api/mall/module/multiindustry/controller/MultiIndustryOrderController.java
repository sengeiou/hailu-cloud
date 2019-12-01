package com.hailu.cloud.api.mall.module.multiindustry.controller;


import com.hailu.cloud.api.mall.module.multiindustry.entity.MultiIndustryOrder;
import com.hailu.cloud.api.mall.module.multiindustry.service.MultiIndustryOrderService;
import com.hailu.cloud.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@RequestMapping("/app/multiIndustryOrder")
@Validated
@Api(tags = "商城-多行业-APP-订单")
@Slf4j
public class MultiIndustryOrderController {

    @Autowired
    private MultiIndustryOrderService orderService;

    @ApiOperation(value = "多行业下单预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "storeId", value = "商品编号(现传店铺编号)",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "会员名称",  required = true,  paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "手机号码",  required = true,  paramType = "query")

    })
    @PostMapping("/placeAnOrder")
    public MultiIndustryOrder addOrder(MultiIndustryOrder order, HttpServletRequest request) throws BusinessException, ParseException {
        return orderService.insertSelective(order, request);
    }


    @ApiOperation(value = "用户查询多行业订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第N页", required = true,  paramType = "query"),
            @ApiImplicitParam(name = "size", value = "页面大小",  required = true,  paramType = "query")
    })
    @PostMapping("/userQueryOrder")
    public Object findOrderListByMemberId(HttpServletRequest request, Integer page ,Integer size){
        return orderService.findOrderListByMemberId(request, page, size);
    }

    @ApiOperation(value = "用户查询订单详情", notes = "<pre>" +
            "{\n" +
            "  'code': 200,\n" +
            "  'message': '请求成功',\n" +
            "  'data': {\n" +
            "    'id': 7319805388471298,                                    //编号\n" +
            "    'orderNumber': '2fa97f3da2d64702a3933e73dd54f496',         //订单号\n" +
            "    'totalType': 5819938384165405,                             //总类型(经营类型编号)\n" +
            "    'memberId': '4f94dca513834de09afd3144aeb2c139',            //商品编号(店铺编号)\n" +
            "    'storeId': 5444781580777984,                               //用户ID\n" +
            "    'productTitle': '虾米',                                     //产品标题\n" +
            "    'memberName': '大红色的发',                                 //会员名称\n" +
            "    'phone': '13129020401',                                    //手机好码\n" +
            "    'purchaseQuantity': '1',                                   //购买数量\n" +
            "    'exchangeCode': '5727160257',                              //兑换码\n" +
            "    'state': 1,                                                //订单状态(未完成-1、已完成-2)\n" +
            "    'stateDisPlay': '未完成',                                   //订单状态(中文)\n" +
            "    'orderTime': '2019-11-30 19:16:43'                         //下单时间\n" +
            "  }\n" +
            "}" +
            "</pre>")
    @ApiImplicitParam(name = "id", value = "编号", required = true,  paramType = "query")
    @PostMapping("/orderDetails")
    public Object findOrderListById(Long id){
        return orderService.selectByPrimaryKey(id);
    }
}
