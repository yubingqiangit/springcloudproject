package com.yu.serviceImpl;

import com.yu.dao.ModelMapper;
import com.yu.entity.ModelStakeRel;
import com.yu.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 15:39 
 */
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ModelStakeRel getModelStakeRelById(Integer id) {
        System.out.println("getModelStakeRelById id = "+id);
        ModelStakeRel modelStakeRel = modelMapper.getModelStakeRelById(id);
        return modelStakeRel;
    }
}
