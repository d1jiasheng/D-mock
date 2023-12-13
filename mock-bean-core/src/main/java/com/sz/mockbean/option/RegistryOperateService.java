package com.sz.mockbean.option;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.request.MockBeanProtocal;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
public class RegistryOperateService {

    public static void doRegistryOperate(Channel channel, List<MockBeanRegisterConfig> registerConfigs) {
        MockBeanProtocal protocal = new MockBeanProtocal();
        //注册不需要序列号
        protocal.setSeqId(null);
        protocal.setAction("registry");
        protocal.setMsg(JSON.toJSONString(registerConfigs));
        channel.writeAndFlush(JSON.toJSONString(protocal));
    }
}
