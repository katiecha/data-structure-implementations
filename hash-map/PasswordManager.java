package hash_map;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }

    // put
    @Override
    public void put(K key, V value) { // creates a new Account object using website (key), password (value) and places it in _passwords
        Account<K, V> newAccount = new Account<K, V>(key, value);
        int index = key.hashCode();
        index = (Math.abs(index)) % 50;

        if (_passwords[index] == null) { // index is unoccupied
            _passwords[index] = newAccount;
        } else{ // index is occupied
            Set<K> keySet = keySet();
            if (keySet.contains(key)) { // website (key) is the same
                Account next = _passwords[index];
                while (next != null) {
                    if (next.getWebsite().equals(key)) { // website (key) is the same
                        next.setPassword(value);
                        return;
                    } next = next.getNext();
                }
            } else{ // website (key) is not the same
                Account<K, V> oldHead = _passwords[index]; // sets 'oldHead' to current index
                _passwords[index] = newAccount; // sets current index to 'newAccount'
                _passwords[index].setNext(oldHead); // sets _next of current index to 'old head'
            }
        }
    }

    // get
    // This operation should have O(1) runtime. --> use key's index to find
    @Override
    public V get(K key) { // returns the password for the given key parameter
        int index = key.hashCode();
        index = (Math.abs(index)) % 50;

        Account next = _passwords[index];

        while (next != null){ // iterates through entire linked list
            if (next.getWebsite().equals(key)) { // finds account with appropriate website
                return (V) next.getPassword(); // returns password
            } next = next.getNext();
        } return null; // returns null if not found at index
    }

    // size
    @Override
    public int size() { // returns number of accounts in _passwords
        int size = 0;
        for (int i = 0; i < _passwords.length; i++){ // iterates through entire _passwords hashmap
            if (_passwords[i] != null) { // checks if index is occupied
                Account next = _passwords[i];
                while (next != null){ // finds occupied indexes in linked list
                    size++;
                    next = next.getNext();
                }
            }
        } return size;
    }

    // keySet
    @Override
    public Set<K> keySet() { // returns a Set of all the websites in _passwords
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < _passwords.length; i++){ // iterates through entire _passwords hashmap
            Account next = _passwords[i];
            while (next != null){ // finds occupied indexes in linked list
                keys.add((K) next.getWebsite()); // adds the website to the set
                next = next.getNext();
            }
        } return keys;
    }

    // remove
    @Override
    public V remove(K key) { // removes key, value pair from _passwords and returns password
        int index = key.hashCode();
        index = (Math.abs(index)) % 50; // gets index of key
        Account next = _passwords[index];
        Account prev = _passwords[index];
        while (next != null) {
            if (next.getWebsite().equals(key)) { // found key to be removed
                V password = (V) next.getPassword();
                if (next.equals(prev)){ // on first element
                    _passwords[index] = next.getNext();
                    return password;
                }
                prev.setNext(next.getNext());
                return password;
            }
            prev = next;
            next = next.getNext();
        }
        return null;
    }

    // checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> keys = new ArrayList<K>();

        for (int i = 0; i < _passwords.length; i++){ // iterates through entire _passwords hashmap
            Account next = _passwords[i];
            while (next != null){ // finds occupied indexes in linked list
                if (next.getPassword().equals(value)){
                    keys.add((K) next.getWebsite());
                }
                next = next.getNext();
            }
        }
        return keys;
    }

    // checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) { // checks if entered master password matches MASTER_PASSWORD
        if (enteredPassword.equals(MASTER_PASSWORD)){
            return true;
        } return false;
    }

    // Generates random password of input length
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    // Used for testing
    public Account[] getPasswords() {
        return _passwords;
    }
}
