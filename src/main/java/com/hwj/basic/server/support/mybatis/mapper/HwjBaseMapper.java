package com.hwj.basic.server.support.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HwjBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     * @param list 插入数据列表
     * @return 返回插入数
     */
    int insertBatchSomeColumn(@Param("list") List<T> list);
}
