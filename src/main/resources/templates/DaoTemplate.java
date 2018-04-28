/**
 * 
 */
package com.xhyj.meeting.dao;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xhyj.meeting.db.entity._{className};


/**
 * 
 * <p>Title: _{tableComments}</p>  
 * <p>Description: </p>  
 * @author zhaojz
 * @date _{date}
 */
@DynamicUpdate
public interface _{className}Dao extends JpaRepository<_{className}, String> ,JpaSpecificationExecutor<_{className}>{

}