package com.application.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

/***
 *   Note module(class) with data fields and some methods.
 *   Fields:
 *      id - Integer, isn't null and unique.
 *      noteName - String, isn't null.
 *      note - String, isn't null.
 *      userId - Integer, isn't null.
 *      createdAt - LocalDateTime, isn't null and default current time.
 */
public class Note {

    private Integer id;
    private String noteName;
    private String note;
    private Integer userId;
    private LocalDateTime createdAt;

    public Integer getId() { return id; }
    public String getNoteName() {
        return noteName;
    }
    public String getNote() {
        return note;
    }
    public Integer getUserId() {
        return userId;
    }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setNoteName(String noteName) {
        checkNoteNameValidation(noteName);
        this.noteName = noteName;
    }
    public void setNote(String note) {
        checkNoteValidation(note);
        this.note = note;
    }

    /**
     *  Check validation functions are to check whether set data is valid.
     *  They are for every Note class fields.
     * */
    private static void checkNoteNameValidation(String noteName) throws IllegalArgumentException, NullPointerException{
        CheckDataNull.checkDataNull(noteName, "Note name");
        if(noteName.length() > 64)
            throw new IllegalArgumentException("Note name too big.");
    }

    private static void checkNoteValidation(String note) throws IllegalArgumentException, NullPointerException{
        CheckDataNull.checkDataNull(note, "Note");
        if(note.length() > 2048)
            throw new IllegalArgumentException("Note name too big.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(noteName, note1.noteName) && Objects.equals(note, note1.note) && Objects.equals(userId, note1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteName, note, userId);
    }

    /**
     * Realization of Builder pattern.
     * It's used for readable and convenient use when needs to create a Note object.
     * */


    public static NoteBuilder base() {return new NoteBuilder();}

    public static class NoteBuilder{
        private Note noteBuilder;

        private NoteBuilder() {noteBuilder = new Note();}

        public NoteBuilder id(Integer id) {
            this.noteBuilder.id = id;
            return this;
        }

        public NoteBuilder noteName(String noteName) {
            checkNoteNameValidation(noteName);
            this.noteBuilder.noteName = noteName;
            return this;
        }

        public NoteBuilder note(String note) {
            checkNoteValidation(note);
            this.noteBuilder.note = note;
            return this;
        }

        public NoteBuilder userId(Integer userId) {
            this.noteBuilder.userId = userId;
            return this;
        }

        public NoteBuilder createdAt(LocalDateTime createdAt) {
            this.noteBuilder.createdAt = createdAt;
            return this;
        }

        public Note build() {return noteBuilder;}
    }
}
