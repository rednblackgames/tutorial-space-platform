package games.rednblack.hyperrunner.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;

@All(ViewPortComponent.class)
public class CameraSystem extends IteratingSystem {

    protected ComponentMapper<TransformComponent> transformMapper;
    protected ComponentMapper<ViewPortComponent> viewportMapper;

    private int focus = -1;
    private final float xMin, xMax, yMin, yMax;

    private final Vector3 mVector3 = new Vector3();
    private static final float cameraSpeed = 5.0f;

    public CameraSystem(float xMin, float xMax, float yMin, float yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    @Override
    protected void process(int entity) {
        ViewPortComponent viewPortComponent = viewportMapper.get(entity);
        Camera camera = viewPortComponent.viewPort.getCamera();

        if (focus != -1) {
            TransformComponent transformComponent = transformMapper.get(focus);

            if (transformComponent != null) {
                float x = Math.max(xMin, Math.min(xMax, transformComponent.x));
                float y = Math.max(yMin, Math.min(yMax, transformComponent.y + 2));

                mVector3.set(x, y, camera.position.z);

                float dt = world.getDelta();
                float alpha = 1.0f - (float) Math.exp(-cameraSpeed * dt);
                if (alpha > 1.0f) alpha = 1.0f;

                camera.position.lerp(mVector3, alpha);
            }
        }
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }
}
