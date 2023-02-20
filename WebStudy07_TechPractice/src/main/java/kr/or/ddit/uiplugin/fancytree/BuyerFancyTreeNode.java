package kr.or.ddit.uiplugin.fancytree;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import kr.or.ddit.vo.BuyerVO;

public class BuyerFancyTreeNode extends FancyTreeNodeAdapter<BuyerVO> {

	public BuyerFancyTreeNode(BuyerVO data) {
		super(data);
	}

	@Override
	public String getTitle() {
		return getData().getBuyerName();
	}

	@Override
	public String getKey() {
		return getData().getBuyerId();
	}

	@Override
	public boolean isFolder() {
		return getData().hasProds();
	}

	@Override
	public boolean isLazy() {
		return isFolder();
	}
	
	@Override
	public List<FancyTreeNode<?>> getChildren() {
		return Optional.ofNullable(getData().getProdList())
						.orElse(new HashSet<>())
						.stream()
						.map(ProdFancyTreeNode::new)
						.collect(Collectors.toList());
	}
}
