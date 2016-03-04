package com.weixinserver.service;

import java.util.List;

public interface CommonService {
  /**
   * @Title: save
   * @Description: 保存数据记录
   * @param @param t
   * @return T 返回类型
   * @throws
   */
  <T> T save(T t);

  /**
   * @Title: update
   * @Description: 更新数据记录
   * @param @param t 实体类对象
   * @return T 返回类型
   * @throws
   */
  <T> T update(T t);

  /**
   * @Title: delete
   * @Description: 删除数据记录
   * @param @param t 实体类对象
   * @return T 返回类型
   * @throws
   */
  <T> T delete(T t);

  /**
   * @Title: findById
   * @Description: 通过id查找
   * @param @param id-
   * @param @param clazz 实体类对应的Class对象
   * @return T 返回类型
   * @throws
   */
  <T> T findById(long id, Class<T> clazz);

  /**
   * @Title: findByCode
   * @Description: 通过code查找
   * @param @param code-
   * @param @param clazz 实体类对应的Class对象
   * @return T 返回类型
   * @throws
   */
  <T> T findByCode(String code, Class<T> clazz);
  /**
   * @Title: findByWhere
   * @Description: 通过where条件查找
   * @param @param where-
   * @param @param clazz 实体类对应的Class对象
   * @return T 返回类型
   * @throws
   */
  <T> T findByWhere(String where, Class<T> clazz);

  /**
   * @Title: findAll
   * @Description: 查询所有记录
   * @param @param clazz 实体类对应的Class对象
   * @return List<T> 返回类型
   * @throws
   */
  <T> List<T> findAll(Class<T> clazz);

  /**
   * @Title: getCount
   * @Description: 获取总记录数
   * @param @param clazz 实体类对应的Class对象
   * @return long 返回类型
   * @throws
   */
  <T> long getCount(Class<T> clazz);

  /**
   * @Title: getListByWhere
   * @Description: 带条件的查询
   * @param @param where 查询条件
   * @param @param clazz 实体类对应的Class对象
   * @return List<T> 返回类型
   * @throws
   */
  <T> List<T> getListByWhere(String where, Class<T> clazz);
}
