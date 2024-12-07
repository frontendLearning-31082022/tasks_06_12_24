class ModifyToOneDigitTest {

    @org.junit.jupiter.api.Test
    void calculateOperations() {
        int[] arr = new int[]{1, 2,3};

        assert  ModifyToOneDigit.calculateOperations(arr)==2;
    }
    @org.junit.jupiter.api.Test
    void calculateOperations2() {
        int[] arr = new int[]{1, 2,4,6,10};

        assert  ModifyToOneDigit.calculateOperations(arr)==13;
    }
    @org.junit.jupiter.api.Test
    void calculateOperations3() {
        int[] arr = new int[]{1, 10,2,9};

        assert  ModifyToOneDigit.calculateOperations(arr)==16;
    }

    @org.junit.jupiter.api.Test
    void calculateOperations_negative() {
        int[] arr = new int[]{-1, 2,3};

        assert  ModifyToOneDigit.calculateOperations(arr)==4;
    }
}