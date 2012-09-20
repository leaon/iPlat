/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.pagination;

/**
 * 分页类，用于记录分页过程中的状态信息。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-5				Leaon				创建Page.java。
 *
 */
public class Page {
	
    /**
     * 分页查询开始记录位置。
     */
    private int begin = 1;
    
    /**
     * 分页查看下结束位置。
     */
    private int end;
    
    /**
     * 每页显示记录数。
     */
    private int length = 20;
    
    /**
     * 查询结果总记录数。
     */
    private int count;
    
    /**
     * 当前页码。  
     */
    private int current = 1;
    
    /**
     * 总共页数。   
     */
    private int total;
    
    /**
     * 构造函数。
     */
    public Page() {
    	
    }   
  
    /**  
     * 构造方法。  
     *   
     * @param begin 开始位置。
     * @param length 每页显示条目数，可以动态改变。
     */  
    public Page(int begin, int length) {
        this.begin = begin;   
        this.length = length;   
        this.end = this.begin + this.length;   
        this.current = (int) Math.floor((this.begin * 1.0d) / this.length) + 1;   
    }   
  
    /**  
     * 构造方法。
     * 
     * @param begin 开始位置。
     * @param length 结束位置。
     * @param count 总记录数。
     */  
    public Page(int begin, int length, int count) {   
        this(begin, length);   
        this.count = count;   
    }

	/**
	 * 获取begin的值。
	 *
	 * @return 返回begin的值。
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * 设置begin的值。 
	 * 
	 * @param begin 要设置的begin的值。
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * 获取end的值。
	 *
	 * @return 返回end的值。
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * 设置end的值。 
	 * 
	 * @param end 要设置的end的值。
	 */
	public void setEnd(int end) {
		this.end = end;
	}

	/**
	 * 获取length的值。
	 *
	 * @return 返回length的值。
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 设置length的值。 
	 * 
	 * @param length 要设置的length的值。
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * 获取count的值。
	 *
	 * @return 返回count的值。
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 设置count的值。 
	 * 
	 * @param count 要设置的count的值。
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 获取current的值。
	 *
	 * @return 返回current的值。
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * 设置current的值。 
	 * 
	 * @param current 要设置的current的值。
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * 获取total的值。
	 *
	 * @return 返回total的值。
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * 设置total的值。 
	 * 
	 * @param total 要设置的total的值。
	 */
	public void setTotal(int total) {
		this.total = total;
	}   
}  

