package com.fullerton.edu.cpsc.cpsc476.Util;

public class ErrorUtilCPSC{
	private String ERROR_MESSAGE;
	
	private String PAGE_TO_REDIRECT;
	
	private Boolean isValidToContinue;
	
	public Boolean getIsValidToContinue() {
		return isValidToContinue;
	}

	public void setIsValidToContinue(Boolean isValidToContinue) {
		this.isValidToContinue = isValidToContinue;
	}

	public String getERROR_MESSAGE() {
		return ERROR_MESSAGE;
	}

	public void setERROR_MESSAGE(String eRROR_MESSAGE) {
		ERROR_MESSAGE = eRROR_MESSAGE;
	}

	public String getPAGE_TO_REDIRECT() {
		return PAGE_TO_REDIRECT;
	}

	public void setPAGE_TO_REDIRECT(String pAGE_TO_REDIRECT) {
		PAGE_TO_REDIRECT = pAGE_TO_REDIRECT;
	}	
	
	
	public ErrorUtilCPSC everythingIsFine(ErrorUtilCPSC e){
		e.ERROR_MESSAGE = null;
		e.PAGE_TO_REDIRECT = null;
		e.isValidToContinue = Boolean.TRUE;
		return e;
	}
	
	public ErrorUtilCPSC specificMessage(ErrorUtilCPSC e,String errorMessage){
		e.ERROR_MESSAGE = errorMessage;
		e.PAGE_TO_REDIRECT = null;
		e.isValidToContinue = Boolean.FALSE;
		return e;
	}
	
	public ErrorUtilCPSC specificMessageAndPage(ErrorUtilCPSC e,String errorMessage,String page){
		e.ERROR_MESSAGE = errorMessage;
		e.PAGE_TO_REDIRECT = page;
		e.isValidToContinue = Boolean.FALSE;
		return e;
	}
}
