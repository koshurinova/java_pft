public class MyFirstPr {

    public static void main(String[] args) {
        hello("user");
        hello("world");
        double len = 5;
        double a = 6;
        double b = 7;
        System.out.println("Площадь квадрата со стороной " + len + " = " + area(len));
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b+ " = " + area(a, b));
    }

    public static void hello(String slovo) {
        System.out.println("Hello, " + slovo + "!");
    }

    public static double area(double l) {
        return l * l;
    }

    public static double area(double a, double b) {
        return a * b;

    }
}


