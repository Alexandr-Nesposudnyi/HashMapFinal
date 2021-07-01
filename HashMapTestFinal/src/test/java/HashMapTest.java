import org.junit.Assert;
import org.junit.Test;


import java.util.NoSuchElementException;

public class HashMapTest {
    @Test
    public void checkIfMapSizeHASIncreased() {
        HashMapIndex hashmap = new HashMapIndex();
        for (int i = 0; i < 10000; i++) {
            hashmap.put(i, i * i);
        }
        Assert.assertEquals("Test failed! The size isn't correct. Expected 10000 but was "
                + hashmap.getSize(), 10000, hashmap.getSize());
        for (int i = 0; i < 10000; i++) {
            Assert.assertEquals(i * i, hashmap.getValue(i));
        }
    }
    @Test(expected = NoSuchElementException.class)
    public void GetByaNonExistentkey() {
        HashMapIndex hashmap = new HashMapIndex();
        hashmap.getValue(35);
    }
    @Test
    public void size() {
        HashMapIndex hashmap = new HashMapIndex();
        for (int i = 0; i < 15; i++) {
            hashmap.put(i, i * i);
        }

        Assert.assertEquals("The test was failed! The size of map isn't correct  Expected 15 but was " + hashmap.getSize(),
                15, hashmap.getSize());
    }



    @Test(expected = NoSuchElementException.class)
    public void getByNonExistedKeyButWithSameBucket() {
        HashMapIndex hashmap = new HashMapIndex();
        hashmap.put(41, 12);
        hashmap.getValue(21);
    }

    @Test
    public void putAndGet() {
        HashMapIndex hashmap = new HashMapIndex();
        hashmap.put(-1, 12);
        hashmap.put(13, 321);
        hashmap.put(0, 255);


        Assert.assertEquals("The test was failed! It's wrong value. Expected 12 but was "
                + hashmap.getValue(-1), 12L, hashmap.getValue(-1));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 321 but was "
                + hashmap.getValue(13), 321L, hashmap.getValue(13));
        Assert.assertEquals("The test was failed! It's wrong value. Expected 266 but was "
                + hashmap.getValue(0), 255L, hashmap.getValue(0));
    }

    @Test
    public void putElementWithSameKey() {
        HashMapIndex hashmap = new HashMapIndex();
        hashmap.put(1, 55);
        hashmap.put(1, 66);

        Assert.assertEquals("Test failed! It should change value when put with same key ",
                1, hashmap.getSize());

        Assert.assertEquals("The test was failed! It's wrong value. Expected 66 but was "
                + hashmap.getValue(1), 66L, hashmap.getValue(1));
    }



    @Test
    public void PutAndGetWithCollision() {
        HashMapIndex hashmap = new HashMapIndex();
        hashmap.put(74, 1);
        hashmap.put(-2, 2);
        hashmap.put(2, 3);
        hashmap.put(62, 4);
        hashmap.put(29, 5);

        Assert.assertEquals("The test was failed! The size of map isn't correct. Expected 3 but was "
                + hashmap.getSize(), 5, hashmap.getSize());
    }

    @Test
    public void getSizeOfEmptyMap() {
        HashMapIndex hashmap = new HashMapIndex();
        Assert.assertEquals("The test was failed! The size of map isn't correct. Expected 0 but was "
                + hashmap.getSize(), 0, hashmap.getSize());
        ;
    }


}


