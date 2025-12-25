package games.rednblack.hyperrunner.teavm;

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle;
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration;
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder;
import com.github.xpenatan.gdx.backends.teavm.config.plugins.TeaReflectionSupplier;

import java.io.File;
import java.io.IOException;
import org.teavm.tooling.TeaVMTool;
import org.teavm.vm.TeaVMOptimizationLevel;

public class Build {

    public static void main(String[] args) throws IOException {
        TeaBuildConfiguration teaBuildConfiguration = new TeaBuildConfiguration();
        teaBuildConfiguration.assetsPath.add(new AssetFileHandle(".." + File.separatorChar + "assets"));
        teaBuildConfiguration.webappPath = new File("build" + File.separatorChar + "dist").getCanonicalPath();

        //libGDX
        TeaReflectionSupplier.addReflectionClass("com.badlogic.gdx.math.Vector2");

        //Artemis
        TeaReflectionSupplier.addReflectionClass("com.artemis.BaseSystem");
        TeaReflectionSupplier.addReflectionClass("com.artemis.utils.BitVector");
        TeaReflectionSupplier.addReflectionClass("com.artemis.utils.Bag");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Aspect.Builder");
        TeaReflectionSupplier.addReflectionClass("com.artemis.WildBag");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityEdit");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityTransmuter.TransmuteOperation");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentRemover");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentTypeFactory.ComponentTypeListener");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntitySubscription");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Component");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Aspect");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Entity");

        //HyperLap2D Runtime
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.data");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.components");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.SceneLoader");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.systems");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.box2dLight.LightData");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.factory");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.scripts.PhysicsBodyScript");

        //HyperLap2D Extensions
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.spine");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.talos");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.talos.runtime.serialization");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.talos.runtime.modules");

        //Game
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.component");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.system");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.script");

        TeaBuilder.config(teaBuildConfiguration);
        TeaVMTool tool = new TeaVMTool();
        tool.setOptimizationLevel(TeaVMOptimizationLevel.FULL);
        tool.setObfuscated(true);
        tool.setMainClass(TeaVMLauncher.class.getName());
        TeaBuilder.build(tool, false);
    }
}