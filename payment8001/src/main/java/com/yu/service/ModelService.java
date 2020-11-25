package com.yu.service;

import com.yu.entity.ModelStakeRel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 15:38 
 */
public interface ModelService {

    ModelStakeRel getModelStakeRelById(@PathVariable("id")Integer id);

    ModelStakeRel getModelByParams(@PathVariable("id") Integer id, @PathVariable("modelId") String modelId);

    List<ModelStakeRel> getModelPostList(ModelStakeRel modelStakeRel);

}
