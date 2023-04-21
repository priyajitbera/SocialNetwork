package com.priyajit.project.socialnetwork.strategy;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReadableDateFormatter implements DateFormatter{
    @Override
    public String format(Date date) {

        Date now = new Date();
        long diffInMillies = Math.abs(now.getTime() - date.getTime());
        long diffSecs = diffInMillies / 1000;
        long diffMins = diffSecs / 60;
        long diffHours = diffMins / 60;
        long diffDays = diffHours / 24;
        long diffMonths = diffDays / 30;
        long diffYears = diffMonths / 12;

        if(diffYears > 0) return diffYears + " y";
        if(diffMonths > 0) return diffMonths + " mon";
        if(diffDays > 0) return diffDays + " d";
        if(diffHours > 0) return diffHours + " hr";
        if(diffMins > 0) return diffMins + " m";
        return diffSecs + " s";
    }
}
