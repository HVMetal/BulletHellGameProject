package org.bullethell.game.entities.physics.collision.management;

public final class HashGridParameters implements SpacePartitionParameters {
    private int minRowIndex, maxRowIndex, minColIndex, maxColIndex;

    @Override
    public int[] getParameters() {
        return new int[]{minRowIndex, maxRowIndex, minColIndex, maxColIndex};
    }

    @Override
    public void setParameters(int[] param) {
        minRowIndex = param[0];
        maxRowIndex = param[1];
        minColIndex = param[2];
        maxColIndex = param[3];
    }
}
