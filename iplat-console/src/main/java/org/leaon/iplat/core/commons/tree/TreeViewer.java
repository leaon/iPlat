/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.commons.tree;

import org.apache.log4j.Logger;

import java.util.Iterator;

/**
 * 树的加载器。用于加载并展示树结构的数据。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Remark			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-11			Leaon				创建TreeViewer.java。
 *
 */
public class TreeViewer {
	
	/** 
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(TreeViewer.class);

	/**
	 * 实例化一个新的<code>TreeViwer</code>类的对象。
	 */
	public TreeViewer() {
	}

	/**
	 * 实例化一个新的<code>TreeViwer</code>类的对象。
	 *
	 * @param treeNode 树节点。
	 */
	public TreeViewer(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	/** check id。. */
	private String checkId = "";
	
	/** ck node name。. */
	private String ckNodeName = "ckNode";

	/**
	 * 获取 ck node name。.
	 *
	 * @return ck node name。
	 */
	public String getCkNodeName() {
		return ckNodeName;
	}

	/**
	 * 设置 ck node name。.
	 *
	 * @param ckNodeName 新的 ck node name 值。
	 */
	public void setCkNodeName(String ckNodeName) {
		this.ckNodeName = ckNodeName;
	}


	/**
	 * 获取checkId的值。
	 *
	 * @return 返回checkId的值。
	 */
	public String getCheckId() {
		return checkId;
	}

	/**
	 * 设置checkId的值。 
	 * 
	 * @param checkId 要设置的checkId的值。
	 */
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	/**
	 * Display view。.
	 *
	 * @param treeNode - tree node。
	 * @return string。
	 */
	public String displayView(TreeNode treeNode) {
		String childView = getChildView(treeNode, 1);
		String wholeView = this.getRootView(treeNode) + childView
				+ this.getJsCode();
		return wholeView;
	}

	/**
	 * Display leaf check view。.
	 *
	 * @param treeNode - tree node。
	 * @return string。
	 */
	public String displayLeafCheckView(TreeNode treeNode) {
		String childView = getChildLeafCheckView(treeNode, 1);
		String wholeView = this.getRootLeafCheckView(treeNode) + childView
				+ this.getJsCode();
		return wholeView;
	}

	/**
	 * Display view。.
	 *
	 * @return string。
	 */
	public String displayView() {
		return this.displayView(this.treeNode);
	}

	/**
	 * Display leaf check view。.
	 *
	 * @return string。
	 */
	public String displayLeafCheckView() {
		return this.displayLeafCheckView(this.treeNode);
	}

	/**
	 * 获取 expand img。.
	 *
	 * @return expand img。
	 */
	public String getExpandImg() {
		return expandImg;
	}

	/**
	 * 设置 expand img。.
	 *
	 * @param expandImg 新的 expand img 值。
	 */
	public void setExpandImg(String expandImg) {
		this.expandImg = expandImg;
	}

	/**
	 * 获取 leaf img。.
	 *
	 * @return leaf img。
	 */
	public String getLeafImg() {
		return leafImg;
	}

	/**
	 * 设置 leaf img。.
	 *
	 * @param leafImg 新的 leaf img 值。
	 */
	public void setLeafImg(String leafImg) {
		this.leafImg = leafImg;
	}

	/**
	 * 获取 line img。.
	 *
	 * @return line img。
	 */
	public String getLineImg() {
		return lineImg;
	}

	/**
	 * 设置 line img。.
	 *
	 * @param lineImg 新的 line img 值。
	 */
	public void setLineImg(String lineImg) {
		this.lineImg = lineImg;
	}

	/**
	 * 获取 shrink img。.
	 *
	 * @return shrink img。
	 */
	public String getShrinkImg() {
		return shrinkImg;
	}

	/**
	 * 设置 shrink img。.
	 *
	 * @param shrinkImg 新的 shrink img 值。
	 */
	public void setShrinkImg(String shrinkImg) {
		this.shrinkImg = shrinkImg;
	}

	/** tree node。. */
	private TreeNode treeNode = null;
	
	/** img path。. */
	private String imgPath = "/wis/images";
	
	/** CKEXP。. */
	private String CKEXP = " checked ";
	
	/** expand img。. */
	private String expandImg = imgPath + "/Darrow11.gif";
	
	/** shrink img。. */
	private String shrinkImg = imgPath + "/Rarrow11.gif";
	
	/** leaf img。. */
	private String leafImg = imgPath + "/darrow51.gif";
	
	/** line img。. */
	private String lineImg = imgPath + "/darrow61.gif";
	
	/** branch end img。. */
	private String branchEndImg = imgPath + "/branch_end.gif";
	
	/** plus end img。. */
	private String plusEndImg = imgPath + "/plus_end.gif";
	
	/** lable style。. */
	private String lableStyle = " style=\"cursor:hand;font-family:verdana;font-size:12px\" ";
	
	/** on mouse over。. */
	private String onMouseOver = " onMouseOver=\"this.style.color='red'\" ";
	
	/** on mouse out。. */
	private String onMouseOut = " onMouseOut=\"this.style.color='black'\" ";

	/**
	 * 获取 js code。.
	 *
	 * @return js code。
	 */
	private String getJsCode() {
		String retView = "\r\n<script>" + "\r\nfunction toggleMenuDisplay(id)"
				+ "{" + "var divCtl=document.getElementById('divId_'+id);"
				+ "var imgCtl=document.getElementById('imgId_'+id);"
				+ "if(divCtl.style.display == 'none'){" + " imgCtl.src=\""
				+ expandImg
				+ "\";"
				+ " divCtl.style.display='block';"
				+ "}"
				+ "else {"
				+ " imgCtl.src=\""
				+ shrinkImg
				+ "\";"
				+ " divCtl.style.display='none';"
				+ "}"
				+ "}\r\n"
				+ "function onCheck(chCtl)"
				+ "{"
				+ " var checkCount=0;"
				+ " var ckNodes= document.getElementsByName(\""
				+ ckNodeName
				+ "\");"
				+ " for(var i=0;i<ckNodes.length;i++){"
				+ " if(ckNodes[i].checked) checkCount++;}"
				+ " if(checkCount>=2) {"
				+ " alert(\"只能选择一个节点\");"
				+ " chCtl.checked=false;" + " }" + "}\r\n" + "</script>\r\n";
		return retView;
	}

	/**
	 * 获取 root view。.
	 *
	 * @param treeNode - tree node。
	 * @return root view。
	 */
	private String getRootView(TreeNode treeNode) {
		String imgId = " id=\"imgId_" + treeNode.getId() + "\"";
		String checkedString = "";
		if (treeNode.getId().equals(this.getCheckId()))
			checkedString = this.CKEXP;
		String onClick = " onClick=\"toggleMenuDisplay('"
				+ treeNode.getId() + "')\"";
		if (treeNode.isLeaf())
			onClick = "";
		String retView = "<img src=\"" + expandImg + "\" " + onClick + imgId
				+ " width='26' height='22' align='absmiddle'>"
				+ "<input type='checkbox' name=\"" + ckNodeName + "\" value=\""
				+ treeNode.getId() + "\" " + checkedString
				+ "onClick=\"onCheck(this)\">" + "<label " + onClick
				+ onMouseOver + onMouseOut + lableStyle + ">"
				+ treeNode.getName() + "</label>";
		return retView;
	}

	/**
	 * 获取 root leaf check view。.
	 *
	 * @param treeNode - tree node。
	 * @return root leaf check view。
	 */
	private String getRootLeafCheckView(TreeNode treeNode) {
		String checkBox = "";
		String checkedString = "";
		if (treeNode.getId().equals(this.getCheckId()))
			checkedString = this.CKEXP;
		if (treeNode.isLeaf())
			checkBox = "<input type='checkbox' name=\"" + ckNodeName
					+ "\" value=\"" + treeNode.getId() + "\""
					+ checkedString + " onClick=\"onCheck(this)\">";
		String imgId = " id=\"imgId_" + treeNode.getId() + "\"";
		String onClick = " onClick=\"toggleMenuDisplay('"
				+ treeNode.getId() + "')\"";
		if (treeNode.isLeaf())
			onClick = "";
		String retView = "<img src=\"" + expandImg + "\" " + onClick + imgId
				+ " width='26' height='22' align='absmiddle'>" + checkBox
				+ "<label " + onClick + onMouseOver + onMouseOut + lableStyle
				+ ">" + treeNode.getName() + "</label>";
		return retView;
	}

	/**
	 * 获取 leaf view。.
	 *
	 * @param treeNode - tree node。
	 * @param lastFlag - last flag。
	 * @return leaf view。
	 */
	private String getLeafView(TreeNode treeNode, int lastFlag) {
		String checkedString = "";
		if (treeNode.getId().equals(this.getCheckId()))
			checkedString = this.CKEXP;
		String imgSrc = leafImg;
		if (lastFlag == 1)
			imgSrc = this.branchEndImg;
		String retView = "<img src=\"" + imgSrc
				+ "\" width='26' height='22' align='absmiddle'>"
				+ "<input type='checkbox' name=\"" + ckNodeName + "\" value=\""
				+ treeNode.getId() + "\"" + checkedString
				+ " onClick=\"onCheck(this)\">" + "<label" + onMouseOver
				+ onMouseOut + lableStyle + ">" + treeNode.getName()
				+ "</label><br>";
		return retView;
	}

	/**
	 * 获取 dir view。.
	 *
	 * @param treeNode - tree node。
	 * @param lastFlag - last flag。
	 * @return dir view。
	 */
	private String getDirView(TreeNode treeNode, int lastFlag) {
		String checkedString = "";
		if (treeNode.getId().equals(this.getCheckId()))
			checkedString = this.CKEXP;
		String imgSrc = shrinkImg;
		// if(lastFlag==1)imgSrc=this.plusEndImg;
		String imgId = " id=\"imgId_" + treeNode.getId() + "\"";
		String onClick = " onClick=\"toggleMenuDisplay('"
				+ treeNode.getId() + "')\"";
		String retView = "<img src=\"" + imgSrc + "\" " + onClick + imgId
				+ " width='26' height='22' align='absmiddle'>"
				+ "<input type='checkbox' name=\"" + ckNodeName + "\" value=\""
				+ treeNode.getId() + "\"" + checkedString
				+ "onClick=\"onCheck(this)\">" + "<label " + onClick
				+ onMouseOver + onMouseOut + lableStyle + " >"
				+ treeNode.getName() + "</label><br>";
		return retView;
	}

	/**
	 * 获取 dir no check view。.
	 *
	 * @param treeNode - tree node。
	 * @param lastFlag - last flag。
	 * @return dir no check view。
	 */
	private String getDirNoCheckView(TreeNode treeNode, int lastFlag) {
		String imgSrc = shrinkImg;
		// if(lastFlag==1)imgSrc=this.plusEndImg;
		String imgId = " id=\"imgId_" + treeNode.getId() + "\"";
		String onClick = " onClick=\"toggleMenuDisplay('"
				+ treeNode.getId() + "')\"";
		String retView = "<img src=\"" + imgSrc + "\" " + onClick + imgId
				+ " width='26' height='22' align='absmiddle'>" + "<label "
				+ onClick + onMouseOver + onMouseOut + lableStyle + " >"
				+ treeNode.getName() + "</label><br>";
		return retView;
	}

	/**
	 * 获取 line view。.
	 *
	 * @return line view。
	 */
	private String getLineView() {
		String retView = "<img src=\"" + lineImg
				+ "\" width='26' height='22' align='absmiddle'>";
		return retView;
	}

	/**
	 * 获取 child view。.
	 *
	 * @param treeNode - tree node。
	 * @param depth - depth。
	 * @return child view。
	 */
	public String getChildView(TreeNode treeNode, int depth) {
		String disPlayView = "style='display:none'";
		if (depth == 1)
			disPlayView = "style='display:block'";
		String nodeView = "<div id=\"divId_" + treeNode.getId() + "\""
				+ disPlayView + ">";
		String frontLineView = "";
		for (int i = 0; i < depth; i++)
			frontLineView += this.getLineView();
		Iterator it = treeNode.getChildList().iterator();
		while (it.hasNext()) {
			TreeNode tmpNode = (TreeNode) it.next();
			int lastFlag = 0;
			if (it.hasNext() == false)
				lastFlag = 1;
			if (tmpNode.isLeaf()) {
				nodeView += frontLineView + this.getLeafView(tmpNode, lastFlag);
			} else {
				nodeView += frontLineView + this.getDirView(tmpNode, lastFlag)
						+ getChildView(tmpNode, depth + 1);
			}
		}
		return nodeView += "</div>";
	}

	/**
	 * 获取 child leaf check view。.
	 *
	 * @param treeNode - tree node。
	 * @param depth - depth。
	 * @return child leaf check view。
	 */
	public String getChildLeafCheckView(TreeNode treeNode, int depth) {
		String disPlayView = "style='display:none'";
		if (depth == 1)
			disPlayView = "style='display:block'";
		String nodeView = "<div id=\"divId_" + treeNode.getId() + "\""
				+ disPlayView + ">";
		String frontLineView = "";
		for (int i = 0; i < depth; i++)
			frontLineView += this.getLineView();
		Iterator it = treeNode.getChildList().iterator();
		while (it.hasNext()) {
			TreeNode tmpNode = (TreeNode) it.next();
			int lastFlag = 0;
			if (it.hasNext() == false)
				lastFlag = 1;
			if (tmpNode.isLeaf()) {
				nodeView += frontLineView + this.getLeafView(tmpNode, 0);
			} else {
				nodeView += frontLineView + this.getDirNoCheckView(tmpNode, 0)
						+ getChildLeafCheckView(tmpNode, depth + 1);
			}
		}
		return nodeView += "</div>";
	}

	/**
	 * 获取 tree node。.
	 *
	 * @return tree node。
	 */
	public TreeNode getTreeNode() {
		return treeNode;
	}

	/**
	 * 设置 tree node。.
	 *
	 * @param treeNode 新的 tree node 值。
	 */
	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	/**
	 * 获取 branch end img。.
	 *
	 * @return branch end img。
	 */
	public String getBranchEndImg() {
		return branchEndImg;
	}

	/**
	 * 设置 branch end img。.
	 *
	 * @param branchEndImg 新的 branch end img 值。
	 */
	public void setBranchEndImg(String branchEndImg) {
		this.branchEndImg = branchEndImg;
	}

	/**
	 * 获取 plus end img。.
	 *
	 * @return plus end img。
	 */
	public String getPlusEndImg() {
		return plusEndImg;
	}

	/**
	 * 设置 plus end img。.
	 *
	 * @param plusEndImg 新的 plus end img 值。
	 */
	public void setPlusEndImg(String plusEndImg) {
		this.plusEndImg = plusEndImg;
	}

	/**
	 * 获取 img path。.
	 *
	 * @return img path。
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 设置 img path。.
	 *
	 * @param imgPath 新的 img path 值。
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
