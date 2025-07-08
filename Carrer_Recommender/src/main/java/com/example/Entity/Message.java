package com.example.Entity;




public class Message {

	 private String type;   // "alert-success", "alert-danger", etc.
	    private String content;
	    
	    
	    public Message(String content,String type) {
	    	this.content=content;
	    	this.type=type;
	    }
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
