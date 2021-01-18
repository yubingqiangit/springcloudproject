package com.yu.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.entity.ModelStakeRel;
import com.yu.mapper.ModelStakeRelMapper;
import com.yu.service.ModelStakeService;
import org.springframework.stereotype.Service;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2021/1/18 10:50 
 */
@Service
public class ModelStakeServiceImpl extends ServiceImpl<ModelStakeRelMapper, ModelStakeRel> implements ModelStakeService {
}
