import org.junit.jupiter.api.Test;

class CircleArrayPathFinderTest {

    @org.junit.jupiter.api.Test
    void calculatePath_simple1() {
        CircleArrayPathFinder circleArray=new CircleArrayPathFinder(4,3);
        assert  circleArray.calculatePath().equals("13");
    }
    @org.junit.jupiter.api.Test
    void calculatePath_simple2() {
        CircleArrayPathFinder circleArray=new CircleArrayPathFinder(5,4);
        assert  circleArray.calculatePath().equals("14253");
    }


    @Test
    void getEndPoint_overStep() {
        CircleArrayPathFinder circleArray=new CircleArrayPathFinder(5,6);;
        assert circleArray.getEndPoint()==0;
    }

    @Test
    void getEndPoint_overStep_2() {
        CircleArrayPathFinder circleArray=new CircleArrayPathFinder(5,9);;
        assert circleArray.getEndPoint()==3;
    }
}