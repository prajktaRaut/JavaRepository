package com.bridgelabz.fundoo.utility;

public class Response {


		Integer statuscode;
		String message;
		Object token;
		
		
		public Response(Integer statuscode, String message, Object token) {
			super();
			this.statuscode = statuscode;
			this.message = message;
			this.token = token;
		}


		public Integer getStatuscode() {
			return statuscode;
		}


		public void setStatuscode(Integer statuscode) {
			this.statuscode = statuscode;
		}


		public String getMessage() {
			return message;
		}


		public void setMessage(String message) {
			this.message = message;
		}


		public Object getToken() {
			return token;
		}


		public void setToken(Object token) {
			this.token = token;
		}


		@Override
		public String toString() {
			return "Response [statuscode=" + statuscode + ", message=" + message + ", token=" + token + "]";
		}

		

}
