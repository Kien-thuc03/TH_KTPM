package org.example;

public class Main {
    public static void main(String[] args) {
        XmlService xmlService = new XmlServiceImpl();

        String sampleXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                          "<person>\n" +
                          "    <name>John Doe</name>\n" +
                          "    <age>30</age>\n" +
                          "    <city>New York</city>\n" +
                          "</person>";
        xmlService.setXmlData(sampleXml);

        JsonService jsonService = new XmlToJsonAdapter(xmlService);
        
        String jsonData = jsonService.getJsonData();
        System.out.println("Converted to JSON:\n" + jsonData);

        String modifiedJson = "{\"person\":{\"name\":\"Jane Doe\",\"age\":25,\"city\":\"Los Angeles\"}}";
        jsonService.setJsonData(modifiedJson);

        String modifiedXml = xmlService.getXmlData();
        System.out.println("\nConverted back to XML:\n" + modifiedXml);
    }
}