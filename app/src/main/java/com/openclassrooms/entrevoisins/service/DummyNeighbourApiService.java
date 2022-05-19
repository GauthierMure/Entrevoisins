package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * @param position
     */
    @Override
    public Neighbour getNeighbour(int position){return neighbours.get(position);}

    @Override
    public void addFavoriteNeighbour(int position) {
        neighbours.get(position).setFavorite(true);
    }

    @Override
    public void removeFavoriteNeighbour(int position) {
        neighbours.get(position).setFavorite(false);
    }
}
