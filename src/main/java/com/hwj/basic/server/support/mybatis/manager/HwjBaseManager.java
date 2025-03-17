package com.hwj.basic.server.support.mybatis.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.server.support.mybatis.mapper.HwjBaseMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 11:33:22
 */
public abstract class HwjBaseManager<M extends HwjBaseMapper<T>, T, Q> {

    /**
     * limit 500
     */
    protected static final String LAST_SQL_LIMIT_500 = "limit 500";

    @Autowired
    protected M baseMapper;

    /**
     * 构建查询条件<br>
     * 子类实现
     *
     * @param params 查询参数
     * @return QueryWrapper<T>
     */
    protected abstract QueryWrapper<T> buildQueryWrapper(Q params);

    /**
     * 根据id查询
     *
     * @param id 记录id
     * @return T
     */
    public T selectById(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return this.baseMapper.selectById(id);
    }

    /**
     * 根据id列表查询
     *
     * @param idList 记录id列表
     * @return List<T>
     */
    public List<T> selectByIds(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return null;
        }
        return this.baseMapper.selectBatchIds(idList);
    }

    /**
     * 条件查询
     *
     * @param params 查询参数
     * @return T
     */
    public T selectOne(Q params) {
        QueryWrapper<T> wrapper = this.buildQueryWrapper(params);
        return this.baseMapper.selectOne(wrapper);
    }

    /**
     * 根据条件查询
     *
     * @param params 查询参数
     * @return List<T>
     */
    public List<T> selectList(Q params) {
        QueryWrapper<T> wrapper = this.buildQueryWrapper(params);
        wrapper.last(LAST_SQL_LIMIT_500);
        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 根据条件查询记录数量
     *
     * @param params 查询参数
     * @return Long
     */
    public long selectCount(Q params) {
        QueryWrapper<T> wrapper = this.buildQueryWrapper(params);
        return this.baseMapper.selectCount(wrapper);
    }

    /**
     * 分页条件查询记录
     *
     * @param dataPage 分页参数
     * @param params   查询参数
     * @param mapper   数据转换函数
     * @return Page<T>
     */
    public <E> DataPage<E> selectPage(DataPage<E> dataPage, Q params, Function<List<T>, List<E>> mapper) {
        Page<T> page = this.convertPage(dataPage);
        page = this.selectPage(page, params);
        dataPage = this.convertDataPage(page);
        dataPage.setDataList(mapper.apply(page.getRecords()));
        return dataPage;
    }

    /**
     * 分页条件查询记录
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return Page<T>
     */
    public Page<T> selectPage(Page<T> page, Q params) {
        QueryWrapper<T> wrapper = this.buildQueryWrapper(params);
        return this.baseMapper.selectPage(page, wrapper);
    }

    /**
     * 插入记录
     *
     * @param obj 记录对象
     * @return true or false
     */
    public boolean insert(T obj) {
        if (Objects.isNull(obj)) {
            return false;
        }
        return this.baseMapper.insert(obj) > 0;
    }

    /**
     * 批量插入记录
     *
     * @param list 记录对象列表
     * @return true or false
     */
    public boolean batchInsert(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return this.baseMapper.insertBatchSomeColumn(list) > 0;
    }

    /**
     * 根据id更新记录
     *
     * @param params 更新参数
     * @return true or false
     */
    public boolean updateById(T params) {
        return this.baseMapper.updateById(params) > 0;
    }

    /**
     * 转分页查询参数
     *
     * @param page 分页查询参数
     * @param <E>  泛型
     * @return DataPage<E>
     */
    private <E> DataPage<E> convertDataPage(Page<T> page) {
        DataPage<E> dataPage = new DataPage<>();
        dataPage.setPageNo((int) page.getCurrent());
        dataPage.setPageSize((int) page.getSize());
        dataPage.setTotalCount((int) page.getTotal());
        return dataPage;
    }

    /**
     * 转mybatis分页查询参数
     *
     * @param dataPage 分页查询参数
     * @param <E>      泛型
     * @return Page<T>
     */
    private <E> Page<T> convertPage(DataPage<E> dataPage) {
        Page<T> page = new Page<>();
        page.setCurrent(dataPage.getPageNo());
        page.setSize(dataPage.getPageSize());

        if (StringUtils.isBlank(dataPage.getOrderBy())
                || StringUtils.isBlank(dataPage.getOrder())) {
            return page;
        }

        if (DataPage.ASC.equals(dataPage.getOrderBy())) {
            page.setOrders(OrderItem.ascs(dataPage.getOrder().split(",")));
            return page;
        }
        page.setOrders(OrderItem.descs(dataPage.getOrder().split(",")));
        return page;
    }
}
