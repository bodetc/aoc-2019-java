package net.spizzer.aoc2019.helpers.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree<T> {
    private final Map<T, Node<T>> nodes;

    public Tree() {
        nodes = new HashMap<>();
    }

    public void addLink(T parentData, T childData) {
        Node<T> parent = nodes.computeIfAbsent(parentData, Node::new);;
        Node<T> child = nodes.computeIfAbsent(childData, Node::new);;

        child.setParent(parent);
    }

    public int getNumberOfIndirectParents() {
        int count = 0;
        for (Node<T> node : nodes.values()) {
            count += node.getParents().size();
        }
        return count;
    }

    public int getShortestPath(T a, T b) {
        List<Node<T>> parentsA = nodes.get(a).getParents();
        List<Node<T>> parentsB = nodes.get(b).getParents();

        Node<T> commonParent = parentsA.stream()
                .filter(nodeA -> parentsB.stream().anyMatch(nodeB -> nodeB.getValue().equals(nodeA.getValue())))
                .findFirst()
                .orElseThrow();

        return parentsA.indexOf(commonParent)+parentsB.indexOf(commonParent);
    }
}
