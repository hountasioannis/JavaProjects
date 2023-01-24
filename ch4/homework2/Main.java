package gr.aueb.cf.ch4.homework2;

import java.io.*;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Line line = new Line(7.0);
        Circle circle = new Circle(10.0);
        Rectangle rectangle = new Rectangle(5.0, 4.0);

        //copy with Copy Constructor
        Line cloneLine = new Line(line);
        cloneLine.setLength(8.0);
       Circle cloneCircle = new Circle(circle);
        cloneCircle.setRadius(5.0);
        Rectangle cloneRectangle = new Rectangle(rectangle);
        cloneRectangle.setHeight(8.0);

        System.out.println(line);
        System.out.println(cloneLine);
        System.out.println(circle);
        System.out.println(cloneCircle);
        System.out.println(rectangle);
        System.out.println(cloneRectangle);

        System.out.println();

        //copy with clone()
        Line cloneLine2 = line.clone();
        cloneLine2.setLength(9.0);
        Circle cloneCircle2 = circle.clone();
        cloneCircle2.setRadius(20.0);
        Rectangle cloneRectangle2 = rectangle.clone();
        cloneRectangle2.setHeight(2.0);

        System.out.println(line);
        System.out.println(cloneLine2);
        System.out.println(circle);
        System.out.println(cloneCircle2);
        System.out.println(rectangle);
        System.out.println(cloneRectangle2);

        System.out.println();

        //Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Public/shape.ser"))){
            oos.writeObject(circle);
            System.out.println("success in serialize");
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception");
            e.printStackTrace();
        } catch (NotSerializableException e1) {
            System.out.println("not serializable exception");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO exception");
            e2.printStackTrace();
        }

        System.out.println();
        //Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Public/shape.ser"))){
            Circle circle1 = (Circle) ois.readObject();
            System.out.println(circle1);
        } catch (FileNotFoundException e) {
            System.out.println("file not found exception");
            e.printStackTrace();
        } catch (ClassNotFoundException e1) {
            System.out.println("class not found exception");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("IO exception");
            e2.printStackTrace();
        }
    }
}
