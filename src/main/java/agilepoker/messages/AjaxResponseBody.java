package agilepoker.messages;

import java.util.List;

import agilepoker.domain.User;

public class AjaxResponseBody {

    private String msg;
    private User result;

    public void setMsg(String msg) {
    	this.msg = msg;
    }
    
    public String getMsg() {
    	return msg;
    }
    
    public void setResult(User result) {
    	this.result = result;
    }
    
    public User getResult() {
    	return result;
    }
    
    

}
