package com.baiyi.order.action;

import com.baiyi.order.model.Cashbox;
import com.baiyi.order.util.Feedback;

@SuppressWarnings("serial")
public class CashboxAction extends CommonsAction {

	public String update() {
		Cashbox min = cashboxService.findByTerminal(-1);
		Cashbox max = cashboxService.findByTerminal(0);
		if (min == null || max == null) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}

		/* nd100 */
		min.setNd100tw100(nd100tw100Min);
		min.setNd100cn10(nd100cn10Min);
		/* nv9 default:0 */
		/* hopper */
		min.setHoppertw1(hoppertw1Min);
		min.setHoppertw5(hoppertw5Min);
		min.setHoppertw10(hoppertw10Min);
		min.setHoppertw50(hoppertw50Min);
		min.setHoppercn01(hoppercn01Min);
		min.setHoppercn05(hoppercn05Min);
		min.setHoppercn1(hoppercn1Min);

		/* nd100 */
		max.setNd100tw100(nd100tw100Max);
		max.setNd100cn10(nd100cn10Max);
		/* nv9 */
		max.setNv9tw100(nv9tw100Max);
		max.setNv9tw500(nv9tw500Max);
		max.setNv9tw1000(nv9tw1000Max);
		max.setNv9cn1(nv9cn1Max);
		max.setNv9cn5(nv9cn5Max);
		max.setNv9cn10(nv9cn10Max);
		max.setNv9cn20(nv9cn20Max);
		max.setNv9cn50(nv9cn50Max);
		max.setNv9cn100(nv9cn100Max);
		/* hopper */
		max.setHoppertw1(hoppertw1Max);
		max.setHoppertw5(hoppertw5Max);
		max.setHoppertw10(hoppertw10Max);
		max.setHoppertw50(hoppertw50Max);
		max.setHoppercn01(hoppercn01Max);
		max.setHoppercn05(hoppercn05Max);
		max.setHoppercn1(hoppercn1Max);

		cashboxService.update(min);
		cashboxService.update(max);

