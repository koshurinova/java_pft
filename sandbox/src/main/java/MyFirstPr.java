public class MyFirstPr {

    public static void main(String[] args) {
        hello("user");
        hello("world");

        Square s=new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r=new Rectangle(7,8);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b+ " = " + r.area());
    }

    public static void hello(String slovo) {
        System.out.println("Hello, " + slovo + "!");
    }




}


