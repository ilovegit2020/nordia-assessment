package com.app.readwritexml.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.readwritexml.batch.config.JobCompletionListener;
import com.app.readwritexml.entity.PaymentIdentification;
import com.app.readwritexml.util.FileReaderUtility;
import com.app.readwritexml.util.FileReaderUtilityImpl;

public class ReaderForXML implements ItemReader<PaymentIdentification> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReaderForXML.class);
	@Autowired
	private FileReaderUtility fileReaderUtility;
	private boolean batchJobState = false;
//	@Value("${inputxmlfile}")
//	private String INPUT_FILE;

	private static final String INPUT_FILE = "G:\\SNEHA\\InterviewPreparation\\Nordia\\pain.001.valid_original.xml";

	@Override
	public PaymentIdentification read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (!batchJobState) {
			batchJobState = true;
			if (null != fileReaderUtility) {
				PaymentIdentification paymentIdentification = fileReaderUtility.readXMLFile(INPUT_FILE);
				LOGGER.info("Showinf Data from object" + paymentIdentification.toString());
				return paymentIdentification;
			} else {
				fileReaderUtility = new FileReaderUtilityImpl();
				PaymentIdentification paymentIdentification = fileReaderUtility.readXMLFile(INPUT_FILE);
				return paymentIdentification;
			}

		}
		return null;
	}
}
