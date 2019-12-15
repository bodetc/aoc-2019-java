package net.spizzer.aoc2019.helpers.tree;

import net.spizzer.aoc2019.common.Reject;

import java.util.ArrayList;
import java.util.List;

class Node<T> {
    private final T value;
    private Node<T> parent;

    Node(T value) {
        this.value = value;
        parent = null;
    }

    void setParent(Node<T> parent) {
        Reject.ifTrue(this.parent != null && this.parent.value != parent.value, "A node cannot have multiple parents!");
        this.parent = parent;
    }

    List<Node<T>> getParents() {
        Node<T> node = this;
        List<Node<T>> parents = new ArrayList<>();
        while (node.parent != null) {
            node = node.parent;
            parents.add(node);
        }
        return parents;
    }

    T getValue() {
        return value;
    }
}
