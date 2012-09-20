/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.commons.tree;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点类，树的最小单元，用于存储实体数据。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建TreeNode.java。
 *
 */
public class TreeNode implements Serializable {

	/**
	 * 可序列化ID。
	 */
	private static final long serialVersionUID = 2568766809345801244L;

	/** 
	 * 日志器。
     */
	private static final Logger logger = Logger.getLogger(TreeNode.class);

	/**
	 * 节点ID。
	 */
	private String id;

	/** 
	 * 节点名称。 
	 */
	protected String name;

	/** 
	 * 父节点ID。
	 */
	private String parentId;

	/** 
	 * 父节点。
	 */
	protected TreeNode parentNode;

	/** 
	 * 节点Bean，用于存储实体对象。
	 */
	protected NodeBean nodeBean;

	/** 
	 * 子节点列表。
	 */
	protected List<TreeNode> childList;

	/**
	 * 实例化一个新的TreeNode类的对象。
	 */
	public TreeNode() {
		initChildList();
	}

	/**
	 * 实例化一个新的TreeNode类的对象。
	 * 
	 * @param parentNode
	 *            父节点。
	 */
	public TreeNode(TreeNode parentNode) {
		this.getParentNode();
		initChildList();
	}

	/**
	 * 检查是否已经是叶子节点。
	 * 
	 * @return 如果已经是叶子节点，则返回 true；否则返回 false。
	 */
	public boolean isLeaf() {
		if (childList == null) {
			return true;
		} else {
			if (childList.isEmpty()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 插入一个子节点到当前节点中 。
	 * 
	 * @param treeNode
	 *            要插入的节点。
	 */
	public void addChildNode(TreeNode treeNode) {
		initChildList();
		childList.add(treeNode);
	}

	/**
	 * 初始化子节点。
	 */
	public void initChildList() {
		if (childList == null)
			childList = new ArrayList<TreeNode>();
	}

	/**
	 * 检查是否已经是一棵合法的树。
	 * 
	 * @return 如果已经是合法的树，则返回 true；否则返回 false。
	 */
	public boolean isValidTree() {
		return true;
	}

	/**
	 * 获取当前节点的父辈节点集合。
	 * 
	 * @return 返回父辈节点的列表。
	 */
	public List<TreeNode> getElders() {
		List<TreeNode> elderList = new ArrayList<TreeNode>();
		TreeNode parentNode = this.getParentNode();
		if (parentNode == null) {
			return elderList;
		} else {
			elderList.add(parentNode);
			elderList.addAll(parentNode.getElders());
			return elderList;
		}
	}

	/**
	 * 获取当前节点的晚辈集合。
	 * 
	 * @return 返回晚辈节点的列表。
	 */
	public List<TreeNode> getJuniors() {
		List<TreeNode> juniorList = new ArrayList<TreeNode>();
		List<TreeNode> childList = this.getChildList();
		if (childList == null) {
			return juniorList;
		} else {
			int childNumber = childList.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode junior = childList.get(i);
				juniorList.add(junior);
				juniorList.addAll(junior.getJuniors());
			}
			return juniorList;
		}
	}

	/**
	 * 返回当前节点的所有子节点。
	 * 
	 * @return 返回子节点的列表。
	 */
	public List<TreeNode> getChildList() {
		return childList;
	}

	/**
	 * 删除节点的所有子节点。
	 */
	public void deleteNode() {
		TreeNode parentNode = this.getParentNode();
		String id = this.getId();

		if (parentNode != null) {
			parentNode.deleteChildNode(id);
		}
	}

	/**
	 * 删除当前节点的某个子节点。
	 * 
	 * @param childId
	 *            子节点ID。
	 */
	public void deleteChildNode(String childId) {
		List<TreeNode> childList = this.getChildList();
		int childNumber = childList.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = childList.get(i);
			if (child.getId() == childId) {
				childList.remove(i);
				return;
			}
		}
	}

	/**
	 * 动态的插入一个新的节点到当前树中。
	 * 
	 * @param treeNode
	 *            要插入的节点。
	 * @return 如果成功，则返回 true；否则返回 false。
	 */
	public boolean insertJuniorNode(TreeNode treeNode) {
		String juniorParentId = treeNode.getParentId();
		if (this.parentId == juniorParentId) {
			addChildNode(treeNode);
			return true;
		} else {
			List<TreeNode> childList = this.getChildList();
			int childNumber = childList.size();
			boolean insertFlag;

			for (int i = 0; i < childNumber; i++) {
				TreeNode childNode = childList.get(i);
				insertFlag = childNode.insertJuniorNode(treeNode);
				if (insertFlag == true)
					return true;
			}
			return false;
		}
	}

	/**
	 * 根据ID查找树中的某个节点。
	 * 
	 * @param id
	 *            节点ID。
	 * @return 返回要查找的节点。
	 */
	public TreeNode findTreeNodeById(String id) {
		if (this.id.equals(id)) {
			return this;
		}

		if (childList.isEmpty() || childList == null) {
			return null;
		} else {
			int childNumber = childList.size();
			for (int i = 0; i < childNumber; i++) {
				TreeNode child = childList.get(i);
				TreeNode resultNode = child.findTreeNodeById(id);
				if (resultNode != null) {
					return resultNode;
				}
			}
			return null;
		}
	}

	/**
	 * 遍历一棵树，层次遍历。
	 */
	public void traverse() {
		if (id == null) {
			return;
		}
		// 执行遍历后动作。
		if (childList == null || childList.isEmpty())
			return;
		int childNumber = childList.size();
		for (int i = 0; i < childNumber; i++) {
			TreeNode child = childList.get(i);
			child.traverse();
		}
	}

	/**
	 * 设置当前节点的子节点列表。
	 * 
	 * @param childList
	 *            子节点列表。
	 */
	public void setChildList(List<TreeNode> childList) {
		this.childList = childList;
	}

	/**
	 * @return 返回节点ID的值。
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            设置节点ID的值。
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 返回节点名称的值。
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            设置节点名称的值。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回父节点ID的值。
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            设置设置父节点ID的值。
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 返回返回父节点。
	 */
	public TreeNode getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode
	 *            设置父节点。
	 */
	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return 返回节点实体对象。
	 */
	public NodeBean getNodeBean() {
		return nodeBean;
	}

	/**
	 * @param nodeBean
	 *            设置节点实体对象。
	 */
	public void setNodeBean(NodeBean nodeBean) {
		this.nodeBean = nodeBean;
	}

}
