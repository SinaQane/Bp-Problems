// Problem: Given four points A, B, C and D, determine if the segments AB and CD intersect.

// I copied the code from https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect

import java.util.Scanner;

class Main {
    static class Point {
        double x;
        double y;
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean onSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;
        return false;
    }

    static int orientation(Point p, Point q, Point r) {
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // colinear

        return (val > 0)? 1: 2; // clock or counterclock wise
    }

    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        double o1 = orientation(p1, q1, p2);
        double o2 = orientation(p1, q1, q2);
        double o3 = orientation(p2, q2, p1);
        double o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double Ax = sc.nextInt();
        double Ay = sc.nextInt();
        double Bx = sc.nextInt();
        double By = sc.nextInt();
        double Cx = sc.nextInt();
        double Cy = sc.nextInt();
        double Dx = sc.nextInt();
        double Dy = sc.nextInt();

        Point p1 = new Point(Ax, Ay);
        Point q1 = new Point(Bx, By);
        Point p2 = new Point(Cx, Cy);
        Point q2 = new Point(Dx, Dy);

        if(doIntersect(p1, q1, p2, q2))
            System.out.println("YES");
        else
            System.out.println("NO");

    }
}
