package cn.lili.controller.coin;


import cn.lili.common.enums.ResultUtil;
import cn.lili.common.security.AuthUser;
import cn.lili.common.security.context.UserContext;
import cn.lili.common.vo.PageVO;
import cn.lili.common.vo.ResultMessage;
import cn.lili.modules.coin.entity.Address;
import cn.lili.modules.coin.entity.CoinKeys;
import cn.lili.modules.coin.entity.CoinQuotes;
import cn.lili.modules.coin.entity.Notices;
import cn.lili.modules.coin.entity.vos.AddressVo;
import cn.lili.modules.coin.service.CoinKeysService;
import cn.lili.modules.coin.service.CoinQuotesService;
import cn.lili.modules.common.dto.TopOptions;
import cn.lili.modules.distribution.entity.vos.DistributionOrderSearchParams;
import cn.lili.modules.goods.entity.vos.CategoryVO;
import cn.lili.modules.member.entity.dos.MemberAddress;
import cn.lili.mybatis.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.beust.ah.A;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "个人coin信息")
@RequestMapping("/buyer/encrypt")
public class EncryptController {
    @Autowired
    CoinQuotesService coinQuotesService;


    @Autowired
    CoinKeysService coinKeysService;

    @ApiOperation(value = "获赔配置信息")
    @GetMapping("/keys")
    public ResultMessage<CoinKeys> getKeys(String type) {
        AuthUser currentUser = UserContext.getCurrentUser();
        CoinKeys coinKeys=coinKeysService.getMemberKeys(currentUser.getId(),type);
        return ResultUtil.data(coinKeys);

    }


    @ApiOperation(value = "保存配置信息")
    @PostMapping("/save")
    public ResultMessage save(@RequestBody  CoinKeys coinKeys) {

        AuthUser currentUser = UserContext.getCurrentUser();
        coinKeys.setMemberId(currentUser.getId());
        coinKeys.setCreateBy(currentUser.getId());
        coinKeys.setCreateTime(new Date());
        coinKeysService.save(coinKeys);
        return ResultUtil.success();
    }

}
