/**
 * 
 */
package com.xhyj.meeting.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xhyj.meeting.dao.MeetBaseInfoDao;
import com.xhyj.meeting.db.entity.MeetBaseInfo;
import com.xhyj.meeting.util.MyBeanUtil;
import com.xhyj.meeting.util.TokenUtl;


/**
 * 
 * <p>Title: _{tableComments}</p>  
 * <p>Description: </p>  
 * @author zhaojz
 * @date _{date}
 */
@Service
public class _{className}Service {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value(value = "${page.size}")
	private int pageSize;
	@Autowired
	private _{className}Dao _{objectName}Dao;
	/**
	 * 
	 * @param meetBaseInfo
	 * @param pageNum
	 * @return
	 */
	public Page<_{className}> findPage(_{className} _{objectName},int pageNum) {
		Specification<_{className}> specification = getWhereClause(_{objectName});
		Pageable page =new PageRequest(pageNum, pageSize,Sort.Direction.ASC, "id");
		return _{objectName}Dao.findAll(specification, page);
	}
	/**
	 * 
	 * @return
	 */
	public List<_{className}> findAll() {
		return _{objectName}Dao.findAll();
	}
	/**
	 * 
	 * @param meetBaseInfo
	 * @return
	 */
	public _{className} findOneById(_{className} _{objectName}) {
		return _{objectName}Dao.findOne(_{objectName}.getId());
	}
	
	/**
	 * @param meetBaseInfo
	 * @return
	 */
	public _{className} add(_{className} _{objectName}) {
		MyBeanUtil.complementAddBean(meetBaseInfo, TokenUtl.getOperatorIdFromToken(""));
		logger.info(_{objectName}.toString());
		return _{objectName}Dao.save(_{objectName});
	}
	
	/**
	 * @param meetBaseInfo
	 * @return
	 */
    @Transactional
	public _{className} update(_{className} meetBaseInfo) {
    	_{className} dbBean = _{objectName}Dao.findOne(_{objectName}.getId());
    	dbBean.setName(_{objectName}.getName());
    	MyBeanUtil.setBean(_{objectName}, dbBean);
    	MyBeanUtil.complementUpdateBean(_{objectName}, TokenUtl.getOperatorIdFromToken(""));
    	_{objectName}Dao.save(dbBean);
		return dbBean;
	}
    
    @Transactional
	public void delete(_{className} _{objectName}) {
    	_{objectName}Dao.delete(_{objectName}.getId());
	}
    /**
     * 动态生成where语句
     * @param searchArticle
     * @return
     */
    private Specification<_{className}> getWhereClause(final _{className} meetBaseInfo){
        return new Specification<_{className}>() {
			@Override
			public Predicate toPredicate(Root<_{className}> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicate = new ArrayList<>();
                if(!StringUtils.isEmpty(meetBaseInfo.getName())){
                    predicate.add(cb.like(root.get("name").as(String.class), "%"+meetBaseInfo.getName()+"%"));
                }
                if(!StringUtils.isEmpty(meetBaseInfo.getStt())){
                    predicate.add(cb.equal(root.get("stt").as(String.class), meetBaseInfo.getStt()));
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
			}
        };
    }
}
