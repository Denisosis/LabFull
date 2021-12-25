package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.enums.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class HistoryContent {
    private static final Logger log = LogManager.getLogger(HistoryContent.class);
    long id;
    String className;
    String createdDate = new SimpleDateFormat(ConstantsProperties.MONGODB_DATE_TEMPLATE).format(new Date());
    String actor = ConstantsProperties.ACTOR_NAME;
    Status status;
    Object jsonObject;

    public HistoryContent(String className, Status status, Object jsonObject) {
        this.className = className;
        this.status = status;
        this.jsonObject = jsonObject;
    }

    public HistoryContent() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public String toString() {
        return "org.example.HistoryContent{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", actor='" + actor + '\'' +
                ", status=" + status +
                ", jsonObject='" + jsonObject.toString() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryContent that = (HistoryContent) o;
        return id == that.id && Objects.equals(className, that.className) && Objects.equals(createdDate, that.createdDate) && Objects.equals(actor, that.actor) && status == that.status && Objects.equals(jsonObject, that.jsonObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, className, createdDate, actor, status, jsonObject);
    }
}
