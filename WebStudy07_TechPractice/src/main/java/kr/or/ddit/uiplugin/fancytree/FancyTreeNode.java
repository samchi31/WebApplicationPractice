package kr.or.ddit.uiplugin.fancytree;

import java.util.List;

/**
 * FancyTreeNode 의 일부 구조를 반영하여 설계함
 * @see <a href='https://wwwendt.de/tech/fancytree/doc/jsdoc/FancytreeNode.html'>FancyTreeNode API document</a>
 */
public interface FancyTreeNode<T> {
	public String getTitle();
	public String getKey();
	public boolean isFolder();
	public boolean isLazy();
	public T getData();
	public String getType();
	
	public default List<FancyTreeNode<?>> getChildren() {
		return null;
	}
	
	public default FancyTreeNode<?> getParent() {
		return null;
	}
}
