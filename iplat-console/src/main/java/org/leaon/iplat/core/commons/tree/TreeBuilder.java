/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.commons.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 树结构构建类，用于组装和展示树型数据结构。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建TreeBuilder.java。
 *
 */
public class TreeBuilder {

	/** 日志器。 */
	private static final Logger logger = Logger.getLogger(TreeBuilder.class);

	/**
	 * 根节点。
	 */
	private TreeNode root;

	/**
	 * 当前节点列表。
	 */
	private List<TreeNode> tempNodeList;

	/**
	 * 是否是一棵合法的树。
	 */
	private boolean isValidTree = true;

	/**
	 * 实例化一个新的TreeBuilder类的对象。
	 */
	public TreeBuilder() {
	}

	/**
	 * 实例化一个新的<code>TreeBuilder</code>类的对象。
	 * 
	 * @param treeNodeList
	 *            树节点列表。
	 */
	public TreeBuilder(List<TreeNode> treeNodeList) {
		tempNodeList = treeNodeList;
		generateTree();
	}

	/**
	 * 通过节点ID获取树节点。
	 * 
	 * @param tree
	 *            待查找的树。
	 * @param id
	 *            节点ID。
	 * @return 返回符合条件的树节点。
	 */
	public static TreeNode getTreeNodeById(TreeNode tree, String id) {
		if (tree == null) {
			return null;
		}
		TreeNode treeNode;
		treeNode = tree.findTreeNodeById(id);
		return treeNode;
	}

	/**
	 * 通过指定节点或者实体对象列表生成一棵树。
	 */
	public void generateTree() {
		HashMap<String, TreeNode> nodeMap = putNodesIntoMap();
		putChildIntoParent(nodeMap);
	}

	/**
	 * 将当前节点列表放进Map中，并以节点ID作为键。
	 * 
	 * @return 包含树节点的<code>HashMap</code>。
	 */
	protected HashMap<String, TreeNode> putNodesIntoMap() {
		String currentId = "";
		HashMap<String, TreeNode> nodeMap = new HashMap<String, TreeNode>();
		Iterator<TreeNode> it = tempNodeList.iterator();
		while (it.hasNext()) {
			TreeNode treeNode = (TreeNode) it.next();
			String id = treeNode.getId();
			currentId = id;
			this.root = treeNode;
			String keyId = id;

			nodeMap.put(keyId, treeNode);
		}
		return nodeMap;
	}

	/**
	 * 设置父节点指向子节点。
	 * 
	 * @param nodeMap
	 *            包含树节点的<code>Map</code>，其<code>Map</code>键为节点ID。
	 */
	protected void putChildIntoParent(HashMap<String, TreeNode> nodeMap) {
		Iterator<TreeNode> it = nodeMap.values().iterator();
		while (it.hasNext()) {
			TreeNode treeNode = (TreeNode) it.next();
			String pid = treeNode.getParentId();
			String parentKeyId = String.valueOf(pid);
			if (nodeMap.containsKey(parentKeyId)) {
				TreeNode parentNode = (TreeNode) nodeMap.get(parentKeyId);
				if (parentNode == null) {
					this.isValidTree = false;
					return;
				} else {
					parentNode.addChildNode(treeNode);
					// System.out.println("childId: "
					// +treeNode.getSelfId()+" parentId: "+parentNode.getSelfId());
				}
			}
		}
	}

	/**
	 * 初始化当前节点列表。
	 */
	protected void initTempNodeList() {
		if (this.tempNodeList == null) {
			this.tempNodeList = new ArrayList<TreeNode>();
		}
	}

	/**
	 * 添加节点到当前节点列表。
	 * 
	 * @param treeNode
	 *            要添加的节点。
	 */
	public void addTreeNode(TreeNode treeNode) {
		initTempNodeList();
		this.tempNodeList.add(treeNode);
	}

	/**
	 * 向当前树中添加一个节点。
	 * 
	 * @param treeNode
	 *            要添加的节点。
	 * @return 返回boolen类型值，指示是否添加成功。
	 */
	public boolean insertTreeNode(TreeNode treeNode) {
		boolean insertFlag = root.insertJuniorNode(treeNode);
		return insertFlag;
	}

	/**
	 * 将实体对象列表转换为树节点列表。
	 * 
	 * @param entityList
	 *            实体对象列表。
	 * @return 返回转换后的树节点列表。
	 */
	public static List<TreeNode> changeEnititiesToTreeNodes(
			List<NodeBean> beanList) {
		NodeBean bean;
		List<TreeNode> tempNodeList = new ArrayList<TreeNode>();
		TreeNode treeNode;

		Iterator<NodeBean> it = beanList.iterator();
		while (it.hasNext()) {
			bean = (NodeBean) it.next();
			treeNode = new TreeNode();
			treeNode.setNodeBean(bean);
			treeNode.setParentId(bean.getParentId());
			treeNode.setId(bean.getId());
			treeNode.setName(bean.getName());
			tempNodeList.add(treeNode);
		}
		return tempNodeList;
	}

	/**
	 * 检查是否是一棵合法的树。
	 * 
	 * @return 如果已经valid tree，则返回true；否则返回false。
	 */
	public boolean isValidTree() {
		return this.isValidTree;
	}

	/**
	 * 获取根节点。
	 * 
	 * @return 返回根节点。
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * 设置根节点的值。
	 * 
	 * @param root
	 *            新的根节点值。
	 */
	public void setRoot(TreeNode root) {
		this.root = root;
	}

	/**
	 * 获取当前节点列表。
	 * 
	 * @return 包含当前节点的列表。
	 */
	public List<TreeNode> getTempNodeList() {
		return tempNodeList;
	}

	/**
	 * 设置当前节点列表。
	 * 
	 * @param tempNodeList
	 *            设置新的当前节点列表。
	 */
	public void setTempNodeList(List<TreeNode> tempNodeList) {
		this.tempNodeList = tempNodeList;
	}

}
