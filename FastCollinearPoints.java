import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private int numSegments;
    private int n;

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Pointer null");

        n = points.length;
        for (int i = 0; i < n; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Has null");
        }
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);

        for (int i = 1; i < n; i++) {
            if (sortedPoints[i].compareTo(sortedPoints[i - 1]) == 0)
                throw new IllegalArgumentException("Has duplicate");
        }

        numSegments = 0;
        for (int i = 0; i < n - 3; i++) {
            Arrays.sort(sortedPoints);
            Arrays.sort(sortedPoints, sortedPoints[i].slopeOrder());
            int left = 1;
            for (int right = 2; right < n; right++) {
                if (Double.compare(sortedPoints[0].slopeTo(sortedPoints[left]),
                        sortedPoints[right].slopeTo(sortedPoints[0])) != 0) {
                    if (right - left >= 3 && sortedPoints[0].compareTo(sortedPoints[left]) < 0) {
                        LineSegment newSegment = new LineSegment(sortedPoints[0], sortedPoints[right - 1]);
                        lineSegments.add(newSegment);
                        numSegments++;
                    }
                    left = right;
                }
            }
            if (n - left >= 3 && sortedPoints[0].compareTo(sortedPoints[left]) < 0) {
                LineSegment newSegment = new LineSegment(sortedPoints[0], sortedPoints[n - 1]);
                lineSegments.add(newSegment);
                numSegments++;
            }
        }

    } // finds all line segments containing 4 or more points

    public int numberOfSegments() {
        return numSegments;

    } // the number of line segments

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[numSegments]);
    } // the line segments
}