package kr.or.ddit.uiplugin.fancytree;

public abstract class FancyTreeNodeAdapter<T> implements FancyTreeNode<T> {
	private T data;
	public FancyTreeNodeAdapter(T data) {
		super();
		this.data = data;
	}
	
	@Override
	public String getType() {
		return getData().getClass().getSimpleName();
	}
	
	public T getData() {
		return data;
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}
}
