package org.bullethell.game.entities.physics.collision.management;

import org.bullethell.game.entities.model.interfaces.CollisionEntity;

import java.util.*;

public final class SpatialHashGrid implements SpatialPartition<CollisionEntity> {
    private final int cellDimension, gridWidth, gridHeight;
    private final Map<Integer, List<CollisionEntity>> hashGrid;

    private final static int CELL_INIT_CAPACITY = 25;

    public SpatialHashGrid(int spaceWidth, int spaceHeight, int cellDimension) {
        this.cellDimension = cellDimension;
        gridWidth = (int) Math.ceil(1f * spaceWidth / cellDimension);
        gridHeight = (int) Math.ceil(1f * spaceHeight / cellDimension);

        hashGrid = new HashMap<>();

        for(int row = 0; row < gridHeight; ++row) {
            for(int column = 0; column < gridWidth; ++column) {
                hashGrid.put(hashFunction(row, column), new ArrayList<>(CELL_INIT_CAPACITY));
            }
        }
    }

    private int hashFunction(int row, int column) {
        return (column + gridWidth * row);
    }

    private int[] generateIndices(float[] bounds) {
        final int minRow = forceBounds((int) Math.floor(bounds[1] / cellDimension), gridHeight);
        final int maxRow = forceBounds((int) Math.ceil((bounds[1] + bounds[3]) / cellDimension), gridHeight);
        final int minCol = forceBounds((int) Math.floor(bounds[0] / cellDimension), gridWidth);
        final int maxCol = forceBounds((int) Math.ceil((bounds[0] + bounds[2]) / cellDimension), gridWidth);

        return new int[]{minRow, maxRow, minCol, maxCol};
    }

    private int forceBounds(int value, int upperBound) {
        return (value < 0 ? 0 : Math.min(value, upperBound));
    }

    @Override
    public void add(CollisionEntity object) {
        final int[] indices = generateIndices(object.getHitbox().getDimensions());

        if(object.getCollisionParametersComponent() == null) {
            object.setCollisionParametersComponent(new HashGridParameters());
        }
        object.getCollisionParametersComponent().setParameters(indices);

        for(int row = indices[0]; row < indices[1]; ++row) {
            for(int column = indices[2]; column < indices[3]; ++column) {
                hashGrid.get(hashFunction(row, column)).add(object);
            }
        }
    }

    @Override
    public void remove(CollisionEntity object) {
        final int[] indices = object.getCollisionParametersComponent().getParameters();

        for(int row = indices[0]; row < indices[1]; ++row) {
            for(int column = indices[2]; column < indices[3]; ++column) {
                hashGrid.get(hashFunction(row, column)).remove(object);
            }
        }
    }

    @Override
    public void update(CollisionEntity object) {
        final int[] newIndices = generateIndices(object.getHitbox().getDimensions());
        final int[] currentIndices = object.getCollisionParametersComponent().getParameters();

        if(newIndices[0] == currentIndices[0]
                && newIndices[1] == currentIndices[1]
                && newIndices[2] == currentIndices[2]
                && newIndices[3] == currentIndices[3]) {
            return;
        }

        remove(object);
        add(object);
    }

    @Override
    public Set<CollisionEntity> getObjectsNearby(CollisionEntity object) {
        final Set<CollisionEntity> objectList = new HashSet<>();
        final int[] indices = object.getCollisionParametersComponent().getParameters();

        for(int row = indices[0]; row < indices[1]; ++row) {
            for(int column = indices[2]; column < indices[3]; ++column) {
                final Collection<CollisionEntity> nearbyObjectsList = hashGrid.get(hashFunction(row, column));

                for(CollisionEntity nearbyObject : nearbyObjectsList) {
                    if(nearbyObject != object) {
                        objectList.add(nearbyObject);
                    }
                }
            }
        }
        return objectList;
    }

    @Override
    public void clear() {
        for(int row = 0; row < gridHeight; row++) {
            for(int column = 0; column < gridWidth; column++) {
                hashGrid.get(hashFunction(row, column)).clear();
            }
        }
    }
}
