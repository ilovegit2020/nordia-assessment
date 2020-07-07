package com.app.readwritexml.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.app.readwritexml.entity.PaymentIdentification;

@Component
public class FileReaderUtilityImpl implements FileReaderUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderUtilityImpl.class);
	@Override
	public PaymentIdentification readXMLFile(String filePath) throws JDOMException, IOException {
		LOGGER.info("INSIDE FileReaderUtility"); 
		if (filePath == null) {
			throw new IOException("File not found at given path");
		} else {
			File inputFile = new File(filePath);
			SAXBuilder saxBuilder = new SAXBuilder();

			List<PaymentIdentification> pidList = new ArrayList<PaymentIdentification>();
			PaymentIdentification paymentId = new PaymentIdentification();

			Document document = saxBuilder.build(inputFile);
			Element rootElement = document.getRootElement();// Document
			List<Element> children = rootElement.getChildren();// CstmrCdtTrfInitn

		
			for (Element element : children) { 
				if (element.getName().equals("CstmrCdtTrfInitn")) {
					List<Element> eleChildren = element.getChildren();
					for (int i = 0; i < eleChildren.size(); i++) { 
						if (eleChildren.get(i).getName().equals("PmtInf")) {
							Element ele = eleChildren.get(i);
							List<Element> children2 = ele.getChildren();
							for (int c = 0; c < children2.size(); c++) { 
								if (children2.get(c).getName().equals("CdtTrfTxInf")) {
									Element ele1 = children2.get(c);
									List<Element> childList = ele1.getChildren();
									for (int k = 0; k < childList.size(); k++) { 
										if (childList.get(k).getName().equals("PmtId")) {
											Element eleInner = childList.get(k);
											List<Element> innerChild2 = eleInner.getChildren();

											for (Element element2 : innerChild2) { 
												if (element2.getName().equals("InstrId")) {
													paymentId.setInstrId(element2.getValue());
												}
												if (element2.getName().equals("EndToEndId")) {
													paymentId.setEndToEndId(element2.getValue());
												}
												LOGGER.info("printing inner children : " + element2.getName()
														+ " and value : " + element2.getValue());
											}
											pidList.add(paymentId);

										}
									}
								}
							}

						}
					}

				}

			}
			return paymentId;
		}
	}
}
