package ru.javaops.webapp;

import ru.javaops.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    public static void main(String[] args) {
        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";
        Collection<Resume> collection = new ArrayList<>();
        collection.add(new Resume(UUID_1));
        collection.add(new Resume(UUID_2));
        collection.add(new Resume(UUID_3));

        for (Resume r : collection) {
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
//                collection.remove(r);
            }
        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }
        System.out.println(collection.toString());
        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1,new Resume(UUID_1));
        map.put(UUID_2,new Resume(UUID_2));
        map.put(UUID_3,new Resume(UUID_3));

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

    }
}
