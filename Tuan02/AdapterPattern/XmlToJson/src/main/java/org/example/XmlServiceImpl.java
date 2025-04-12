package org.example;

public class XmlServiceImpl implements XmlService {
    private String xmlData;

    @Override
    public String getXmlData() {
        return xmlData;
    }

    @Override
    public void setXmlData(String xmlData) {
        this.xmlData = xmlData;
    }
}