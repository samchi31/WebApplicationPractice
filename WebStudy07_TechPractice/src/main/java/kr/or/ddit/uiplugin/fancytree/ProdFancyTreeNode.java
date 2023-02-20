package kr.or.ddit.uiplugin.fancytree;

import kr.or.ddit.vo.ProdVO;

public class ProdFancyTreeNode extends FancyTreeNodeAdapter<ProdVO>{

	public ProdFancyTreeNode(ProdVO data) {
		super(data);
	}

	@Override
	public String getTitle() {
		return getData().getProdName();
	}

	@Override
	public String getKey() {
		return getData().getProdId();
	}

	@Override
	public boolean isFolder() {
		return false;
	}
}
