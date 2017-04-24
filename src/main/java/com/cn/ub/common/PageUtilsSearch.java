package com.cn.ub.common;



import java.util.List;


/**
  * @copyright：丰巨泰科
  * @ClassName: PageUtilsSearch
  * @Description:  扩展的分页插件
  * @param <T> Page中记录的类型
  * @author lichuan
  * @date 2016年7月12日 下午4:17:58
  * @version V1.0  
 */
public class PageUtilsSearch<E> extends PageUtils<E> {

	private static final long serialVersionUID = 1L;

	public PageUtilsSearch(List<E> list) {
		super(list);
	}

    private List<E> rows;

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}
}
