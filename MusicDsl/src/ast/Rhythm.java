package ast;

import org.jfugue.pattern.Pattern;

import java.util.List;
import java.lang.StringBuilder;

public class Rhythm extends Node {
    public String title;
    public Pattern pattern;
    private String instrument;
    private List<Note> notes;
    private int loopTimes;

    public Rhythm(String title, String instrument, List<Note> notes, int loopTimes) {
        this.title = title;
        this.instrument = instrument;
        this.notes = notes;
        this.loopTimes = loopTimes;
        this.pattern = new Pattern();
    }

    public String getInstrument() { return this.instrument; }

    @Override
    public void evaluate(StringBuilder sb) {
        Pattern rhythm = new Pattern();
        for(Note n : notes) {
            n.evaluate(sb);
            sb.append(" ");
        }
        rhythm.add(sb.toString());
        rhythm.setInstrument(instrument);
        if (this.loopTimes > 0) {
            rhythm.repeat(loopTimes);
        }

        this.pattern = rhythm;

        sb.delete(0, sb.length());
        sb.append(rhythm.toString());

        // System.out.println("Rhythm: " + this.title);
        // System.out.println(sb.toString());
    }

    public <C,T> T accept(C context, MusicDslVisitor<C,T> v) {
        return v.visit(context, this);
    }
}
