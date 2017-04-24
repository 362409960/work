package com.cn.ub.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
  * @copyright：丰巨泰科
  * @ClassName: PageUtils
  * @Description: 扩展的分页插件
  * @param <T> Page中记录的类型
  * @author zhaoxing
  * @date 2016年7月12日 下午4:22:14
  * @version V1.0  
 */
public class PageUtils<T> extends PageInfo<T> {

    public PageUtils(List<T> list) {
        super(list);
    }

    public PageUtils(List<T> list, int navigatePages) {
        super(list, navigatePages);
    }

    /**
     * 因为easyui不认识list属性，所以扩展一个跟easyui对应的结果集属性
     */
    private List<T> rows;

    public List<T> getRows() {
        return super.getList();
    }

}
