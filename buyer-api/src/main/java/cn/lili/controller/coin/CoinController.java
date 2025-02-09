package cn.lili.controller.coin;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.BinanceAccount;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.service.BinanceService;
import cn.lili.modules.coin.service.CoinQuotesService;
import cn.lili.modules.common.dto.TopOptions;
import cn.lili.modules.distribution.entity.vos.DistributionOrderSearchParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "个人coin信息")
@RequestMapping("/buyer/coin")
public class CoinController {
    @Autowired
    CoinQuotesService coinQuotesService;

    @Autowired
    BinanceService binanceService;



    @ApiOperation(value = "获得账户信息")
    @GetMapping("/account")
    public ResultMessage<List<BinanceAccount>> getAccount() {

        List<BinanceAccount> accountInfo = binanceService.getAccountInfo();

        return ResultUtil.data(accountInfo);
    }

    @ApiOperation(value = "获得可以充值币的信息")
    @GetMapping("/list")
    public ResultMessage<List<TopOptions>> conlist(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<TopOptions> options=new ArrayList<>();


        for (int i = 0; i < 1; i++) {
            TopOptions topOptions = new TopOptions("USDT","USDT",0F,0F);
            options.add(topOptions);
        }



        return ResultUtil.data(options);
    }


    @ApiOperation(value = "获得全部市场信息")
    @GetMapping("/market/list")
    public ResultMessage<List<TopOptions>> config(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<TopOptions> options=new ArrayList<>();

        List<CoinQuotes> toplist = coinQuotesService.getToplist();

        for (int i = 0; i < toplist.size(); i++) {
            CoinQuotes coinQuotes = toplist.get(i);
            TopOptions topOptions = new TopOptions(coinQuotes.getSymbol(),"USDT",coinQuotes.getPrice(),coinQuotes.getPrice());
            options.add(topOptions);
        }



        return ResultUtil.data(options);
    }



}
