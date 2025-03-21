package com.hwj.basic.server.activity.service.impl;

import com.hwj.basic.common.activity.dto.ActivityDTO;
import com.hwj.basic.common.activity.service.ActivityReadService;
import com.hwj.basic.constant.ErrorCode;
import com.hwj.basic.mybatis.DataPage;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.activity.converter.ActivityEntityConverter;
import com.hwj.basic.server.activity.entity.ActivityEntity;
import com.hwj.basic.server.activity.manager.ActivityEntityManager;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityReadServiceImpl implements ActivityReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityReadServiceImpl.class);

    @Resource
    private ActivityEntityManager activityEntityManager;

    @Resource
    private ActivityEntityConverter activityEntityConverter;

    @Override
    public RpcResult<List<ActivityDTO>> queryList() {
        List<ActivityEntity> activityEntities = activityEntityManager.selectList(null);
        List<ActivityDTO> data = activityEntityConverter.toDTOList(activityEntities);
        return RpcResult.success(data);
    }


    @Override
    public RpcResult<DataPage<ActivityDTO>> queryPage(DataPage<ActivityDTO> dataPage, ActivityDTO activityDTO) {
        if (Objects.isNull(dataPage) || Objects.isNull(activityDTO)) {
            LOGGER.warn("dataPage Is Null Or Member Is Null");
            return ErrorCode.PARAMS_MISS.toRpcResult();
        }
        dataPage = activityEntityManager.selectPage(dataPage,
                activityEntityConverter.from(activityDTO),
                this::toActivityList
        );

        return RpcResult.success(dataPage);
    }

    /**
     * 转activityrList
     * @param list ActivityEntity List
     * @return List<ActivityDTO>
     */
    private List<ActivityDTO> toActivityList(List<ActivityEntity> list) {
        return list.stream()
                .map(data -> activityEntityConverter.toDTO(data))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
