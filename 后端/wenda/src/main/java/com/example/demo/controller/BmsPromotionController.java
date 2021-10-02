package com.example.demo.controller;

import com.example.demo.common.api.ApiResult;
import com.example.demo.model.entity.BmsPromotion;
import com.example.demo.service.IBmsPromotionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/promotion")
public class BmsPromotionController extends BaseController{

    @Resource
    private IBmsPromotionService bmsPromotionService;

    @GetMapping("/all")
    public ApiResult<List<BmsPromotion>> getNotices(){
        List<BmsPromotion> list = bmsPromotionService.list();
        return ApiResult.success(list);
    }

}
