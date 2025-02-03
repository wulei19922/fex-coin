package cn.lili.controller.option;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.service.CoinQuotesService;
import cn.lili.modules.common.dto.IndexConfig;
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
@Api(tags = "coin首页")
@RequestMapping("/buyer/option")
public class OptionController {
    @Autowired
    CoinQuotesService coinQuotesService;


    @ApiOperation(value = "获得置顶市场信息")
    @GetMapping("/top/list")
    public ResultMessage<List<TopOptions>> optionList(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<TopOptions> options=new ArrayList<>();

        List<CoinQuotes> toplist = coinQuotesService.getToplist();

        for (int i = 0; i < toplist.size(); i++) {
            CoinQuotes coinQuotes = toplist.get(i);
            TopOptions topOptions = new TopOptions(coinQuotes.getSymbol(),"USDT",coinQuotes.getPrice(),coinQuotes.getPrice());
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
