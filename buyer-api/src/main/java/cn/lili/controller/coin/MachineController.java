package cn.lili.controller.coin;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.entity.Mining;
import cn.lili.modules.coin.entity.Notices;
import cn.lili.modules.coin.service.CoinQuotesService;
import cn.lili.modules.common.dto.TopOptions;
import cn.lili.modules.distribution.entity.vos.DistributionOrderSearchParams;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "获得交易信息")
@RequestMapping("/buyer/machine")
public class MachineController {
    @Autowired
    CoinQuotesService coinQuotesService;


    @ApiOperation(value = "获得coin信息")
    @GetMapping("/list")
    public ResultMessage<Page<Mining>> conlist(PageVO pageVO) {
        List<Mining> toplist =  new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Mining n=new Mining();
            n.setId("1");
            n.setName("test");
            n.setCreateTime(new Date());
            n.setPowerUnit(100.0f);
            n.setPowerVol(1.2f);
            n.setLifeCycle(1);
            n.setPowerUnit(1f);

            toplist.add(n);
        }
        Page<Mining> objectPage = PageUtil.initPage(pageVO);
        objectPage.setRecords(toplist);



        return ResultUtil.data(objectPage);
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
