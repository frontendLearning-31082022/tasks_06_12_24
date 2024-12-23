import java.util.ArrayList;
import java.util.stream.Collectors;


public class CircleArrayPathFinder {
    private int size;

    private int distance;

    int curStartPoint = 0;
    private ArrayList<Integer> orderFirstEls;

    public CircleArrayPathFinder(int size, int distance) {
        this.size = size;
        this.distance = distance - 1;
        orderFirstEls = new ArrayList();
    }

    public static void main(String[] args) {
        System.out.println(new CircleArrayPathFinder(Integer.valueOf(args[0]),
                Integer.valueOf(args[1])).calculatePath());
    }

    public String calculatePath() {
        boolean allFinded = false;
        while (!allFinded) {
            calculateOneLoop();
            if (curStartPoint == 0) allFinded = true;
        }

        return orderFirstEls.stream().map(x -> String.valueOf(x)).collect(Collectors.joining());
    }

    private void calculateOneLoop() {
//        distinctIntervals.add(curStartPoint);
        orderFirstEls.add(curStartPoint + 1);
        curStartPoint = getEndPoint();
    }

    public int getEndPoint() {
        int offsetPos = curStartPoint + distance;
        return offsetPos - (Integer.valueOf((offsetPos) / size) * size);
    }
}
