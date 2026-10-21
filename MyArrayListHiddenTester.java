/*
 * Name: Zhiyuan Huang
 * Email: zhh087@ucsd.edu
 * PID: 200336800
 * Sources used: pa2-starter
 *
 * My implementation of ArrayList.
 */

import static org.junit.Assert.*;

import org.junit.*;

/**
 * A class tests MyArrayList, including constructors and all public methods
 */
public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test
     */

    static final int DEFAULT_CAPACITY = 5;
    static final int DEFAULT_MULTIPLE = 4;
    static final int DEFAULT_INSERT = 10;

    // constructor
    Integer[] nullRef, defaultEmpty = {null, null, null, null, null},
        null1 = {1, null, 3}, null2 = {null, 2, null, 4, null, 6, null};
    // append
    Integer[] fullArrInts = {1, 2, 3, 4, 5}, fullArrApp = {1, 2, 3, 4, 5, DEFAULT_INSERT, null, null, null, null}, 
    arrAppNullInts = {1, 2, 3, 4}, arrAppNullApp = {1, 2, 3, 4, null, null, null, null};
    MyArrayList fullArr, arrAppNull;
    // add DEFAULT_MULTIPLE
    int[] addIdx = { 2, 0, 4, 1};
    Integer[][] addExp = {
        {1, null, 2, 3, null, null},
        {0, 1, null, 2, 3, null},
        {0, 1, null, 2, 4, 3},
        {0, 1, 1, null, 2, 4, 3, null, null, null, null, null}
    };
    MyArrayList addNull1;
    // remove DEFAULT_MULTIPLE
    int[] rmIdx = { 2, 0, 4, 1};
    Integer[][] rmExp = {
        {null, 2, 4, null, 6, null, null},
        {2, 4, null, 6, null, null, null},
        {2, 4, null, 6, null, null, null},
        {2, null, 6, null, null, null, null}
    };
    MyArrayList rmNull2;
    // expand
    MyArrayList ex2000;
    MyArrayList zero;
    // outofbounds
    MyArrayList ofbArr;

    private void ofbArrReset(){
        ofbArr = new MyArrayList<>(null1);
        ofbArr.expandCapacity(DEFAULT_CAPACITY);
    }

    @Before
    public void setUp() throws Exception {
        fullArr = new MyArrayList<>(fullArrInts);
        arrAppNull = new MyArrayList<>(arrAppNullInts);
        
        addNull1 = new MyArrayList<>(null1);

        rmNull2 = new MyArrayList<>(null2);

        ex2000 = new MyArrayList<>();
        zero = new MyArrayList<>(0);

        ofbArrReset();
    }

    // ----------------MyArrayList class----------------

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg() {
        boolean thrown = false;
        try {
            new MyArrayList<Integer>(-1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg() {
        assertEquals(defaultEmpty, (new MyArrayList<Integer>(nullRef)).data);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull() {
        assertEquals(null1, (new MyArrayList<Integer>(null1)).data);
        assertEquals(null1.length, (new MyArrayList<Integer>(null1)).size);
        assertEquals(null2, (new MyArrayList<Integer>(null2)).data);
        assertEquals(null2.length, (new MyArrayList<Integer>(null2)).size);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity() {
        fullArr.append(DEFAULT_INSERT);
        assertArrayEquals(fullArr.data, fullArrApp);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull() {
        arrAppNull.append(null);
        assertArrayEquals(arrAppNull.data, arrAppNullApp);
    }

    /**
     * Aims to test the add method when input index is out of bounds
     */
    @Test
    public void testAddOutOfBounds() {
        boolean thrown = false;
        ofbArrReset();
        try {
            ofbArr.add(4,DEFAULT_INSERT);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        thrown = false;
        ofbArrReset();
        try {
            ofbArr.add(-1,DEFAULT_INSERT);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testAddMultiple() {
        for(int i = 0;i<DEFAULT_MULTIPLE;i++){
            addNull1.add(addIdx[i],addIdx[i]);
            assertArrayEquals(addNull1.data, addExp[i]);
        }
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound() {
        boolean thrown = false;
        ofbArrReset();
        try {
            ofbArr.get(3);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        thrown = false;
        ofbArrReset();
        try {
            ofbArr.get(-1);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound() {
        boolean thrown = false;
        ofbArrReset();
        try {
            ofbArr.set(3, DEFAULT_INSERT);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        thrown = false;
        ofbArrReset();
        try {
            ofbArr.set(-1, DEFAULT_INSERT);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple() {
        for(int i = 0;i<DEFAULT_MULTIPLE;i++){
            rmNull2.remove(rmIdx[i]);
            assertArrayEquals(rmNull2.data, rmExp[i]);
        }
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound() {
        boolean thrown = false;
        ofbArrReset();
        try {
            ofbArr.remove(3);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
        thrown = false;
        ofbArrReset();
        try {
            ofbArr.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller() {
        boolean thrown = false;
        try {
            new MyArrayList<Integer>(5).expandCapacity(4);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Aims to test the expandCapacity method when
     * requiredCapacity is greater than current capacity * 2 and default capacity
     */
    @Test
    public void testExpandCapacityLarge() {
        int[] n ={6,11,21,41,81,400,401,2000}, m ={10,20,40,80,160,400,800,2000};
        for(int i = 0;i<n.length;++i){
            ex2000.expandCapacity(n[i]);
            assertEquals(m[i], ex2000.getCapacity());
        }
        
        zero.expandCapacity(3);
        assertEquals(DEFAULT_CAPACITY, zero.getCapacity());
        zero= new MyArrayList<>(0);
        zero.expandCapacity(11);
        assertEquals(11, zero.getCapacity());
    }
}
