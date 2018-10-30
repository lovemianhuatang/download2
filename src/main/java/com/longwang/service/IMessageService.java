package com.longwang.service;

import com.github.pagehelper.PageInfo;
import com.longwang.common.Result;
import com.longwang.entity.Message;

/**
 * 留言Service接口
 * @author longwang
 * @create 2018-10-25 11:09
 */
public interface IMessageService {

    /**
     * 分页查询所有
     * @param pageNum  当前页
     * @param pageSize  //每页记录数
     * @return
     */
    Result<PageInfo<Message>> findAll(Integer pageNum, Integer pageSize);

    /**
     * 根据Id获取实体
     * @param id  主键
     * @return
     */
    Result<Message> findById(Long id);

    /**
     * 删除留言
     * @param id  主键
     * @return
     */
    Result<String> delete(Long id);

}
