package games.rednblack.hyperrunner.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;

import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;
import games.rednblack.editor.renderer.utils.ComponentRetriever;

public class CameraSystem extends IteratingSystem {

    private Entity focus;
    private final float xMin, xMax, yMin, yMax;

    public CameraSystem(float xMin, float xMax, float yMin, float yMax) {
        super(Family.all(ViewPortComponent.class).get());

        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ViewPortComponent viewPortComponent = ComponentRetriever.get(entity, ViewPortComponent.class);
        Camera camera = viewPortComponent.viewPort.getCamera();

        if (focus != null) {
            TransformComponent transformComponent = ComponentRetriever.get(focus, TransformComponent.class);

            if (transformComponent != null) {

                float x = Math.max(xMin, Math.min(xMax, transformComponent.x));
                float y = Math.max(yMin, Math.min(yMax, transformComponent.y + 2));

                camera.position.set(x, y, 0);
            }
        }
    }

    public void setFocus(Entity focus) {
        this.focus = focus;
    }
}
