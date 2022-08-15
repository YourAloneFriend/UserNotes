package com.application.model;

import java.util.Objects;
import java.util.regex.Pattern;

/***
 *   Note module(class) with data fields and some methods.
 *   Fields:
 *      noteName - String, isn't null.
 *      note - String, isn't null.
 *      userId - Long, isn't null.
 */
public class Note {

    private String noteName;
    private String note;
    private Long userId;

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

        public NoteBuilder noteName(String noteName) {
            this.noteBuilder.noteName = noteName;
            return this;
        }

        public NoteBuilder note(String note) {
            this.noteBuilder.note = note;
            return this;
        }

        public NoteBuilder userId(Long userId) {
            this.noteBuilder.userId = userId;
            return this;
        }

        public Note build() {return noteBuilder;}
    }
}
