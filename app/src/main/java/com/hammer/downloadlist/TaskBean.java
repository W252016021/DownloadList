package com.hammer.downloadlist;

public class TaskBean {
    private String taskName;
    private String speed;
    private String info;
    private int progress;
    private String tag;

    public TaskBean(String taskName, String speed, String info, int progress, String tag) {
        this.taskName = taskName;
        this.speed = speed;
        this.info = info;
        this.progress = progress;
        this.tag = tag;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
