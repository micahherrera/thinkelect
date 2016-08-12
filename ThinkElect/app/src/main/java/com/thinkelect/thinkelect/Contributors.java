package com.thinkelect.thinkelect;

/**
 * Created by micahherrera on 8/12/16.
 */
public class Contributors {
    String name;
    String contribution;

    public Contributors(String name, String contribution) {
        this.name = name;
        this.contribution = contribution;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }
}
