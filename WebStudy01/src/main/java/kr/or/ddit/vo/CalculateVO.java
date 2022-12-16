package kr.or.ddit.vo;

import java.io.Serializable;

import kr.or.ddit.enumpkg.OperatorType;

public class CalculateVO implements Serializable {
	private int leftOp;
	private int rightOp;
	private OperatorType operator;
	
	public int getResult() {
		return operator.operate(leftOp, rightOp);
	}
	public String getExpression() {
		return operator.getExpression(leftOp, rightOp);
	}
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}	
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CalculateVO [leftOp=");
		builder.append(leftOp);
		builder.append(", rightOp=");
		builder.append(rightOp);
		builder.append(", operator=");
		builder.append(operator);
		builder.append("]");
		return builder.toString();
	}
	
}
