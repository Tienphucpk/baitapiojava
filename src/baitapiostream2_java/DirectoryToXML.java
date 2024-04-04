package baitapiostream2_java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DirectoryToXML {

    public static void main(String[] args) {
        String directoryPath = getDirectoryPathFromUser();
        if (directoryPath == null) {
            System.out.println("Đường dẫn không hợp lệ.");
            return;
        }

        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Đường dẫn không phải là một thư mục.");
            return;
        }

        Document xmlDoc = createXmlDocument(directory);
        String outputFilePath = "directory_structure.xml";

        try {
            writeXmlDocumentToFile(xmlDoc, outputFilePath);
            System.out.println("Cây thư mục đã được lưu vào tệp " + outputFilePath);
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDirectoryPathFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        return scanner.nextLine();
    }

    private static Document createXmlDocument(File directory) {
        Document doc = null;

        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement(directory.getName());
            doc.appendChild(rootElement);

            buildXmlTree(directory, rootElement, doc);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private static void buildXmlTree(File directory, Element parentElement, Document doc) {
        for (File item : Objects.requireNonNull(directory.listFiles())) {
            if (item.isDirectory()) {
                Element subdirElement = doc.createElement(item.getName());
                parentElement.appendChild(subdirElement);
                buildXmlTree(item, subdirElement, doc);
            } else {
                Element fileElement = doc.createElement("file");
                fileElement.appendChild(doc.createTextNode(item.getName()));
                parentElement.appendChild(fileElement);
            }
        }
    }

    private static void writeXmlDocumentToFile(Document doc, String filePath) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        try (FileWriter writer = new FileWriter(filePath)) {
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
        }
    }
}
