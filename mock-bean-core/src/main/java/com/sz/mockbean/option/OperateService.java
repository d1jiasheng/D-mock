package com.sz.mockbean.option;

import com.alibaba.fastjson.JSON;
import com.sz.mockbean.model.MockBeanRegisterConfig;
import com.sz.mockbean.model.WriteValueModel;
import com.sz.mockbean.request.MockBeanProtocal;
import io.netty.channel.Channel;

import java.util.List;

/**
 * @author dijiasheng
 * @date 2023/12/13
 */
public class OperateService {

    public static void doRegistryOperate(Channel channel, List<MockBeanRegisterConfig> registerConfigs) {
        MockBeanProtocal protocal = new MockBeanProtocal();
        //注册不需要序列号
        protocal.setSeqId(null);
        protocal.setAction("registry");
        protocal.setMsg(JSON.toJSONString(registerConfigs));
        channel.writeAndFlush(JSON.toJSONString(protocal));
    }

    public static void doWriteOperate(Channel channel, WriteValueModel writeValueModel) {
        MockBeanProtocal protocal = new MockBeanProtocal();
        protocal.setAction("write");
        protocal.setMsg(JSON.toJSONString(writeValueModel));
        channel.writeAndFlush(JSON.toJSONString(protocal));
    }
}
