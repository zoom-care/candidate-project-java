package com.zoomcare.candidatechallenge.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class Node<T> implements Serializable
{
    private T data;
    private Node<T> root;
    private List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public Node<T> addChild(T data) {
        Node<T> child = new Node<>(data);
        child.root = this;
        this.children.add(child);
        return child;
    }
}
