package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyNeighbourGenerator {

    public static List<Neighbour> DUMMY_NEIGHBOURS = Arrays.asList(
            new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", false),
            new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", false),
            new Neighbour(3, "Chloé", "http://i.pravatar.cc/150?u=a042581f4e29026704f", false),
            new Neighbour(4, "Vincent", "http://i.pravatar.cc/150?u=a042581f4e29026704a", false),
            new Neighbour(5, "Elodie", "http://i.pravatar.cc/150?u=a042581f4e29026704b", false),
            new Neighbour(6, "Sylvain", "http://i.pravatar.cc/150?u=a042581f4e29026704c", false),
            new Neighbour(7, "Laetitia", "http://i.pravatar.cc/150?u=a042581f4e29026703d", false),
            new Neighbour(8, "Dan", "http://i.pravatar.cc/150?u=a042581f4e29026703b", false),
            new Neighbour(9, "Joseph", "https://i.pravatar.cc/150?u=a042581f4e29026754d", false),
            new Neighbour(10, "Emma", "http://i.pravatar.cc/150?u=a042581f4e29026706d", false),
            new Neighbour(11, "Patrick", "http://i.pravatar.cc/150?u=a042581f4e29026702d", false),
            new Neighbour(12, "Ludovic", "http://i.pravatar.cc/150?u=a042581f3e39026702d", false)
    );
    public static List<Neighbour> DUMMY_NEIGHBOURS_ADD = Arrays.asList(
            new Neighbour(13, "Farah", "https://i.pravatar.cc/150?u=a042581f4e290269804c", false),
            new Neighbour(14, "Fabien", "https://i.pravatar.cc/150?u=a042581f4e297899804dd", false),
            new Neighbour(15, "Anas", "https://i.pravatar.cc/150?u=a042581f4e297899804dr", false),
            new Neighbour(16, "Lyam", "https://i.pravatar.cc/150?u=a042581f4e297899804dg", false),
            new Neighbour(17, "Lionel", "https://i.pravatar.cc/150?u=a042581ffhh2978h77804dg", false)


    );

    static List<Neighbour> generateNeighbours() {
        return new ArrayList<>(DUMMY_NEIGHBOURS);

    }

}
