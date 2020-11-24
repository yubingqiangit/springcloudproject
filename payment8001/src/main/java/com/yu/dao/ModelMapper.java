package com.yu.dao;


import com.yu.entity.ModelStakeRel;
import org.springframework.stereotype.Repository;

/**
 * TODO 
 * @version 1.0
 * @author yubingqian
 * @date 2020/9/25 15:27 
 */
@Repository
public interface ModelMapper {

    //@Select("SELECT * FROM pc_model_stake_rel WHERE id = #{id}")
    ModelStakeRel getModelStakeRelById(Integer id);

    ModelStakeRel getModelByParams( Integer id, String modelId);

}
