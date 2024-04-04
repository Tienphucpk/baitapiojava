package baitapiostream2_java;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentListToXML {

    public static void main(String[] args) {
        // Tạo danh sách sinh viên
        Student[] students = {
            new Student("Phuc", 20, 3.8),
            new Student("Oanh", 21, 3.9),
            new Student("Sang", 22, 3.7)
        };

        // Xuất danh sách sinh viên dưới dạng tệp XML
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // Tạo phần tử gốc
            Element rootElement = doc.createElement("Students");
            doc.appendChild(rootElement);

            // Tạo phần tử cho mỗi sinh viên
            for (Student student : students) {
                Element studentElement = doc.createElement("Student");
                rootElement.appendChild(studentElement);

                // Thêm thuộc tính cho sinh viên
                studentElement.setAttribute("Name", student.getName());
                studentElement.setAttribute("Age", String.valueOf(student.getAge()));
                studentElement.setAttribute("GPA", String.valueOf(student.getGpa()));
            }

            // Ghi dữ liệu vào tệp XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileWriter("student_list.xml"));
            transformer.transform(source, result);

            System.out.println("Danh sách sinh viên đã được lưu vào tệp student_list.xml");
        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}

// Định nghĩa lớp Student
class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }
}