		jsonData.put("result", Feedback.UPDATE.toString());
		return SUCCESS;
	}

	public String find() {
		Cashbox min = cashboxService.findByTerminal(-1);
		Cashbox max = cashboxService.findByTerminal(0);

		if (min == null) {// TODO init int webcontext
			min = new Cashbox();
			min.setTerminalId(-1);

			/**/
			min.setNd100tw100(100);
			min.setNd100cn10(100);

			min.setHoppertw1(100);
			min.setHoppertw5(100);
			min.setHoppertw10(100);
			min.setHoppertw50(100);
			min.setHoppercn01(150);
			min.setHoppercn05(150);
			min.setHoppercn1(150);

			cashboxService.save(min);
		}
		if (max == null) {
			max = new Cashbox();
			max.setTerminalId(0);

			/**/
			max.setNd100tw100(600);
			max.setNd100cn10(600);

			/**/
			max.setNv9tw100(160);
			max.setNv9tw500(160);
			max.setNv9tw1000(160);
			max.setNv9cn1(80);
			max.setNv9cn5(80);
			max.setNv9cn10(80);
			max.setNv9cn20(80);
			max.setNv9cn50(80);
			max.setNv9cn100(80);

			/**/
			max.setHoppertw1(250);
			max.setHoppertw5(250);
			max.setHoppertw10(250);
			max.setHoppertw50(250);
			max.setHoppercn01(300);
			max.setHoppercn05(300);
			max.setHoppercn1(300);
			cashboxService.save(max);
		}

		min = cashboxService.findByTerminal(-1);
		max = cashboxService.findByTerminal(0);

		jsonData.put("min", min);
		jsonData.put("max", max);
		return SUCCESS;
	}

	/* 找钞 */
	private int nd100tw100Min;
	private int nd100tw100Max;

	private int nd100cn10Min;
	private int nd100cn10Max;
	/* 收钞 */
	private int nv9tw100Min;
	private int nv9tw100Max;
	private int nv9tw500Min;
	private int nv9tw500Max;
	private int nv9tw1000Min;
	private int nv9tw1000Max;

	private int nv9cn1Min;
	private int nv9cn1Max;
	private int nv9cn5Min;
	private int nv9cn5Max;
	private int nv9cn10Min;
	private int nv9cn10Max;
	private int nv9cn20Min;
	private int nv9cn20Max;
	private int nv9cn50Min;
	private int nv9cn50Max;
	private int nv9cn100Min;
	private int nv9cn100Max;
	/* 硬币 */
	private int hoppertw1Min;
	private int hoppertw1Max;
	private int hoppertw5Min;
	private int hoppertw5Max;
	private int hoppertw10Min;
	private int hoppertw10Max;
	private int hoppertw50Min;
	private int hoppertw50Max;

	private int hoppercn01Min;
	private int hoppercn01Max;
	private int hoppercn05Min;
	private int hoppercn05Max;
	private int hoppercn1Min;
	private int hoppercn1Max;

	public int getNd100tw100Min() {
		return nd100tw100Min;
	}

	public void setNd100tw100Min(int nd100tw100Min) {
		this.nd100tw100Min = nd100tw100Min;
	}

	public int getNd100tw100Max() {
		return nd100tw100Max;
	}

	public void setNd100tw100Max(int nd100tw100Max) {
		this.nd100tw100Max = nd100tw100Max;
	}

	public int getNd100cn10Min() {
		return nd100cn10Min;
	}

	public void setNd100cn10Min(int nd100cn10Min) {
		this.nd100cn10Min = nd100cn10Min;
	}

	public int getNd100cn10Max() {
		return nd100cn10Max;
	}

	public void setNd100cn10Max(int nd100cn10Max) {
		this.nd100cn10Max = nd100cn10Max;
	}

	public int getNv9tw100Min() {
		return nv9tw100Min;
	}

	public void setNv9tw100Min(int nv9tw100Min) {
		this.nv9tw100Min = nv9tw100Min;
	}

	public int getNv9tw100Max() {
		return nv9tw100Max;
	}

	public void setNv9tw100Max(int nv9tw100Max) {
		this.nv9tw100Max = nv9tw100Max;
	}

	public int getNv9tw500Min() {
		return nv9tw500Min;
	}

	public void setNv9tw500Min(int nv9tw500Min) {
		this.nv9tw500Min = nv9tw500Min;
	}

	public int getNv9tw500Max() {
		return nv9tw500Max;
	}

	public void setNv9tw500Max(int nv9tw500Max) {
		this.nv9tw500Max = nv9tw500Max;
	}

	public int getNv9tw1000Min() {
		return nv9tw1000Min;
	}

	public void setNv9tw1000Min(int nv9tw1000Min) {
		this.nv9tw1000Min = nv9tw1000Min;
	}

	public int getNv9tw1000Max() {
		return nv9tw1000Max;
	}

	public void setNv9tw1000Max(int nv9tw1000Max) {
		this.nv9tw1000Max = nv9tw1000Max;
	}

	public int getNv9cn1Min() {
		return nv9cn1Min;
	}

	public void setNv9cn1Min(int nv9cn1Min) {
		this.nv9cn1Min = nv9cn1Min;
	}

	public int getNv9cn1Max() {
		return nv9cn1Max;
	}

	public void setNv9cn1Max(int nv9cn1Max) {
		this.nv9cn1Max = nv9cn1Max;
	}

	public int getNv9cn5Min() {
		return nv9cn5Min;
	}

	public void setNv9cn5Min(int nv9cn5Min) {
		this.nv9cn5Min = nv9cn5Min;
	}

	public int getNv9cn5Max() {
		return nv9cn5Max;
	}

	public void setNv9cn5Max(int nv9cn5Max) {
		this.nv9cn5Max = nv9cn5Max;
	}

	public int getNv9cn10Min() {
		return nv9cn10Min;
	}

	public void setNv9cn10Min(int nv9cn10Min) {
		this.nv9cn10Min = nv9cn10Min;
	}

	public int getNv9cn10Max() {
		return nv9cn10Max;
	}

	public void setNv9cn10Max(int nv9cn10Max) {
		this.nv9cn10Max = nv9cn10Max;
	}

	public int getNv9cn20Min() {
		return nv9cn20Min;
	}

	public void setNv9cn20Min(int nv9cn20Min) {
		this.nv9cn20Min = nv9cn20Min;
	}

	public int getNv9cn20Max() {
		return nv9cn20Max;
	}

	public void setNv9cn20Max(int nv9cn20Max) {
		this.nv9cn20Max = nv9cn20Max;
	}

	public int getNv9cn50Min() {
		return nv9cn50Min;
	}

	public void setNv9cn50Min(int nv9cn50Min) {
		this.nv9cn50Min = nv9cn50Min;
	}

	public int getNv9cn50Max() {
		return nv9cn50Max;
	}

	public void setNv9cn50Max(int nv9cn50Max) {
		this.nv9cn50Max = nv9cn50Max;
	}

	public int getNv9cn100Min() {
		return nv9cn100Min;
	}

	public void setNv9cn100Min(int nv9cn100Min) {
		this.nv9cn100Min = nv9cn100Min;
	}

	public int getNv9cn100Max() {
		return nv9cn100Max;
	}

	public void setNv9cn100Max(int nv9cn100Max) {
		this.nv9cn100Max = nv9cn100Max;
	}

	public int getHoppertw1Min() {
		return hoppertw1Min;
	}

	public void setHoppertw1Min(int hoppertw1Min) {
		this.hoppertw1Min = hoppertw1Min;
	}

	public int getHoppertw1Max() {
		return hoppertw1Max;
	}

	public void setHoppertw1Max(int hoppertw1Max) {
		this.hoppertw1Max = hoppertw1Max;
	}

	public int getHoppertw5Min() {
		return hoppertw5Min;
	}

	public void setHoppertw5Min(int hoppertw5Min) {
		this.hoppertw5Min = hoppertw5Min;
	}

	public int getHoppertw5Max() {
		return hoppertw5Max;
	}

	public void setHoppertw5Max(int hoppertw5Max) {
		this.hoppertw5Max = hoppertw5Max;
	}

	public int getHoppertw10Min() {
		return hoppertw10Min;
	}

	public void setHoppertw10Min(int hoppertw10Min) {
		this.hoppertw10Min = hoppertw10Min;
	}

	public int getHoppertw10Max() {
		return hoppertw10Max;
	}

	public void setHoppertw10Max(int hoppertw10Max) {
		this.hoppertw10Max = hoppertw10Max;
	}

	public int getHoppertw50Min() {
		return hoppertw50Min;
	}

	public void setHoppertw50Min(int hoppertw50Min) {
		this.hoppertw50Min = hoppertw50Min;
	}

	public int getHoppertw50Max() {
		return hoppertw50Max;
	}

	public void setHoppertw50Max(int hoppertw50Max) {
		this.hoppertw50Max = hoppertw50Max;
	}

	public int getHoppercn01Min() {
		return hoppercn01Min;
	}

	public void setHoppercn01Min(int hoppercn01Min) {
		this.hoppercn01Min = hoppercn01Min;
	}

	public int getHoppercn01Max() {
		return hoppercn01Max;
	}

	public void setHoppercn01Max(int hoppercn01Max) {
		this.hoppercn01Max = hoppercn01Max;
	}

	public int getHoppercn05Min() {
		return hoppercn05Min;
	}

	public void setHoppercn05Min(int hoppercn05Min) {
		this.hoppercn05Min = hoppercn05Min;
	}

	public int getHoppercn05Max() {
		return hoppercn05Max;
	}

	public void setHoppercn05Max(int hoppercn05Max) {
		this.hoppercn05Max = hoppercn05Max;
	}

	public int getHoppercn1Min() {
		return hoppercn1Min;
	}

	public void setHoppercn1Min(int hoppercn1Min) {
		this.hoppercn1Min = hoppercn1Min;
	}

	public int getHoppercn1Max() {
		return hoppercn1Max;
	}

	public void setHoppercn1Max(int hoppercn1Max) {
		this.hoppercn1Max = hoppercn1Max;
	}

}
