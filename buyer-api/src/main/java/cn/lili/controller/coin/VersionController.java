package cn.lili.controller.coin;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.Version;
import cn.lili.modules.coin.service.BinanceService;
import cn.lili.modules.coin.service.CoinQuotesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "个人coin信息")
@RequestMapping("/buyer/version")
public class VersionController {
    @Autowired
    CoinQuotesService coinQuotesService;

    @Autowired
    BinanceService binanceService;



    @ApiOperation(value = "检查APP版本")
    @GetMapping("/app")
    public ResultMessage<Version> getAccount() {

        Version version=new Version();
        version.setVersionCode(2);
        version.setVersionInfo("测试版本升级");
        version.setVersionName("v2.0");
        version.setAndroidUrl("https://app.liuyingyong.cn/build/download/41467950-e6da-11ef-afec-7be31e5be4e3");
        version.setUpdateType("solicit");

        return ResultUtil.data(version);
    }

}
