package ast;

public interface MusicDslVisitor<C, T> {
    T visit(C context, Song s);
    T visit(C context, Sequence s);
    T visit(C context, Rhythm r);
    T visit(C context, Note n);
}
