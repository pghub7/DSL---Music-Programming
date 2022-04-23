package parser;

import ast.*;
import libs.Tokens;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// alternatively:
// public class ParseTreeToAST extends TinyDOTParserBaseVisitor<Node> { // this gives default implementations (but you need to be careful not to forget to override the ones you want!)

public class ParseTreeToAST extends AbstractParseTreeVisitor<Node> implements MusicDslParserVisitor<Node> {
    private final Tokens tokens;

    public ParseTreeToAST(Lexer lexer) {
        this.tokens = new Tokens(lexer);
    }

    @Override
    public Song visitSong(MusicDslParser.SongContext ctx) {
        String songTitle = ParsingValidator.checkAndGetSongTitle(ctx);
        int tempo = ParsingValidator.checkAndGetTempo(ctx);
        List<Rhythm> rhythms = new ArrayList();
        List<Sequence> sequences = new ArrayList();
        int songLoop = 0;
        if (ctx.loop() != null) {
            // TODO Error handling if loop cannot be parsed to int.
            songLoop = Integer.parseInt(ctx.loop().NUMBER().getText());
        }

        if (ctx.rhythm().isEmpty()) {
            ParsingValidator.errorMessages.add("Ensure there are no typos in rhythm declarations");
        }
        for (MusicDslParser.RhythmContext r : ctx.rhythm()) {
            rhythms.add(visitRhythm(r));
        }

        if (ctx.rhythm().isEmpty()) {
            ParsingValidator.errorMessages.add("Ensure there are no typos in sequence declarations");
        }
        for (MusicDslParser.SequenceContext s : ctx.sequence()) {
            sequences.add(visitSequence(s));
        }

        return new Song(songTitle, tempo, rhythms, sequences, songLoop);
    }

    @Override
    public Rhythm visitRhythm(MusicDslParser.RhythmContext ctx) {
        ParsingValidator.checkRhythm(ctx);
        String rhythmTitle = ctx.QUOTED_NAME().getText();
        String instrument = ctx.INSTRUMENT().getText();
        int loopTimes = 0;
        if (ctx.loop() != null) {
            loopTimes = Integer.parseInt(ctx.loop().NUMBER().getText());
        }
        List<Note> notes = new ArrayList();
        for (TerminalNode n : ctx.notes().NOTE()) {
            String note = n.getText();

            if(note.contains("R")) {
                // implies a Rest token; specify zero for octave in this case
                notes.add(new Note(note.substring(0, 1),"0" , note.substring(1, 2)));
            }else {
                notes.add(new Note(note.substring(0, 1), note.substring(1, 2), note.substring(2, 3)));
            }
        }
        return new Rhythm(rhythmTitle, instrument, notes, loopTimes);
    }

    @Override
    public Sequence visitSequence(MusicDslParser.SequenceContext ctx) {
        int beats = 0;
        if (ctx.beatsoffset() != null) {
            beats = Integer.parseInt(ctx.beatsoffset().NUMBER().getText());
        }

        String startingRhythm = ctx.QUOTED_NAME().getText();

        List<String> parallelRhythms = new ArrayList();
        List<String> followupRhythms = new ArrayList<>();
        for(MusicDslParser.ParallelPlayContext p : ctx.parallelPlay()) {
            parallelRhythms.add(p.QUOTED_NAME().getText());
        }

        for (MusicDslParser.FollowupPlayContext f : ctx.followupPlay()) {
            followupRhythms.add(f.QUOTED_NAME().getText());
        }

        return new Sequence(startingRhythm, beats, followupRhythms, parallelRhythms);
    }

    @Override
    public Node visitParallelPlay(MusicDslParser.ParallelPlayContext ctx) {
        // return visitSequence(ctx.sequence());
        return null;
    }

    @Override
    public  Sequence visitFollowupPlay(MusicDslParser.FollowupPlayContext ctx) {
        return null;
    }

    @Override
    public Node visitTitle(MusicDslParser.TitleContext ctx) { return null; }

    @Override
    public Node visitTempo(MusicDslParser.TempoContext ctx) {
        return null;
    }

    @Override
    public Note visitNotes(MusicDslParser.NotesContext ctx) {
        return null;
    }

    @Override
    public Node visitLoop(MusicDslParser.LoopContext ctx) {
        return null;
    }

    @Override
    public Node visitComment(MusicDslParser.CommentContext ctx) { return null; }

    @Override
    public Node visitBeatsoffset(MusicDslParser.BeatsoffsetContext cts) { return null; }
}
