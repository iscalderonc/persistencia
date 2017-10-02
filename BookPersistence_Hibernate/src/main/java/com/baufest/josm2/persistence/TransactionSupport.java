package com.baufest.josm2.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import javax.annotation.Resource;

@Component
public class TransactionSupport {

	@Autowired
	@Resource(name="transactionManager")
	private PlatformTransactionManager transactionManager = null;

	public TransactionTemplate getTransactionTemplate() {
		return new TransactionTemplate(transactionManager);
	}
	
}
