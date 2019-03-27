package com.azz.order.client.pojo.vo;

public class DivideDetail {
	private String ledgerNo; // 分账子商编
	private String ledgerName;// 分账商户名称 非必填
	private String amount;// 分账金额

	public DivideDetail(String ledgerNo, String ledgerName, String amount) {
		super();
		this.ledgerNo = ledgerNo;
		this.ledgerName = ledgerName;
		this.amount = amount;
	}

	public DivideDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLedgerNo() {
		return ledgerNo;
	}

	public void setLedgerNo(String ledgerNo) {
		this.ledgerNo = ledgerNo;
	}

	public String getLedgerName() {
		return ledgerName;
	}

	public void setLedgerName(String ledgerName) {
		this.ledgerName = ledgerName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
}
