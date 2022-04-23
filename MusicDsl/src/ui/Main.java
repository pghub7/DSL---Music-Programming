package ui;

import ast.MusicDslChecker;
import ast.Song;
import org.antlr.runtime.MismatchedTokenException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import parser.MusicDslLexer;
import parser.MusicDslParser;
import parser.ParseTreeToAST;
import parser.ParsingValidator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException, MismatchedTokenException {
        JFrame f = new JFrame();
        f.setSize(615,520);
        f.setResizable(false);

        JPanel jPanel1 = new JPanel();
        JLabel titleBox = new JLabel();
        JButton exampleButton = new JButton();
        JButton songButton = new JButton();
        JButton templateButton = new JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        titleBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleBox.setText("Welcome to TrueTempo!");
        titleBox.setFont(new java.awt.Font("Cambria", 0, 35));

        exampleButton.setText("Examples");
        exampleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeExampleFrame();
            }
        });

        templateButton.setText("General Template");
        templateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeTemplateFrame();
            }
        });

        songButton.setText("Create Your Own Song!");
        songButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeCreateSongFrame();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(84, 84, 84)
                                                .addComponent(titleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(templateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(55, 55, 55)
                                                .addComponent(exampleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(193, 193, 193)
                                                .addComponent(songButton, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(57, Short.MAX_VALUE))

        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(titleBox, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(templateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exampleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(songButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(87, Short.MAX_VALUE))
        );

        f.add(jPanel1);
        f.setVisible(true);
    }

    private static void makeTemplateFrame() {
        JFrame templateFrame = new JFrame();
        templateFrame.setSize(635,584);
        templateFrame.setResizable(false);

        JPanel jPanel1 = new javax.swing.JPanel();
        jPanel1.setBackground(new java.awt.Color(229, 216, 241));
        JLabel jLabel1 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setEditable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<HTML><U>General Template for a Song</U></HTML>");
        jLabel1.setFont(new java.awt.Font("Cambria", 0, 35));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("// General song specifications\nCreate song\nWith title \"SONG_TITLE\"\nWith tempo #_BETWEEN_60_AND_250\nLoops: # (this is optional but it defines the number of times to loop the song)\n\n// Can create multiple rhythms of the format \nCreate rhythm \"RHYTHM_NAME\"~INSTRUMENT_NAME:\nANY_NUMBER_OF_NOTES_SPECIFIED_WITH{[A-G or R] [1-9] [W,H,Q,I,S,T,X,O]}\n\t// items in {} specify note letter, octave, note length\nLoops: # (this is optional but it defines the number of times to loop the rhythm)\n\n// Multiple sequences can be created ***see comments below for specifications\n// Additionally, sequences (both sequential and parallel) can be declared one after another\n\n// Sequence specification for sequential rhythms\n< #_FOR_OFFSET beats > (this is optional), Play \"RHYTHM1_NAME\", Play \"RHYTHM2_NAME\", etc...\n\n// Sequence specification for parallel rhythms\n< #_FOR_OFFSET beats > (this is optional), Play \"RHYTHM1_NAME\"\n< #_FOR_OFFSET beats > (this is optional), Play \"RHYTHM2_NAME\"\n< #_FOR_OFFSET beats > (this is optional), Play \"RHYTHM3_NAME\"\nOR\n< #_FOR_OFFSET beats > (this is optional), Play \"RHYTHM1_NAME\" ^ \"RHYTHM2_NAME\"");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        templateFrame.add(jPanel1);
        templateFrame.setVisible(true);
    }

    private static void makeCreateSongFrame() {
        JFrame songFrame = new JFrame();
        songFrame.setSize(540,650);
        songFrame.setResizable(false);

        JPanel jPanel2 = new javax.swing.JPanel();
        JLabel jLabel6 = new javax.swing.JLabel();
        JScrollPane jScrollPane5 = new javax.swing.JScrollPane();
        JTextArea textBox = new javax.swing.JTextArea();
        JButton jButton1 = new javax.swing.JButton();
        JLabel jLabel7 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea textOut = new javax.swing.JTextArea();

        jPanel2.setBackground(new java.awt.Color(221, 204, 255));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("<HTML><U>CREATE SONG!</U></HTML>");
        jLabel6.setFont(new java.awt.Font("Cambria", 0, 35));

        textBox.setColumns(20);
        textBox.setRows(5);
        jScrollPane5.setViewportView(textBox);

        jButton1.setText("Play");
        jButton1.addActionListener(e -> {
            try {
                ProcessSong(textBox.getText());
                textOut.setText("Song compiled successfully.");
            } catch (Exception err) {
                textOut.setText("Error: " + err.getMessage());
            }
        });

        jLabel7.setText("Write your song here:");

        textOut.setColumns(20);
        textOut.setRows(5);
        jScrollPane1.setViewportView(textOut);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(235, 235, 235)
                                                .addComponent(jButton1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(560, Short.MAX_VALUE))
        );

        songFrame.add(jPanel2);
        songFrame.setVisible(true);
    }

    private static void makeExampleFrame() {
        JFrame exampleFrame = new JFrame();
        exampleFrame.setSize(760, 650);
        exampleFrame.setResizable(false);

        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTextArea jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setEditable(false);
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        JTextArea jTextArea2 = new javax.swing.JTextArea();
        jTextArea2.setEditable(false);
        JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
        JTextArea jTextArea3 = new javax.swing.JTextArea();
        jTextArea3.setEditable(false);
        JScrollPane jScrollPane4 = new javax.swing.JScrollPane();
        JTextArea jTextArea4 = new javax.swing.JTextArea();
        jTextArea4.setEditable(false);
        JLabel jLabel2 = new javax.swing.JLabel();
        JLabel jLabel3 = new javax.swing.JLabel();
        JLabel jLabel4 = new javax.swing.JLabel();
        JLabel jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(230, 230, 255));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<HTML><U>EXAMPLES</U></HTML>");
        jLabel1.setFont(new java.awt.Font("Cambria", 0, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Create song\nWith title \"song_2\"\nWith tempo 60\n\nCreate rhythm \"rhythm_1\"~Piano:\nC4Q D4Q E4Q F4Q\nLoops: 2\n\nCreate rhythm \"rhythm_2\"~Trumpet:\nC5W D5W E5W F5W\n\nPlay \"rhythm_1\", Play \"rhythm_2\"");
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Create song\nWith title \"song_3\"\nWith tempo 80\n\nCreate rhythm \"rhythm_1\"~Piano:\nC4Q D4Q E4Q F4Q\nLoops: 4\n\nCreate rhythm \"rhythm_2\"~Trumpet:\nC5W D5W E5W F5W\n\nPlay \"rhythm_1\"\nPlay \"rhythm_2\"");
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("Create song\nWith title \"twinkle_twinkle\"\nWith tempo 100\n\nCreate rhythm \"right_hand\"~Piano:\nC5Q C5Q G5Q G5Q A5Q A5Q G5H \nF5Q F5Q E5Q E5Q D5Q D5Q C5H\n\nCreate rhythm \"left_hand\"~Piano:\nE3Q E3Q E3Q E3Q F3Q F3Q E3H \nD3Q D3Q C3Q C3Q B3Q B3Q C3H\n\nPlay \"right_hand\" \nPlay \"left_hand\"");
        jScrollPane3.setViewportView(jTextArea3);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jTextArea4.setText("Create song\nWith title \"song_1\"\nWith tempo 120\n\nCreate rhythm \"rhythm_1\"~Piano:\nC4Q D4Q E4Q F4Q\nLoops: 1\n\nPlay \"rhythm_1\"");
        jScrollPane4.setViewportView(jTextArea4);

        jLabel2.setText("<HTML><U>Basic Song</U></HTML>");

        jLabel3.setText("<HTML><U>Song with sequential rhythms</U></HTML>");

        jLabel4.setText("<HTML><U>Twinkle Twinkle Little Star</U></HTML>");

        jLabel5.setText("<HTML><U>Song with parallel rhythms</U></HTML>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2))
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(jScrollPane3)
                                                        .addComponent(jScrollPane1))
                                                .addGap(59, 59, 59))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 127, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                        .addComponent(jScrollPane4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3))
                                .addContainerGap(30, Short.MAX_VALUE))
        );

        exampleFrame.add(jPanel1);
        exampleFrame.setVisible(true);
    }

    private static void ProcessSong(String text) throws Exception {
        try {
            MusicDslLexer lexer = new MusicDslLexer(CharStreams.fromString(text));
            ArrayList<Token> tokenArrayList = new ArrayList<>();
            for (Token token : lexer.getAllTokens()) {
                System.out.println(token);
                tokenArrayList.add(token);
            }
            lexer.reset();
            TokenStream tokens = new CommonTokenStream(lexer);
            System.out.println("Done tokenizing");

            MusicDslParser parser = new MusicDslParser(tokens);
            ParseTreeToAST visitor = new ParseTreeToAST(lexer);
            Song parsedProgram = visitor.visitSong(parser.song());
            checkForErrors();
            System.out.println("Done parsing.......");

            MusicDslChecker checker = new MusicDslChecker();
            HashSet<String> uniqueRhythms = new HashSet<>();
            String errors = checker.visit(uniqueRhythms, parsedProgram);
            if(!errors.isEmpty()) {
                System.out.println("Static Checks Failed:\n" + errors);
                throw new Exception(errors);
            }

            StringBuilder sb = new StringBuilder();
            parsedProgram.evaluate(sb);
            System.out.println("Done evaluating");

            String song = sb.toString();
            System.out.println(song);

            // loop entire song if required
            Pattern[] patternArr;
            if (parsedProgram.loops > 0) {
                patternArr = new Pattern[parsedProgram.loops];
            } else {
                patternArr = new Pattern[1];
            }
            patternArr[0] = new Pattern(song);
            for(int i = 1; i < patternArr.length; i++) {
                patternArr[i] = new Pattern(song);
            }
            Player player = new Player();
            player.play(patternArr);

        } catch (Exception e) {
            checkForErrors();
            throw e;
        }
    }

    private static void checkForErrors() throws Exception {
        if (ParsingValidator.errorMessages.size() > 0) {
            StringBuilder errorsSb = new StringBuilder();
            for (String error : ParsingValidator.errorMessages) {
                errorsSb.append(error);
                errorsSb.append("\n");
            }
            ParsingValidator.errorMessages.clear();
            ParsingValidator.errorMessages = new ArrayList<>();
            throw new Exception(errorsSb.toString());
        }
    }
}


