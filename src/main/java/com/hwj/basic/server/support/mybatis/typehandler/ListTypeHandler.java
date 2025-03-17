package com.hwj.basic.server.support.mybatis.typehandler;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @Program: hwj
 * @Description:
 * @author: wenjing.huang
 * @since: 2025-03-17 13:39:18
 */
public class ListTypeHandler<T> extends BaseTypeHandler<List<T>> {

    private Class<T> type;

    public ListTypeHandler(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Objects.isNull(parameter) ? null : JSON.toJSONString(parameter));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return StringUtils.isBlank(value) ? null : JSON.parseArray(value, type);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return StringUtils.isBlank(value) ? null : JSON.parseArray(value, type);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return StringUtils.isBlank(value) ? null : JSON.parseArray(value, type);
    }
}
