package com.xunpoit.oa.dao;

import com.xunpoit.oa.entity.UsersRoles;

public interface UsersRolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsersRoles record);

    int insertSelective(UsersRoles record);

    UsersRoles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsersRoles record);

    int updateByPrimaryKey(UsersRoles record);
}