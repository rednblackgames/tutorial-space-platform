package games.rednblack.hyperrunner.component;

import games.rednblack.editor.renderer.components.BaseComponent;

public class PlayerComponent implements BaseComponent {

    public int touchedPlatforms = 0;

    public int diamondsCollected = 0;

    @Override
    public void reset() {
        touchedPlatforms = 0;
        diamondsCollected = 0;
    }
}
