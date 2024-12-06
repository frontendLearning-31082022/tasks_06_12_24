import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class task1Test {

    @org.junit.jupiter.api.Test
    void calculatePath_simple1() {
        task1 circleArray=new task1(4,3);
        assert  circleArray.calculatePath().equals("13");
    }
    @org.junit.jupiter.api.Test
    void calculatePath_simple2() {
        task1 circleArray=new task1(5,4);
        assert  circleArray.calculatePath().equals("14253");
    }


    @Test
    void getEndPoint_overStep() {
        task1 circleArray=new task1(5,6);;
        assert circleArray.getEndPoint()==0;
    }

    @Test
    void getEndPoint_overStep_2() {
        task1 circleArray=new task1(5,9);;
        assert circleArray.getEndPoint()==3;
    }
}