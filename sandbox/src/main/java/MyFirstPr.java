public class MyFirstPr {

    public static void main(String[] args) {

            Point one = new Point(3,2);
            Point two = new Point (5,7);
            System.out.println("Расстояние между двумя точками с использованием функции = " + distance(one,two));
            System.out.println("Расстояние между двумя точками с использованием метода = " + one.distance2(two));
                }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
}
}



