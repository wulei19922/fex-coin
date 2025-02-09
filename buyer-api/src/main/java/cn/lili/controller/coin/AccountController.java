package cn.lili.controller.coin;


import cn.hutool.json.JSONObject;
import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.ChargeAddress;
import cn.lili.modules.coin.entity.CoinBot;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.entity.vos.AccountVo;
import cn.lili.modules.coin.service.BinanceService;
import cn.lili.modules.coin.service.BotService;
import cn.lili.modules.coin.service.CoinQuotesService;
import cn.lili.modules.common.dto.TopOptions;
import cn.lili.modules.distribution.entity.vos.DistributionOrderSearchParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "个人coin信息")
@RequestMapping("/buyer/account")
public class AccountController {
    @Autowired
    CoinQuotesService coinQuotesService;

    @Autowired
    BinanceService binanceService;

    @Autowired
    BotService botService;



    @ApiOperation(value = "获得账户信息")
    @GetMapping("/list")
    public ResultMessage<AccountVo> getAccount() {
        AuthUser currentUser = UserContext.getCurrentUser();
        AccountVo v=new AccountVo();
        List<BinanceAccount> accountInfo = binanceService.getAccountInfo();
        CoinBot botInfo = botService.getBotInfo(currentUser.getId());
        v.setList(accountInfo);
        v.setBotStatus(botInfo.isOpenStatus());
        return ResultUtil.data(v);
    }

    @ApiOperation(value = "获得充值地址")
    @GetMapping("/address")
    public ResultMessage<ChargeAddress> getChargeAddress() {
        AuthUser currentUser = UserContext.getCurrentUser();
        ChargeAddress chargeAddress = binanceService.getChargeAddress(currentUser.getId());
        return ResultUtil.data(chargeAddress);
    }

    @ApiOperation(value = "开启量化")
    @PostMapping("/startq")
    public ResultMessage startQBot(@RequestBody JSONObject status) {
        AuthUser currentUser = UserContext.getCurrentUser();

        boolean b = botService.startQBot(currentUser.getId(), status.getBool("status"));
        return ResultUtil.data(b);
    }


}
