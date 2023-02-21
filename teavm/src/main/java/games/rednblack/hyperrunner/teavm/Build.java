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
        TeaReflectionSupplier.addReflectionClass("com.artemis.components");
        TeaReflectionSupplier.addReflectionClass("com.artemis.io");
        TeaReflectionSupplier.addReflectionClass("com.artemis.link");
        TeaReflectionSupplier.addReflectionClass("com.artemis.managers");
        TeaReflectionSupplier.addReflectionClass("com.artemis.systems");
        TeaReflectionSupplier.addReflectionClass("com.artemis.utils");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ArchetypeBuilder");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Archetype");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ArtemisMultiException");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ArtemisPlugin");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Aspect");
        TeaReflectionSupplier.addReflectionClass("com.artemis.AspectSubscriptionManager");
        TeaReflectionSupplier.addReflectionClass("com.artemis.BaseComponentMapper");
        TeaReflectionSupplier.addReflectionClass("com.artemis.BaseEntitySystem");
        TeaReflectionSupplier.addReflectionClass("com.artemis.BaseSystem");
        TeaReflectionSupplier.addReflectionClass("com.artemis.BatchChangeProcessor");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Component");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentManager");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentMapper");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentPool");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentRemover");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentTypeFactory");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentType");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ConfigurationElement");
        TeaReflectionSupplier.addReflectionClass("com.artemis.DelayedComponentRemover");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityEdit");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Entity");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityManager");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntitySubscription");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntitySystem");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityTransmuterFactory");
        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityTransmuter");
        TeaReflectionSupplier.addReflectionClass("com.artemis.ImmediateComponentRemover");
        TeaReflectionSupplier.addReflectionClass("com.artemis.InjectionException");
        TeaReflectionSupplier.addReflectionClass("com.artemis.InvalidComponentException");
        TeaReflectionSupplier.addReflectionClass("com.artemis.InvocationStrategy");
        TeaReflectionSupplier.addReflectionClass("com.artemis.Manager");
        TeaReflectionSupplier.addReflectionClass("com.artemis.MundaneWireException");
        TeaReflectionSupplier.addReflectionClass("com.artemis.package-info");
        TeaReflectionSupplier.addReflectionClass("com.artemis.PooledComponent");
        TeaReflectionSupplier.addReflectionClass("com.artemis.SystemInvocationStrategy");
        TeaReflectionSupplier.addReflectionClass("com.artemis.WildBag");
        TeaReflectionSupplier.addReflectionClass("com.artemis.WorldConfigurationBuilder");
        TeaReflectionSupplier.addReflectionClass("com.badlogic.gdx.physics.box2d");

        TeaVMTool tool = TeaBuilder.config(teaBuildConfiguration);
        tool.setObfuscated(false);
        tool.setMainClass(TeaVMLauncher.class.getName());
        TeaBuilder.build(tool, false);
    }
}