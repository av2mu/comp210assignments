package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;
    private int _size;

    public PasswordManager() {
        _passwords = new Account[50];
        _size = 0;
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int x = Math.abs(key.hashCode() % _passwords.length);
        Account newAccount = new Account(key, value);
        if (_passwords[x] == null) {
            _passwords[x] = newAccount;
            _size++;
        } else {
            Account current = _passwords[x];
            Account previous = null;
            while (current != null) {
                if (current.getWebsite().equals(key)) {
                    current.setPassword(value);
                    return;
                }
                previous = current;
                current = current.getNext();
            }
            if (previous != null) {
                previous.setNext(newAccount);
                _size++;
            }
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int x = Math.abs(key.hashCode() % _passwords.length);
        Account current = _passwords[x];
        while (current != null) {
            if (current.getWebsite().equals(key)) {
                return (V) current.getPassword();
            }
            current = current.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];
            while (current != null) {
                keys.add((K) current.getWebsite());
                current = current.getNext();
            }
        }
        return keys;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int x = Math.abs(key.hashCode() % _passwords.length);
        Account current = _passwords[x];
        while (current != null) {
            if (current.getWebsite().equals(key)) {
                if (current.getNext() == null) {
                    _passwords[x] = null;
                    _size--;
                    return (V) current.getPassword();
                } else {
                    _passwords[x] = current.getNext();
                    _size--;
                    return (V) current.getPassword();
                }
            }
            while (current.getNext() != null) {
                if (current.getNext().getWebsite().equals(key)) {
                    if (current.getNext().getNext() == null) {
                        current.setNext(null);
                        _size--;
                        return (V) current.getNext().getPassword();
                    }
                    Account temp = current.getNext();
                    current.setNext(current.getNext().getNext());
                    _size--;
                    return (V) temp.getPassword();
                }
                current = current.getNext();
            }
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> duplicates = new ArrayList<>();
        for (int i = 0; i < _passwords.length; i++) {
            Account current = _passwords[i];
            while (current != null) {
                if (current.getPassword().equals(value)) {
                    duplicates.add((K) current.getWebsite());
                }
                current = current.getNext();
            }
        }
        return duplicates;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        if (enteredPassword.equals(MASTER_PASSWORD)) {
            return true;
        }
        return false;
    }

    /*
    Generates random password of input length
     */
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

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
