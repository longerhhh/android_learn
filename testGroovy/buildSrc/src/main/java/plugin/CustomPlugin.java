package plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class CustomPlugin implements Plugin<Project>{
    @Override
    public void apply(Project project) {
        System.out.println("in custom plugin java......................................");
        ExtConfig customConfigJava = project.getExtensions().create("customConfigJava", ExtConfig.class);
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                System.out.println("name in java plugin is " + customConfigJava.getName());
            }
        });
    }
}