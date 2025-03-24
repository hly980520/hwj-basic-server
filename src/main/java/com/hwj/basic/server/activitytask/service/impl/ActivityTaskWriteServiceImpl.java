package com.hwj.basic.server.activitytask.service.impl;

import com.hwj.basic.common.activitytask.dto.ActivityTaskDTO;
import com.hwj.basic.common.activitytask.service.ActivityTaskWriteService;
import com.hwj.basic.result.RpcResult;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityTaskWriteServiceImpl implements ActivityTaskWriteService {
    @Override
    public RpcResult<ActivityTaskDTO> create(ActivityTaskDTO activityTaskDTO) {
        return null;
    }

    @Override
    public RpcResult<ActivityTaskDTO> update(ActivityTaskDTO activityTaskDTO) {
        return null;
    }

    @Override
    public RpcResult<ActivityTaskDTO> deleted(Long id) {
        return null;
    }
}
