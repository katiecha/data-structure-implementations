package hash_map;

import java.util.List;
import java.util.Set;

public interface Map <K, V> {

    /**
     * Creates an Account object using the key value pair,
     * and inserts the object at the appropriate index
     * based on the hash of they key. If the key already
     * exists in map, update its value.
     */
    void put(K key, V value);

    /**
     * Returns the value associated with the given key.
     * This operation should have O(1) runtime.
     * If the key is not in the array, return null.
     */
    V get(K key);

    // Returns the number of key-value mappings in the map.
    int size();

    // Returns a Set of all the keys (websites) contained in this map.
    Set<K> keySet();

    /**
     * Removes the Key and value pair from the map
     * and returns the removed value.
     * If the key is not in the array, return null.
     */
    V remove(K key);

    /**
     * Returns a list the website names
     * that have a password matching the parameter
     */
    List<K> checkDuplicate(V value);

    /**
     * Checks to see if the entered Master Password matches
     * the password stored in MASTER_PASSWORD
     */
    boolean checkMasterPassword(String enteredPassword);

    String generateRandomPassword(int length);


}
