package inatel.eribox.test.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class GenericDAO<T, ID extends Serializable>{
	
	@PersistenceContext
	private EntityManager entityManager;
	
    private final Class<T> oClass;			

	public Class<T> getObjectClass() {
        return this.oClass;
    }
	
    @SuppressWarnings("unchecked")
    public GenericDAO() {
    	/*Set class type to oClass attribute*/
        this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
	@Transactional
    public T update(T entityBean) {
	    entityBean = entityManager.merge(entityBean);
	    entityManager.flush();
	    return entityBean;
    }
	
    @SuppressWarnings("unchecked")
    @Transactional
    public T searchParam(String query, Map<String, Object> params) {
        Query q = entityManager.createQuery(query);
        for (String key : params.keySet()) {
            q.setParameter(key, params.get(key));
        }
        return (T) q.getSingleResult();
    }
    
    @Transactional
    public void delete(T entityBean) {
	    entityBean = entityManager.merge(entityBean);
	    entityManager.remove(entityBean);
	    entityManager.flush();
    }
    
    public T findById(ID id) {
		return entityManager.find(oClass, id);
    }    
    
    @SuppressWarnings("unchecked")
    @Transactional
    public List<T> listSearchParam(String query, Map<String, Object> params) {
		Query q = entityManager.createQuery(query);
		if (params != null){
		    for (String key : params.keySet()) {
		        q.setParameter(key, params.get(key));
		    }
		}
		return q.getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<T> findAll() {
    	String queryS = " FROM " + oClass.getSimpleName() + " ";
    	Query query = entityManager.createQuery(queryS);
		return query.getResultList();
    }
}