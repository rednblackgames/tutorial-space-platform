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
        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.spine");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.talos");
        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner");
        TeaReflectionSupplier.addReflectionClass("com.talosvfx.talos.runtime");
        TeaReflectionSupplier.addReflectionClass("com.artemis.injection");
        TeaReflectionSupplier.addReflectionClass("com.badlogic.gdx.physics.box2d");

        TeaVMTool tool = TeaBuilder.config(teaBuildConfiguration);
        tool.setObfuscated(false);
        tool.setMainClass(TeaVMLauncher.class.getName());
        TeaBuilder.build(tool, false);
    }
}