package com.longwang.dao;

import com.longwang.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 留言Dao接口
 */
public interface MessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    /**
     * 查询所有带条件
     * @return
     */
    List<Message> findAll();

}