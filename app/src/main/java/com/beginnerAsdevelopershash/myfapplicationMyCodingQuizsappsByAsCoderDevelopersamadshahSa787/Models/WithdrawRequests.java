package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models;

public class WithdrawRequests {
    private String userId;
    private String gpaynumber;
    private String phonepay;
    private String requestedBy;

    public WithdrawRequests() {

    }

    public WithdrawRequests(String userId, String gpaynumber, String phonepay, String requestedBy) {
        this.userId = userId;
        this.gpaynumber = gpaynumber;
        this.phonepay = phonepay;
        this.requestedBy = requestedBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGpaynumber() {
        return gpaynumber;
    }

    public void setGpaynumber(String gpaynumber) {
        this.gpaynumber = gpaynumber;
    }

    public String getPhonepay() {
        return phonepay;
    }

    public void setPhonepay(String phonepay) {
        this.phonepay = phonepay;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
