package com.shadow.chat.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * @author Shadow
 * @date 2020/11/13 09:39:54
 */
public interface UserDao extends BaseMapper {

    @Insert("insert into ")
    void insert1();
}
