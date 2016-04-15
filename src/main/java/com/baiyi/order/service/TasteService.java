package com.baiyi.order.service;

import java.util.List;

import com.baiyi.order.model.Taste;
import com.baiyi.order.vo.TasteVO;

public interface TasteService {

	public void save(Taste taste);

	public void delete(Integer id);

	public void delete(Taste taste);

	public void delete(Integer[] ids);

	public void delete(List<Taste> tastes);

	public void update(Taste taste);

	public void merge(Taste taste);

	public Taste find(Integer id);

	public Taste find(String name);

	public List<Taste> findList();

	public int count(String name, Integer styleId, Integer userId);

	/**/
	public TasteVO findVO(Integer id);

	public List<TasteVO> findVOList();

	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId);

	public List<TasteVO> findVOList(String name, Integer styleId, Integer userId, String sort, String order, int pageNo, int pageSize);

	/**/
	public boolean exist(Integer id, String name);

	public boolean relate(Integer id);

	public boolean relate(Integer[] ids);
}
