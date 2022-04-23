package ast;

import org.jfugue.pattern.Pattern;

import java.util.HashSet;
import java.util.List;
import java.lang.StringBuilder;
import java.util.HashMap;

public class Song extends Node {
    private String title;
    private int tempo;
    private List<Rhythm> rhythms;
    private List<Sequence> sequences;
    public int loops;
    private HashSet<String> rhythmNamesSet;

    public Song(String title, int tempo, List<Rhythm> rhythms, List<Sequence> sequences, int loops) {
        this.title = title;
        this.tempo = tempo;
        this.rhythms = rhythms;
        this.sequences = sequences;
        this.loops = loops;
        this.rhythmNamesSet = new HashSet<>();
    }

    public List<Rhythm> getRhythms() { return this.rhythms; }

    public List<Sequence> getSequences() { return this.sequences; }

    @Override
    public void evaluate(StringBuilder sb) {
        HashMap<String, Pattern> rhythmMap = new HashMap<>();
        int voiceCounter = -1;
        Pattern song = new Pattern();

        song.setTempo(this.tempo);

        for (Rhythm r : rhythms) {
            r.evaluate(sb);
            rhythmMap.put(r.title, r.pattern);
            // clean StringBuilder before next iteration
            sb.delete(0, sb.length());
        }

        for (Sequence s : this.sequences) {
            // send required info to sequence node
            s.setRhythmMap(rhythmMap);
            s.setVoiceCounter(voiceCounter);

            s.evaluate(sb);

            //update voiceCounter state since
            // the sequence node can manipulate it
            voiceCounter = s.getVoiceCounter();

            song.add(sb.toString());
            sb.delete(0, sb.length());
        }

        sb.delete(0, sb.length());
        sb.append(song.toString());
    }

    public <C,T> T accept(C context, MusicDslVisitor<C,T> v) {
        return v.visit(context, this);
    }
}