// Try playing the song below!
        /*Pattern mainChords = new Pattern("T180 V0 D4Min9hqit Ri G3Majhqi Ri C4Maj9wh Rht ");
        mainChords.add("  D4Minhqit  Ri G4Majhqi   Ri C4Majwh Rht ");
        Pattern pianoTouch = new Pattern("T180 V1 Rw | Rw | Rhi | G4qi G3s A3is CMajis ri");
        pianoTouch.add(" Rw | Rw | Rhi | G4s C5wa100d0 Rw ");

        Pattern introOnce = new Pattern(mainChords, pianoTouch);

        Pattern introRhythm = new Pattern(
                "T180 V9 [CLOSED_HI_HAT]x Rx [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt ");

        Pattern introFirstPart = new Pattern(introOnce, introRhythm.repeat(8));

        Pattern mainRhythm = new Pattern(
                "T180 V9 [BASS_DRUM]x [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt  [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rtt [BASS_DRUM]x [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt [BASS_DRUM]x [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rss [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rtt [BASS_DRUM]x [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [ELECTRIC_SNARE]x [CLOSED_HI_HAT]x [MARACAS]x Rss [BASS_DRUM]x [MARACAS]x Rtt [CLOSED_HI_HAT]x [MARACAS]x Rss [CLOSED_HI_HAT]x [MARACAS]x Rtt ");

        Pattern vocalsSilence = new Pattern("T180 V4 Rw Rw Rw Rw | Rw Rw Rw Rw | Rq ");

        Pattern vocals = new Pattern("T180 V04 ");
        vocals.add("I[TROMBONE]  Rh G5is E5i Ri | G5s Ris E5q Rs | G5q E5i Rs D5q rs C5h Rs");
        vocals.add("I[ALTO_SAX] C4i A5q G5isa50d0 Rs A5s E5i D5is Rs C5qis");
        vocals.add("I[TROMBONE] Rqi A4s G5i E5i Rs | G5is Rs E5q | D5is C5i Rs C5q G4q Ri");
        vocals.add("I[TRUMPET] G3is A3s C4is D4s C4is D4s G4is A4s G4is A4s | E4q rs F4h");
        vocals.add("I[TROMBONE] G5is E5i Ri | G5s Ris E5q Rs | G5q E5i Rs A5is rs G5q A5s E5i D5i ri C5h Rit");
        vocals.add("I[TROMBONE] C5s A3q C5i Rs | D5i Rs Eb5qs Rs | D5q Eb5i Rs D5is Eb5s D4q Rs | C5i A4q C5h Rw Rhi");

        Pattern introSecondPart = new Pattern(mainChords, mainRhythm.repeat(2));

        Pattern bassGuitarSilence = new Pattern("T180 V3 Rw Rw Rw Rw | Rw Rw Rw Rw | Rq ");
        Pattern bassGuitar = new Pattern(
                "T180 V3  I[SLAP_BASS_1] D3is D3s Rhq G3is G3s Rqis B2qi | C3is C3s Rhq D3is D3s Rq E3is E3s Rq | D3is D3s Rhq G2is G2s Rqis B2qi | C3is C3s Rhq G3is G3s Rq A3s Ri G3s E3q ");
        bassGuitar.add(
                "D3is D3s Rhq G2is G2s Rqis B2qi | C3is C3s Rhq D3is D3s Rq E3is E3s Rq D3is D3s Rhq G2is G2s Rqis B2qi C3is C3s Rhq G3i Ri A3q G3is F3s E3q ");

        Pattern introThirdPart = new Pattern(introFirstPart, bassGuitarSilence, bassGuitar.repeat(2), vocalsSilence,
                vocals.repeat(2), introSecondPart.repeat(4));
        player.play(introThirdPart);*/