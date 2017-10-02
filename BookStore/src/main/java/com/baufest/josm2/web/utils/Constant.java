package com.baufest.josm2.web.utils;

public class Constant {
	
	public static final Integer MAX_NUMBER_BOOK_REPEATED_CATEGORY = 5;
	public static final Double AMOUNT_MAX = 1000D;
	public static final Integer DISCOUNT_PERCENT_AMOUNT = 10;
	
	public enum CATEGORY{
			
			ART("ART"),
			ADVENTURE("ADVENTURE"),
			COMPUTATION("COMPUTATION"),
			SCIENCE("SCIENCE"),
			HISTORY("HISTORY"),
			NATURE("NATURE");
			
			private String category;
			
			private CATEGORY(String category) {
		        this.category = category;
		    }
			
		    public String getCategory() {
		        return category;
		    }
	};
	
	public enum OPERATIONS{
		ADD("Add"),
		UPDATE("Update"),
		DELETE("Delete"),
		GET("Get");
		
		private String operation;
		
		private OPERATIONS(String operation) {
	        this.operation = operation;
	    }
		
	    public String getOperation() {
	        return operation;
	    }
	}
	
	
	public enum STATUS{
		SUCCESSFUL(0),
		FAIL(1),
		NOTIFICATION(2);
		
		private Integer status;
		
		private STATUS(Integer status) {
	        this.status = status;
	    }
		
	    public Integer getStatus() {
	        return status;
	    }
	}
	
	public enum GIFT{
		BOOK("Free Book "),
		DISCOUNT("Discount Obtained ");
		
		private String gift;
		
		private GIFT(String gift) {
	        this.gift = gift;
	    }
		
	    public String getGift() {
	        return gift;
	    }
	}
	
	public enum CATEGORY_DISCOUNT{
		
		COMPUTATION("COMPUTATION"),
		SCIENCE("SCIENCE");
		
		private String category;
		
		private CATEGORY_DISCOUNT(String category) {
	        this.category = category;
	    }
		
	    public String getCategory() {
	        return category;
	    }
};
}

