
package forms;

public class ConferenceOptionForm {

	private boolean	evaluate;
	private boolean	msgReg;
	private boolean	msgSub;
	private boolean	addActivity;
	private boolean	reg;
	private boolean	sub;


	public boolean isAddActivity() {
		return this.addActivity;
	}

	public void setAddActivity(final boolean addActivity) {
		this.addActivity = addActivity;
	}

	public boolean isEvaluate() {
		return this.evaluate;
	}

	public void setEvaluate(final boolean evaluate) {
		this.evaluate = evaluate;
	}

	public boolean isMsgReg() {
		return this.msgReg;
	}

	public void setMsgReg(final boolean msgReg) {
		this.msgReg = msgReg;
	}

	public boolean isMsgSub() {
		return this.msgSub;
	}

	public void setMsgSub(final boolean msgSub) {
		this.msgSub = msgSub;
	}

	public boolean isReg() {
		return this.reg;
	}

	public void setReg(final boolean reg) {
		this.reg = reg;
	}

	public boolean isSub() {
		return this.sub;
	}

	public void setSub(final boolean sub) {
		this.sub = sub;
	}

}
