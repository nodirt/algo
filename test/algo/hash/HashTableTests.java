package algo.hash;

import java.util.*;

import org.junit.experimental.theories.DataPoints;

import algo.MapTests;
import algo.hash.chaining.*;
import algo.hash.openAddressing.*;

public class HashTableTests extends MapTests {

    @SuppressWarnings("unchecked")
    @DataPoints
    public static Map<Integer, Integer>[] createTables() {
        return new Map[] {new OpenAddressingHashTable<Integer, Integer>(new LinearProbing()),
                new OpenAddressingHashTable<Integer, Integer>(new DoubleHashing()),
                new ChainingHashTable<Integer, Integer>(new DivisionMethod()),
                new ChainingHashTable<Integer, Integer>(new MultiplicationMethod()),
                new ChainingHashTable<Integer, Integer>(new UniversalMethod())};
    }
}
