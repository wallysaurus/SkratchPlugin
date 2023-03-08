package io.papermc.paper.plugin.entrypoint.dependency;

import com.google.common.graph.Graph;
import com.google.common.graph.Graphs;
import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.provider.entrypoint.DependencyContext;

import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
public class GraphDependencyContext implements DependencyContext {

    private final Graph<String> dependencyGraph;

    public GraphDependencyContext(Graph<String> dependencyGraph) {
        this.dependencyGraph = dependencyGraph;
    }

    @Override
    public boolean isTransitiveDependency(PluginMeta plugin, PluginMeta depend) {
        String pluginIdentifier = plugin.getName();

        if (this.dependencyGraph.nodes().contains(pluginIdentifier)) {
            Set<String> reachableNodes = Graphs.reachableNodes(this.dependencyGraph, pluginIdentifier);
            if (reachableNodes.contains(depend.getName())) {
                return true;
            }
            for (String provided : depend.getProvidedPlugins()) {
                if (reachableNodes.contains(provided)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasDependency(String pluginIdentifier) {
        return this.dependencyGraph.nodes().contains(pluginIdentifier);
    }

}
