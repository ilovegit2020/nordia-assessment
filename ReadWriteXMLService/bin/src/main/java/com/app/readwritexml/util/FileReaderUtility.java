package com.app.readwritexml.util;

import java.io.IOException;

import org.jdom2.JDOMException;

import com.app.readwritexml.entity.PaymentIdentification;


public interface FileReaderUtility {

	public PaymentIdentification readXMLFile(String filePath) throws JDOMException, IOException;
}
