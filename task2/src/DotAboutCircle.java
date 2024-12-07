import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DotAboutCircle {
    int radius;
    Coord centerCircle;
    List<Coord> dotsList;

    public static void main(String[] args) throws IOException {
        DotAboutCircle task2 = new DotAboutCircle(args);
        task2.getDotsList().forEach(x -> {
            DotStatus dotStatus = task2.calculateDotOnCircle(x);
            System.out.println(DotStatus.valueOf(dotStatus.name()).ordinal());
        });
    }

    private DotStatus calculateDotOnCircle(Coord coord) {
        long cathetusX = coord.x - centerCircle.x;
        long cathetusY = coord.y - centerCircle.y;
        double hypotenuse = Math.sqrt((Math.pow(cathetusX, 2) + Math.pow(cathetusY, 2)));

        if (hypotenuse < radius) return DotStatus.Inside;
        if (hypotenuse > radius) {
            return DotStatus.Outside;
        } else {
            return DotStatus.OnBorderCircle;
        }
    }

    public DotAboutCircle(String[] args) throws IOException {
        this.readFilesArgs(args);
    }

    void readFilesArgs(String[] args) throws IOException {
        String radiusAndCenterPath = args[0];
        List<Integer> coords = Arrays.stream(Files.readAllLines(Path.of(radiusAndCenterPath)).get(0).split(" ")).map(x -> Integer.valueOf(x)).collect(Collectors.toList());
        centerCircle = new Coord(coords.get(0), coords.get(1));
        radius = Integer.valueOf(Files.readAllLines(Path.of(radiusAndCenterPath)).get(1));

        String dotsListPath = args[1];
        this.dotsList = Files.readAllLines(Path.of(dotsListPath)).stream().map(x -> {
            List<Integer> coordsInput = Arrays.stream(x.split(" ")).map(y -> Integer.valueOf(y))
                    .collect(Collectors.toList());
            return new Coord(coordsInput.get(0), coordsInput.get(1));
        }).collect(Collectors.toList());
        new Validator().countDots(this.dotsList);
        new String();
    }


    private class Validator {
        public void coordValue(long value) {
            if (Math.abs(value) > Math.pow(10, 38)) throw new IllegalArgumentException("Нарушено условие. " +
                    "Координаты - рациональные числа в диапазоне от 10 в степени -38 до 10 в степени 38");
        }

        public void countDots(List<Coord> dotsList) {
            if (dotsList.size() > 100 || dotsList.size() < 1) throw new IllegalArgumentException("Нарушено условие. " +
                    "Количество точек от 1 до 100;");
        }
    }

    private class Coord {
        public long x;
        public long y;


        public Coord(long x, long y) {
            Validator validator = new Validator();

            validator.coordValue(x);
            validator.coordValue(y);

            this.x = x;
            this.y = y;
        }
    }

    public List<Coord> getDotsList() {
        return dotsList;
    }

    enum DotStatus {
        OnBorderCircle, Inside, Outside
    }

}
