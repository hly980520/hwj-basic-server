package com.hwj.basic.server.activity.service.impl;

import com.hwj.basic.common.activity.service.ActivityWriteService;
import com.hwj.basic.server.constant.DubboConst;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(provider = DubboConst.PROVIDER)
public class ActivityWriteServiceImpl implements ActivityWriteService {
}
