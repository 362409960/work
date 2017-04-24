package com.cn.ub.common;



import java.util.ArrayList;
import java.util.List;

/**
 * 
  * @copyright：丰巨泰科
  * @ClassName: PageUtilsFooter
  * @Description: 扩展的分页插件
  * @param <T> Page中记录的类型
  * @author lichuan
  * @date 2016年7月12日 下午4:16:47
  * @version V1.0  
  */
public class PageUtilsFooter<E> extends PageUtils<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PageUtilsFooter(List<E> list) {
		super(list);
	}

	private List<E> footer;

	private String tmpMsg;
	
	public String getTmpMsg() {
		return tmpMsg;
	}

	public void setTmpMsg(String tmpMsg) {
		this.tmpMsg = tmpMsg;
	}

	public List<E> getFooter() {
		return footer;
	}

	public void setFooter(List<E> footer) {
		this.footer = footer;
	}
	
	public void setFooter(E footer) {
		List<E> list = new ArrayList<E>();
		list.add(footer);
		this.footer = list;
	}
}
