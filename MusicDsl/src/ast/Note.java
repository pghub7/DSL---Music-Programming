package ast;

import java.lang.StringBuilder;

public class Note extends Node {
    private String note;
    private String octave;
    private String duration;

    public Note(String note, String octave, String duration) {
        this.note = note;
        this.octave = octave;
        this.duration = duration;
    }

    @Override
    public void evaluate(StringBuilder sb) {
        if (this.note.equals("R")) {
            sb.append(note);
            sb.append(duration);
        } else {
            sb.append(this.note);
            sb.append(this.octave);
            sb.append(this.duration);
        }
    }

    public <C,T> T accept(C context, MusicDslVisitor<C,T> v) {
        return v.visit(context, this);
    }
}
