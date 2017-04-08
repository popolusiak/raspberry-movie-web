package eu.suhajko

import org.junit.runner.notification.RunNotifier
import org.junit.runners.model.InitializationError
import org.spockframework.runtime.RunContext
import org.spockframework.runtime.Sputnik

/**
 * Predefined number of specifications execution. It's configured via system variable -DkintupsRepeat=10.
 * Spring container is loaded only first time, next executions will use existing spring container.
 * It's for debug purpose
 */
class Kintups extends Sputnik {
    Kintups(Class<?> clazz) throws InitializationError {
        super(clazz)
    }

    @Override
    void run(RunNotifier notifier) {
        def n = Integer.valueOf(System.getProperty("kintupsRepeat", "1"));
        super.runExtensionsIfNecessary();
        super.generateSpecDescriptionIfNecessary();
        n.times {
            RunContext.get().createSpecRunner(super.getSpec(), notifier).run();
        }
    }
}
