package agilepoker.messages;

public class BooleanMessage {
	private boolean booleanValue;
	
	public BooleanMessage() {}
	public BooleanMessage(boolean booleanValue) { this.booleanValue = booleanValue; }

	public boolean getBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
}
