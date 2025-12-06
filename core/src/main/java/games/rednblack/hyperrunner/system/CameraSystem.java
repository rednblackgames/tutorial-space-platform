package games.rednblack.hyperrunner.system;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import games.rednblack.editor.renderer.components.TransformComponent;
import games.rednblack.editor.renderer.components.ViewPortComponent;
import games.rednblack.editor.renderer.systems.strategy.FixedTimestep;
import games.rednblack.editor.renderer.systems.strategy.InterpolationSystem;

@FixedTimestep
@All(ViewPortComponent.class)
public class CameraSystem extends IteratingSystem implements InterpolationSystem {

    protected ComponentMapper<TransformComponent> transformMapper;
    protected ComponentMapper<ViewPortComponent> viewportMapper;

    private int focus = -1;
    private final float xMin, xMax, yMin, yMax;

    private final Vector3 logicPos = new Vector3();
    private final Vector3 prevLogicPos = new Vector3();

    private final FloatReference velocityX = new FloatReference();
    private final FloatReference velocityY = new FloatReference();

    private final float smoothTime = 0.15f;
    private boolean initialized = false;

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

        if (!initialized) {
            logicPos.set(camera.position);
            prevLogicPos.set(camera.position);
            initialized = true;
        }

        prevLogicPos.set(logicPos);

        if (focus != -1) {
            TransformComponent transformComponent = transformMapper.get(focus);
            if (transformComponent != null) {
                float targetX = Math.max(xMin, Math.min(xMax, transformComponent.x));
                float targetY = Math.max(yMin, Math.min(yMax, transformComponent.y + 2)); // +2 offset

                float dt = world.getDelta();

                float newX = smoothDamp(logicPos.x, targetX, velocityX, smoothTime, Float.MAX_VALUE, dt);
                float newY = smoothDamp(logicPos.y, targetY, velocityY, smoothTime, Float.MAX_VALUE, dt);

                logicPos.x = newX;
                logicPos.y = newY;
                logicPos.z = camera.position.z;
            }
        }
    }

    @Override
    public void interpolate(float alpha) {
        int[] ids = subscription.getEntities().getData();
        for (int i = 0, s = subscription.getEntities().size(); i < s; i++) {
            int entity = ids[i];
            ViewPortComponent viewPortComponent = viewportMapper.get(entity);
            Camera camera = viewPortComponent.viewPort.getCamera();

            float visualX = prevLogicPos.x * (1f - alpha) + logicPos.x * alpha;
            float visualY = prevLogicPos.y * (1f - alpha) + logicPos.y * alpha;

            camera.position.set(visualX, visualY, logicPos.z);
            camera.update();
        }
    }

    public void setFocus(int focus) {
        this.focus = focus;
    }

    public static float smoothDamp(float current, float target, FloatReference currentVelocity, float smoothTime, float maxSpeed, float deltaTime) {
        smoothTime = Math.max(0.0001f, smoothTime);
        float omega = 2f / smoothTime;
        float x = omega * deltaTime;
        float exp = 1f / (1f + x + 0.48f * x * x + 0.235f * x * x * x);
        float change = current - target;
        float originalTo = target;
        float maxChange = maxSpeed * smoothTime;
        change = Math.max(-maxChange, Math.min(maxChange, change));
        target = current - change;
        float temp = (currentVelocity.value + omega * change) * deltaTime;
        currentVelocity.value = (currentVelocity.value - omega * temp) * exp;
        float output = target + (change + temp) * exp;
        if (originalTo - current > 0.0f == output > originalTo) {
            output = originalTo;
            currentVelocity.value = (output - originalTo) / deltaTime;
        }
        return output;
    }

    public static class FloatReference {
        public float value = 0;
    }
}