import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();
    private int numSegments;
    private int n;

    public BruteCollinearPoints(Point[] points) {
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

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                for (int r = q + 1; r < n; r++) {
                    for (int s = r + 1; s < n; s++) {
                        double slopeIJ = sortedPoints[p].slopeTo(sortedPoints[q]);
                        double slopeJK = sortedPoints[q].slopeTo(sortedPoints[r]);
                        double slopeKL = sortedPoints[r].slopeTo(sortedPoints[s]);
                        if (slopeIJ == slopeJK && slopeJK == slopeKL) {
                            Point[] collinearPoints = { sortedPoints[p], sortedPoints[q], sortedPoints[r],
                                    sortedPoints[s] };
                            Arrays.sort(collinearPoints);
                            LineSegment lineSegment = new LineSegment(collinearPoints[0], collinearPoints[3]);
                            lineSegments.add(lineSegment);
                            numSegments += 1;
                        }
                    }
                }
            }
        }
    } // finds all line segments containing 4 points

    public int numberOfSegments() {
        return numSegments;

    } // the number of line segments

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    } // the line segments
}