package algo.hash;

import algo.hash.openAddressing.OpenAddressingHashTable;

public class OpenAddressingTest extends HashTableTest {

    @Override
    protected AbstractHashTable<Integer, Integer> create() {
        return new OpenAddressingHashTable<Integer, Integer>();
    }
    
}
