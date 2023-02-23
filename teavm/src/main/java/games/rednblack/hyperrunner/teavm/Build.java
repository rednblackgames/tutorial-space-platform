package games.rednblack.hyperrunner.teavm;

import com.github.xpenatan.gdx.backends.teavm.TeaBuildConfiguration;
import com.github.xpenatan.gdx.backends.teavm.TeaBuilder;
import com.github.xpenatan.gdx.backends.teavm.gen.SkipClass;
import com.github.xpenatan.gdx.backends.teavm.plugins.TeaReflectionSupplier;
import java.io.File;
import java.io.IOException;
import org.teavm.tooling.TeaVMTool;

@SkipClass
public class Build {

    public static void main(String[] args) throws IOException {
        TeaBuildConfiguration teaBuildConfiguration = new TeaBuildConfiguration();
        teaBuildConfiguration.assetsPath.add(new File(".." + File.separatorChar + "assets"));
        teaBuildConfiguration.webappPath = new File("build" + File.separatorChar + "dist").getCanonicalPath();
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.spine");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.talos");
        TeaReflectionSupplier.addReflectionClass("com.talosvfx.talos.runtime");

        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.component");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.system");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.script");

        TeaReflectionSupplier.addReflectionClass( "games.rednblack.editor.renderer.data");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.components");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.SceneLoader");
        TeaReflectionSupplier.addReflectionClass( "games.rednblack.editor.renderer.systems");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.box2dLight.LightData");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.factory");

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

        TeaVMTool tool = TeaBuilder.config(teaBuildConfiguration);
        tool.setObfuscated(true);
        tool.setMainClass(TeaVMLauncher.class.getName());
        TeaBuilder.build(tool, false);
    }
}