package com.whkj.spikes.bo.rest;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

@JsonPropertyOrder( {"page", "perPage", "total", "items"})
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int page = 1;

    private int perPage = -1;

    private int total = -1;

    private List<T> items = Lists.newArrayListWithCapacity(0);

    public PageResult(List<T> items, int total, int page, int perPage) {
        this.items = items;
        this.total = total;
        this.page = page;
        this.perPage = perPage;
        if (page < 1) {
            this.page = 1;
        }
    }

    public PageResult() {

    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public int getTotal() {
        return total;
    }


    public PageResult total(int total) {
        this.total = total;
        return this;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPerPage() {
        return perPage;
    }

    /**
     * 设置每页的记录数量.
     */
    public PageResult perPage(final int perPage) {
        this.perPage = perPage;
        return this;
    }

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPage() {
        return this.page;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public PageResult page(int page) {
        this.page = page;
        if (page < 1) {
            this.page = 1;
        }
        return this;
    }

    public List<T> getItems() {
        return items;
    }

    public PageResult setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setPage(int page) {
        this.page = page;
        if (page < 1) {
            this.page = 1;
        }
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((page - 1) * perPage) + 1;
    }


    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (total < 0) {
            return -1;
        }

        long count = total / perPage;
        if (total % perPage > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return (page + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext()) {
            return page + 1;
        } else {
            return page;
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre() {
        return (page - 1 >= 1);
    }

    /**
     * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPre()) {
            return page - 1;
        } else {
            return page;
        }
    }
}