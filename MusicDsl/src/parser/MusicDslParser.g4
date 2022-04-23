parser grammar MusicDslParser;
options { tokenVocab=MusicDslLexer; }

song: SONG_START title tempo loop? rhythm+ sequence+ ;
title: SONG_TITLE QUOTED_NAME ;
tempo: TEMPO_START NUMBER;
rhythm: RHYTHM_START QUOTED_NAME USING INSTRUMENT COLON notes loop? ;
notes: NOTE+ ;
loop: LOOP_START NUMBER ;
sequence: (beatsoffset)? PLAY QUOTED_NAME (parallelPlay)* (followupPlay)* ;
beatsoffset: AFTER_START NUMBER BEATS AFTER_END THEN ;
parallelPlay: AND QUOTED_NAME ;
followupPlay: THEN PLAY QUOTED_NAME ;
comment: COMMENT_START QUOTED_NAME ;