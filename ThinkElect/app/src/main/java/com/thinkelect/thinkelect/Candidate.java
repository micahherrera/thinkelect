package com.thinkelect.thinkelect;

/**
 * Created by micahherrera on 8/12/16.
 */
public class Candidate {
    String firstName;
    String lastName;
    String imgUrl;
    String party;
    String contributionsTotal;

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    String office;

    public Candidate(String firstName, String lastName, String imgUrl, String party, String contributionsTotal, String office) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
        this.party = party;
        this.contributionsTotal = contributionsTotal;
        this.office = office;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getContributionsTotal() {
        return contributionsTotal;
    }

    public void setContributionsTotal(String contributionsTotal) {
        this.contributionsTotal = contributionsTotal;
    }
}
