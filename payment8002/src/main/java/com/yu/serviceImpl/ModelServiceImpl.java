package com.yu.serviceImpl;

import com.yu.dao.ModelMapper;
import com.yu.entity.ModelStakeRel;
import com.yu.exception.CommonException;
import com.yu.exception.ExceptionEnums;
import com.yu.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public ModelStakeRel getModelByParams(Integer id, String modelId) {
        ModelStakeRel modelByParams = modelMapper.getModelByParams(id, modelId);
        if (modelByParams == null)
            throw new CommonException(ExceptionEnums.valueOf("MODEL_NOT_FOUND"));
        return modelByParams;
    }

    @Override
    public List<ModelStakeRel> getModelPostList(ModelStakeRel modelStakeRel) {
        List<ModelStakeRel> modelPostList = modelMapper.getModelPostList(modelStakeRel);
        if (CollectionUtils.isEmpty(modelPostList))
            throw new CommonException(ExceptionEnums.valueOf("MODEL_LIST_IS_EMPTY"));
        return modelPostList;
    }
}
