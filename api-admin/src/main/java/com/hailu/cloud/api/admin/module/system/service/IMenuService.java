package com.hailu.cloud.api.admin.module.system.service;

import com.hailu.cloud.common.model.page.PageInfoModel;
import com.hailu.cloud.common.model.system.SysMenuModel;
import com.hailu.cloud.common.model.system.SysRoleModel;

import java.util.List;

/**
 * @author xuzhijie
 */
public interface IMenuService {

    /**
     * 添加账号
     *
     * @param model
     */
    void addMenu(SysMenuModel model);

    /**
     * 查询菜单列表
     *
     * @param menuName     角色名称
     * @param enableStatus 启用状态
     * @param pageNum      当前页
     * @param pageSize     每页显示数量
     * @return
     */
    PageInfoModel<List<SysMenuModel>> menuList(
            String menuName,
            Integer menuType,
            Integer enableStatus,
            int pageNum,
            int pageSize);

    /**
     * 变更菜单启用状态
     *
     * @param id           菜单ID
     * @param enableStatus 启用状态
     */
    void changeStatus(Long id, int enableStatus);

}