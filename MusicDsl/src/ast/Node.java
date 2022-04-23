package ast;

import java.lang.StringBuilder;

public abstract class Node {
    abstract public void evaluate(StringBuilder sb);
}
