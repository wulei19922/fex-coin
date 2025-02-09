package cn.lili.controller.coin;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.entity.Notices;
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
@RequestMapping("/buyer/notice")
public class NoticeController {
    @Autowired
    CoinQuotesService coinQuotesService;


    @ApiOperation(value = "获得coin信息")
    @GetMapping("/list")
    public ResultMessage<List<Notices>> conlist(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<Notices> toplist =  new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Notices n=new Notices();
            n.setNoticeId("1");
            n.setId("1");
            n.setNoticeTitle("特朗普上台，发行meme币");
            toplist.add(n);
        }

        return ResultUtil.data(toplist);
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
