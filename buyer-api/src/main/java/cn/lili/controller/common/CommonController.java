package cn.lili.controller.common;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.common.dto.IndexConfig;
import cn.lili.modules.distribution.entity.dos.DistributionOrder;
import cn.lili.modules.distribution.entity.vos.DistributionOrderSearchParams;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "coin首页")
@RequestMapping("/buyer/common")
public class CommonController {



    @ApiOperation(value = "获得广告列表")
    @GetMapping("/adlist")
    public ResultMessage<List<String>> adList(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<String> ads=new ArrayList<>();
        ads.add("http://localhost:8090/img/coin_back.jpg");
        return ResultUtil.data(ads);
    }


    @ApiOperation(value = "配置信息")
    @GetMapping("/config")
    public ResultMessage<List<IndexConfig>> config(DistributionOrderSearchParams distributionOrderSearchParams) {
        List<IndexConfig> ads=new ArrayList<>();
        IndexConfig demoConfig = new IndexConfig("demoConfig", 1);
        ads.add(demoConfig);
        return ResultUtil.data(ads);
    }

}
