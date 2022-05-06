package Ducks;

import javafx.scene.layout.Pane;

public abstract class Duck extends Pane {
    private DuckDirection duckDirection = null;

    public DuckDirection getDuckDirection() {
        return duckDirection;
    }

    public void setDuckDirection(DuckDirection duckDirection) {
        this.duckDirection = duckDirection;
    }
}
