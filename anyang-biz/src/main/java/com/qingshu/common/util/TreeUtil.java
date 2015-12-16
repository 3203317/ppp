package com.qingshu.common.util;

import java.util.ArrayList;
import java.util.List;

import com.qingshu.common.json.model.TreeModel;
import com.qingshu.common.json.model.TreeModelParm;

/**
 * @description:(树模型构建工具类)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
public class TreeUtil {
	/**
	 * 构建ComboTree
	 * 
	 * @param list树模型集合
	 * @param modelParm树模型对象
	 * @param ownSet已拥有节点
	 */
	public static List<TreeModel> getTreeList(List<?> list, TreeModelParm modelParm, List<?> ownSet) {
		List<TreeModel> trees = new ArrayList<TreeModel>();
		for(Object obj:list) {
			trees.add(comboTree(obj, modelParm, ownSet));
		}
		return trees;
	}

	/**
	 * 构建ComboTree
	 * 
	 * @param list树模型集合
	 * @param modelParm树模型对象
	 */
	public static List<TreeModel> getTreeList(List<?> list, TreeModelParm modelParm) {
		List<TreeModel> trees = new ArrayList<TreeModel>();
		for(Object obj:list) {
			trees.add(comboTree(obj, modelParm, null));
		}
		return trees;
	}

	/**
	 * 递归树模型
	 */
	private static TreeModel comboTree(Object obj, TreeModelParm modelParm, List<?> ownList) {
		TreeModel tree = new TreeModel();
		ReflectHelper reflectHelper = new ReflectHelper(obj);
		String id = ConvertUtils.getString(reflectHelper.getMethodValue(modelParm.getIdField()));
		String pId = ConvertUtils.getString(reflectHelper.getMethodValue(modelParm.getpIdField(),"0"));
		tree.setId(id);
		tree.setpId(pId);
		tree.setName(ConvertUtils.getString(reflectHelper.getMethodValue(modelParm.getNameField())));
		if(ObjectUtils.isEmpty(pId)) {
			tree.setIsParent(true);
		}
		if(ObjectUtils.isNotEmpty(modelParm.getUrlValue()))
		{
			tree.setUrl(modelParm.getUrlValue()+"?nodeId="+id);
		}
		if(ObjectUtils.isNotEmpty(modelParm.getTargetValue()))
		{
			tree.setTarget(modelParm.getTargetValue());
		}
		List<TreeModel> children = new ArrayList<TreeModel>();
		if(ObjectUtils.isNotEmpty(modelParm.getChildField())) {
			List<?> childList = null;
			if(reflectHelper.getMethodValue(modelParm.getChildField()) != null) {
				Object tempObject=reflectHelper.getMethodValue(modelParm.getChildField());
				if(tempObject!=null){
					childList = (ArrayList<?>) reflectHelper.getMethodValue(modelParm.getChildField());
				}
				if(!ObjectUtils.isEmpty(childList)) {
					tree.setIsParent(true);
					tree.setOpen(modelParm.getIsOpen());
					for(Object object:childList) {
						TreeModel treeModel = comboTree(object, modelParm, ownList);
						children.add(treeModel);
					}
					tree.setChildren(children);// 子节点
				}
				else {
					tree.setIsParent(false);
				}
			}
		}
		if(!ObjectUtils.isEmpty(ownList)) {
			for(Object object:ownList) {
				ReflectHelper oReflectHelper = new ReflectHelper(object);
				String oId = ConvertUtils.getString(oReflectHelper.getMethodValue(modelParm.getIdField()));
				if(id.equals(oId)) {
					tree.setChecked(true);
				}
			}
		}
		return tree;
	}
}
