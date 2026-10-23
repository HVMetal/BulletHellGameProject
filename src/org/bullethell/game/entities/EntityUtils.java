package org.bullethell.game.entities;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class EntityUtils {
    public static <T extends Cloneable> List<T> cloneList(List<T> list) {
        List<T> cloneList = new ArrayList<>();

        for (T item : list) {
            cloneList.add((T) item.cloneComponent());
        }

        return cloneList;
    }
}
