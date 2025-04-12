package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlToJsonAdapter implements JsonService {
    private XmlService xmlService;
    private ObjectMapper jsonMapper;
    private XmlMapper xmlMapper;

    public XmlToJsonAdapter(XmlService xmlService) {
        this.xmlService = xmlService;
        this.jsonMapper = new ObjectMapper();
        this.xmlMapper = new XmlMapper();
    }

    @Override
    public String getJsonData() {
        try {
            // Convert XML to JSON
            JsonNode node = xmlMapper.readTree(xmlService.getXmlData().getBytes());
            return jsonMapper.writeValueAsString(node);
        } catch (Exception e) {
            throw new RuntimeException("Error converting XML to JSON", e);
        }
    }

    @Override
    public void setJsonData(String jsonData) {
        try {
            // Convert JSON to XML
            JsonNode node = jsonMapper.readTree(jsonData);
            String xmlData = xmlMapper.writeValueAsString(node);
            xmlService.setXmlData(xmlData);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to XML", e);
        }
    }
}